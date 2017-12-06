package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    Reservation() { // jpa only
    }

    public Reservation(int eventId, String email, String phoneNumber, String fullName, String number) {
        this.eventId = eventId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.number = number;
    }

    public int eventId;
    public String email;
    public String phoneNumber;
    public String fullName;
    public String number;

    public Long getId() {
        return id;
    }
}
