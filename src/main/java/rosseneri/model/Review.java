package rosseneri.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.Map;

public class Review {

    @Id
    public String id;

    public String url;
    public String text;
    public int rating;

    public String userName;

    @BsonIgnore
    @JsonProperty("user")
    private void unpackNameFromUser(Map<String, String> user) {
        userName = user.get("name");
    }


    @JsonProperty("time_created")
    public String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Review() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                ", userName='" + userName + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}