package org.example.model;

import javax.persistence.Entity;

@Entity
public class Coach extends Person {
    private String sports;
    public Coach(String lastName, String firstName) {
        super(lastName, firstName);
    }

    public Coach() {
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    @Override
    public String toString() {
        return "Coach : " +
                "id = " + this.getId() +
                ", lastName = " + this.getLastName() +
                ", firstName = " + this.getFirstName()+
                ", sports = " + sports ;
    }
}
