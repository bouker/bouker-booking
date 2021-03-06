package hello;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;



@SpringBootApplication
@RestController
@CrossOrigin
public class Application {

    static Logger logger = Logger.getLogger(Application.class.getName());

    @Value("${spring.rest.event}")
    private String REST_EVENT_URI;

    @Autowired
    ReservationRepository repository;

    @RequestMapping("/")
    public String home() {return "complete Hello Docker World!";}

    @RequestMapping(method = RequestMethod.POST, value = "/booking")
    public int add(@RequestBody Reservation reservation) {
        String email = reservation.getEmail();
        String phoneNumber = reservation.getPhoneNumber();
        String number = reservation.getNumber();

        if(!DataValidator.isValidate(email, phoneNumber, number))
            return HttpStatus.BAD_REQUEST.value();

        logger.info("Save reservation to repository - (mail: " + email +", phoneNumber: " + phoneNumber + ", number: " + number + ")");
        repository.save(reservation);

        int response = 0;
        try {
            response = sendPostToEvent(reservation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public int sendPostToEvent(Reservation reservation) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(REST_EVENT_URI +"/events/" + reservation.getEventId());

        String json = "{\"number\":\"" +reservation.getNumber() + "\"}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        logger.info("create response to event: " + json);
        CloseableHttpResponse response = client.execute(httpPost);
        client.close();
        return response.getStatusLine().getStatusCode();
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/events/{event_id}")
//    public int event(@RequestBody NumberOfContestants input, @PathVariable Long event_id) {
//        //...
//        return HttpStatus.FOUND.value();
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
