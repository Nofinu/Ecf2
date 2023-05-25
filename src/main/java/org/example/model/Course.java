package org.example.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private int id;
    private String title;
    private int duration;
    @Temporal(TemporalType.DATE)
    private Date date;

    private int place;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "course_customer",
            joinColumns = @JoinColumn(name = "id_course"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Customer> customers;

    @ManyToMany(mappedBy = "courses")
    private List<Coach> coachs;

    public Course(String title, int duration, Date date, int place) {
        this.title = title;
        this.duration = duration;
        this.date = date;
        this.place = place;
    }

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public List<Coach> getCoachs() {
        return coachs;
    }

    public void setCoachs(List<Coach> coachs) {
        this.coachs = coachs;
    }

    public boolean addCustomer(Customer customer) {
        if (customer != null && this.place>0) {
            this.customers.add(customer);
            place -=1;
            return true;
        } else {
            return false;
        }
    }

    public boolean removeCustomer(Customer customer) {
        this.customers.forEach(System.out::println);
        if(this.customers.contains(customer)){
            this.customers.remove(customer);
            this.place +=1;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Course :" +
                "id=" + id +
                ", title=" + title +
                ", duration=" + duration +
                ", date=" + date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && duration == course.duration && place == course.place && Objects.equals(title, course.title) && Objects.equals(date, course.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, duration, date, place);
    }
}
