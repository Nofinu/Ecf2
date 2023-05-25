package org.example.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Course_Customer",
            joinColumns = @JoinColumn(name = "id_course"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Customer> customers;

    public Course(String title, int duration, Date date) {
        this.title = title;
        this.duration = duration;
        this.date = date;
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

    public boolean addCustomer (Customer customer){
        if(customer !=null){
            this.customers.add(customer);
            return true;
        }else{
            return false;
        }
    }

    public void removeCustomer (Customer customer){
        this.customers.remove(customer);
    }

    @Override
    public String toString() {
        return "Course :" +
                "id=" + id +
                ", title=" + title +
                ", duration=" + duration +
                ", date=" + date ;
    }
}
