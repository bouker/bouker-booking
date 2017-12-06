package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("/")
    public String home() {return "complete Hello Docker World";}

    @RequestMapping(method = RequestMethod.POST, value = "/booking")
    public String add(@RequestBody Reservation input) {

        // TODO
        //save input into database
        //decrease number of people
        //send UI confirmation
        return input.email;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
