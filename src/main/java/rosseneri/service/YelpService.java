package rosseneri.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rosseneri.mode.YelpResponse.SearchResponse;

@Service
public class YelpService {


    private RestTemplate restTemplate;


    public YelpService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SearchResponse searchRestaurantByAddress(String address) {
        SearchResponse searchResponse = this.restTemplate.getForObject("https://api.yelp.com/v3/businesses/search?location=" + address, SearchResponse.class);
        System.out.println("at here");
        System.out.println(searchResponse);
        return searchResponse;
    }
}
