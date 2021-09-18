package rosseneri.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rosseneri.mode.Review;
import rosseneri.mode.ReviewRepository;
import rosseneri.mode.YelpResponse.SearchResponse;
import rosseneri.service.YelpService;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class Controller {


    private final ReviewRepository reviewRepository;

    private YelpService yelpService;

    public Controller(ReviewRepository reviewRepository, YelpService yelpService) {
        this.reviewRepository = reviewRepository;
        this.yelpService = yelpService;
    }

    @GetMapping("/comments")
    Collection<Review> comments() {
        System.out.println("comments");
        return reviewRepository.findAll();
    }

    @GetMapping("/businesses")
    SearchResponse findBusinessByAddress(@RequestParam(name = "addr") String address) {
        return this.yelpService.searchRestaurantByAddress(address);
    }

}