package rosseneri.mode;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {

    public Review findByUser(String user);
}
