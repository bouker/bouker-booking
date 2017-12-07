package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@SpringBootApplication
@RestController
public class Application {
    private String REST_SERVICE_URI = "http://192.168.99.100:8080/";

    @RequestMapping("/")
    public String home() {return "complete Hello Docker World";}

    @RequestMapping(method = RequestMethod.POST, value = "/booking")
    public String add(@RequestBody Reservation input) {

        // TODO
        //save input into database
        String response = sendPost(input);
        //return UI confirmation
        return "add " + input.email+ ", response: " + response;
    }

    public String sendPost(Reservation reservation){
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForObject(REST_SERVICE_URI+"/event/"
                + reservation.eventId, reservation, String.class);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/event/{event_id}")
    public String event(@RequestBody Reservation input, @PathVariable Long event_id) {

        return "event " + event_id + " " + input.email;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
