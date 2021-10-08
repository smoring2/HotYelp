package rosseneri.model.YelpResponse;

import rosseneri.model.Review;

import java.util.List;

public class GetReviewResponse {

    public List<Review> reviews;

    public GetReviewResponse() {
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}