package rosseneri;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rosseneri.mode.Review;
import rosseneri.mode.ReviewRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ReviewRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        // save a couple of customers
        repository.save(new Review("test-id", "test-user1", "test-comment-1", new Date()));
        repository.save(new Review("test-user2", "test-comment-2", new Date()));

        // fetch all customers
        System.out.println("Comments found with findAll():");
        System.out.println("-------------------------------");
        for (Review review : repository.findAll()) {
            System.out.println(review);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Comment found with findByUser('test-user1'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByUser("test-user1"));
    }

}


