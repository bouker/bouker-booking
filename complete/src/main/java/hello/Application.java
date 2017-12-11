package hello;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;



@SpringBootApplication
@RestController
public class Application {
    private String REST_SERVICE_URI = "http://192.168.99.100:8080/";//"http://localhost:8080/";

    @Autowired
    ReservationRepository repository;

    @RequestMapping("/")
    public String home() {return "complete Hello Docker World";}

    @RequestMapping(method = RequestMethod.POST, value = "/booking")
    public int add(@RequestBody Reservation reservation) {
        String email = reservation.getEmail();
        String phoneNumber = reservation.getPhoneNumber();
        String number = reservation.getNumber();

        if(!DataValidator.isValidate(email, phoneNumber, number))
            return HttpStatus.BAD_REQUEST.value();

        repository.save(reservation);

        int response = 0;
        try {
            response = sendPostToEvent(reservation);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public int sendPostToEvent(Reservation reservation) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
//
//        return restTemplate.postForObject(REST_SERVICE_URI+"/events/"
//                + reservation.getEventId(), new NumberOfContestants(Integer.parseInt(reservation.getNumber())), HttpStatus.class);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(REST_SERVICE_URI+"/events/" + reservation.getEventId());

        String json = "{\"number\":\"" +reservation.getNumber() + "\"}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        client.close();
        return response.getStatusLine().getStatusCode();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/events/{event_id}")
    public int event(@RequestBody NumberOfContestants input, @PathVariable Long event_id) {
        //...
        return HttpStatus.FOUND.value();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
