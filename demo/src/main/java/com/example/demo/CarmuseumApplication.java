package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Car;
import com.example.demo.domain.CarRepository;
import com.example.demo.domain.Location;
import com.example.demo.domain.LocationRepository;
import com.example.demo.domain.Owner;
import com.example.demo.domain.OwnerRepository;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;

@SpringBootApplication
public class CarmuseumApplication {

    // Logger for logging application events
    private static final Logger log = LoggerFactory.getLogger(CarmuseumApplication.class);

    // Application entry point
    public static void main(String[] args) {
        SpringApplication.run(CarmuseumApplication.class, args);
    }

    // CommandLineRunner to insert sample data for Location and Owner entities
    @Bean
    public CommandLineRunner locationdemo(LocationRepository LocationRepo, OwnerRepository ownerRepository) {
        return (args) -> {
            // Creating Location entities
            Location l1 = new Location("Kuopio");
            Location l2 = new Location("Helsinki");

            // Saving Location entities to the repository
            LocationRepo.save(l1);
            LocationRepo.save(l2);

            // Creating Owner entities
            Owner o1 = new Owner("Valtionkonttori");
            Owner o2 = new Owner("Kimmo Käki");

            // Saving Owner entities to the repository
            ownerRepository.save(o1);
            ownerRepository.save(o2);
        };
    }

    // CommandLineRunner to insert sample data for Car, Location, Owner, and User entities
    @Bean
    public CommandLineRunner cardemo(
            CarRepository CarRepo,
            LocationRepository LocationRepo,
            UserRepository userRepository,
            OwnerRepository ownerRepository
    ) {
        return (args) -> {
            // Retrieving Location entities from the repository
            Location l1 = LocationRepo.findByLocationName("Kuopio");
            Location l2 = LocationRepo.findByLocationName("Helsinki");

            // Creating and saving new Location entities if not found
            LocationRepo.save(l1);
            LocationRepo.save(l2);

            // Retrieving Owner entities from the repository
            Owner o1 = ownerRepository.findByOwnerName("Valtionkonttori");
            Owner o2 = ownerRepository.findByOwnerName("Kimmo Käki");

            // Creating and saving new Owner entities if not found
            ownerRepository.save(o1);
            ownerRepository.save(o2);

            // Creating Car entities
            Car c1 = new Car("Mercedes-benz", "230SL", 1967, l1, o1);
            Car c2 = new Car("BMW", "CSL", 1972, l2, o2);

            // Saving Car entities to the repository
            CarRepo.save(c1);
            CarRepo.save(c2);

            // Creating User entities
            User user1 = new User("user", "$2a$10$nThzA3dYU69I37hBPhGWeeo8tKCJRMu2mQT34h4Ruxn9M2.V4hxjG", "USER");
            User user2 = new User("admin", "$2a$10$pS3lLe8CodChjSOxcM7avuSe5/MiX74w106girye0o65xU8FS6iy.", "ADMIN");

            // Saving User entities to the repository
            userRepository.save(user1);
            userRepository.save(user2);

            // Logging information about all users
            log.info("fetch all users");
            for (User user : userRepository.findAll()) {
                log.info(user.toString());
            }
        };
    }
}
