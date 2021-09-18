package rosseneri.mode;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Review {

    @Id
    public String id;

    public String user;
    public String comment;
    public Date commentDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Review() {
    }

    public Review(String id, String user, String comment, Date commentDate) {
        this.id = id;
        this.user = user;
        this.comment = comment;
        this.commentDate = commentDate;
    }

    public Review(String user, String comment, Date commentDate) {
        this.user = user;
        this.comment = comment;
        this.commentDate = commentDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", comment='" + comment + '\'' +
                ", commentDate=" + commentDate +
                '}';
    }

}