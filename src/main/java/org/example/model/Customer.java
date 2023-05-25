package org.example.model;

import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer extends Person {

    @Temporal(TemporalType.DATE)
    @Column(name = "date_end_inscription")
    private Date dateEndInscription;

    @ManyToOne()
    @JoinColumn(name = "id_address", nullable = false)
    private Address address;

    @ManyToMany(mappedBy = "customers",fetch = FetchType.EAGER)
    private List<Course> courses;

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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public boolean addCourse(Course course) {
        if (course != null) {
            this.courses.add(course);
            return true;
        } else {
            return false;
        }
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return this.getId() == customer.getId();
    }
}
