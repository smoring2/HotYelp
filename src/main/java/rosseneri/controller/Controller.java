package rosseneri.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rosseneri.model.Business;
import rosseneri.model.Review;
import rosseneri.model.ReviewRepository;
import rosseneri.service.YelpService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    private final ReviewRepository reviewRepository;

    private YelpService yelpService;

    public Controller(ReviewRepository reviewRepository, YelpService yelpService) {
        this.reviewRepository = reviewRepository;
        this.yelpService = yelpService;
    }

    @GetMapping("/businesses")
    List<Business> findBizByAddress(@RequestParam(name = "addr") String address, Principal principal) {
        log.info("Business search requested by user={} for address={}", principal.getName(), address);
        return this.yelpService.searchBizByAddress(address);
    }

    @GetMapping("/reviews")
    List<Review> getReviewByBizId(@RequestParam(name = "biz") String bizId, Principal principal) {
        log.info("Review lookup requested by user={} for bizId={}", principal.getName(), bizId);
        return this.yelpService.getReviewByBizId(bizId);
    }

}
