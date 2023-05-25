package org.example.model;

import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Customer extends Person {

    @Temporal(TemporalType.DATE)
    @Column(name = "date_end_inscription")
    private Date dateEndInscription;

    @ManyToOne()
    @JoinColumn(name = "id_address", nullable = false)
    private Address address;

    public Customer(String lastName, String firstName, Date dateEndInscription) {
        super(lastName, firstName);
        this.dateEndInscription = dateEndInscription;
    }

    public Customer() {
    }

    public Date getDateEndInscription() {
        return dateEndInscription;
    }

    public void setDateEndInscription(Date dateEndInscription) {
        this.dateEndInscription = dateEndInscription;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer :" +
                "id=" + this.getId() +
                ", lastName = " + this.getLastName() +
                ", firstName = " + this.getFirstName() +
                ", dateEndInscription = " + dateEndInscription
                +"  "+ address;
    }
}
