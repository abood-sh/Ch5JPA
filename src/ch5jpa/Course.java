/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch5jpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Abood Sh
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Course.FindALL", 
            query="SELECT c FROM Course c"),
    @NamedQuery(name="Course.FindByID", 
            query="SELECT c FROM Course c WHERE c.id = :id")
})
public class Course implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String room;

    public Course() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    
    
}
