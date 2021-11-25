package covidlocation;

import covidlocation.models.Location;
import covidlocation.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableEurekaClient
public class LocationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LocationServiceApplication.class, args);
       /* Timestamp now = new Timestamp(System.currentTimeMillis());
        User imad = new User(1,"Imad");
        User jean = new User(2,"Jean");
        Location location1 = new Location(1,99,99,now,imad);
        Location location2 = new Location(1,99,99,now,jean);
        System.out.println(location1.isMoreRecentThan(7));

        */



    }

}
