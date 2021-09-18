package rosseneri.mode;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusinessRepository extends MongoRepository<Business, String> {

    public Business findByName(String name);
}
