package rosseneri.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

public class Business implements Comparable {

    @Id
    public String id;

    @Indexed
    public String alias;

    public String name;

    public double rating;

    @JsonProperty("review_count")
    public int reviewCount;

    public int reviewIncCount;

    public int getReviewIncCount() {
        return reviewIncCount;
    }

    public void setReviewIncCount(int reviewIncCount) {
        this.reviewIncCount = reviewIncCount;
    }

    public String address;

    public Business() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int compareTo(Object anotherBizObj) {
        Business anotherBiz = (Business) anotherBizObj;
        if (this.getReviewIncCount() == anotherBiz.getReviewIncCount()) {
            if (this.getReviewCount() == anotherBiz.getReviewCount()) {
                return this.getRating() - anotherBiz.getRating() < 0 ? -1 : 1;
            } else {
                return anotherBiz.getReviewCount() - this.getReviewCount();
            }
        } else {
            return anotherBiz.getReviewIncCount() - this.getReviewIncCount();
        }
    }
}