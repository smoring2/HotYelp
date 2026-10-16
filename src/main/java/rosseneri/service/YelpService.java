package rosseneri.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rosseneri.model.Business;
import rosseneri.model.BusinessRepository;
import rosseneri.model.Review;
import rosseneri.model.ReviewRepository;
import rosseneri.model.YelpResponse.GetReviewResponse;
import rosseneri.model.YelpResponse.SearchResponse;

import java.util.*;

@Service
public class YelpService {

    private static final Logger log = LoggerFactory.getLogger(YelpService.class);

    private RestTemplate restTemplate;

    private BusinessRepository bizRepository;
    private ReviewRepository reviewRepository;


    public YelpService(RestTemplate restTemplate, BusinessRepository bizRepository,
                       ReviewRepository reviewRepository) {
        this.restTemplate = restTemplate;
        this.bizRepository = bizRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Business> searchBizByAddress(String address) {
        log.info("Calling Yelp Business Search API for address={}", address);
        SearchResponse searchResponse = this.restTemplate.getForObject("https://api.yelp.com/v3/businesses/search?location=" + address, SearchResponse.class);
        if (searchResponse != null) {

            for (Business biz : searchResponse.getBusinesses()) {
                Optional<Business> oldBizOpt = this.bizRepository.findById(biz.getId());
                oldBizOpt.ifPresent(business -> biz.setReviewIncCount(biz.getReviewCount() - business.getReviewCount()));
            }

            this.bizRepository.saveAll(searchResponse.businesses);
            log.info("Persisted {} businesses from Yelp search for address={}", searchResponse.businesses.size(), address);
            Collections.sort(searchResponse.getBusinesses());
            return searchResponse.getBusinesses();
        }

        log.warn("Yelp Business Search API returned null for address={}", address);
        return new ArrayList<>();
    }

    public List<Review> getReviewByBizId(String bizId) {
        log.info("Calling Yelp Reviews API for bizId={}", bizId);
        GetReviewResponse getReviewResponse = this.restTemplate.getForObject("https://api.yelp.com/v3/businesses/" + bizId + "/reviews", GetReviewResponse.class);

        if (getReviewResponse != null) {
            this.reviewRepository.saveAll(getReviewResponse.reviews);
            log.info("Persisted {} reviews from Yelp for bizId={}", getReviewResponse.reviews.size(), bizId);
            return getReviewResponse.reviews;
        }

        log.warn("Yelp Reviews API returned null for bizId={}", bizId);
        return new ArrayList<>();
    }
}
