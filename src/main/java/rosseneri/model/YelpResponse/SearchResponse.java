package rosseneri.model.YelpResponse;

import rosseneri.model.Business;

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