package rosseneri.mode.YelpResponse;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import rosseneri.mode.Business;
import rosseneri.mode.Review;

import java.util.List;

public class SearchResponse {

    public List<Business> businesses;

    public SearchResponse() {
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }
}