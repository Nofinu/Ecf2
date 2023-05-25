package org.example.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private int id;
    private String title;
    @Temporal(TemporalType.TIME)
    private Date duration;
    @Temporal(TemporalType.DATE)
    private Date date;

    public Course(String title, Date duration, Date date) {
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

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
