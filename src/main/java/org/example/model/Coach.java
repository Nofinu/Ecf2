package org.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Coach extends Person {
    private String sports;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "coach_course",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_course"))
    private List<Course> courses;

    public Coach(String lastName, String firstName, String sports) {
        super(lastName, firstName);
        this.sports = sports;
    }

    public Coach() {
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
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

    public boolean removeCourse(Course course) {
        if(courses.contains(course)){
            this.courses.remove(course);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Coach : " +
                "id = " + this.getId() +
                ", lastName = " + this.getLastName() +
                ", firstName = " + this.getFirstName() +
                ", sports = " + sports;
    }
}
