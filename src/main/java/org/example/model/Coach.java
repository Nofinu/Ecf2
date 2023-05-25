package org.example.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Coach extends Person {
    private String sports;

    @ManyToMany(mappedBy = "coachs", fetch = FetchType.EAGER)
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

    public void removeCourse(Course course) {
        this.courses.remove(course);
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
