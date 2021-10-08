package rosseneri.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rosseneri.model.Business;
import rosseneri.model.Review;
import rosseneri.model.ReviewRepository;
import rosseneri.model.YelpResponse.GetReviewResponse;
import rosseneri.model.YelpResponse.SearchResponse;
import rosseneri.service.YelpService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {


    private final ReviewRepository reviewRepository;

    private YelpService yelpService;

    public Controller(ReviewRepository reviewRepository, YelpService yelpService) {
        this.reviewRepository = reviewRepository;
        this.yelpService = yelpService;
    }

    @GetMapping("/businesses")
    List<Business> findBizByAddress(@RequestParam(name = "addr") String address) {
        return this.yelpService.searchBizByAddress(address);
    }

    @GetMapping("/reviews")
    List<Review> getReviewByBizId(@RequestParam(name = "biz") String bizId) {
        return this.yelpService.getReviewByBizId(bizId);
    }

}