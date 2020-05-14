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
    @NamedQuery(name="Student.FindALL",
            query="SELECT s FROM Student s"),
    @NamedQuery(name="Student.FindByID",
            query="SELECT s FROM Student s WHERE s.id= :id ")
})
public class Student implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String major;
    private double grade;

    public Student() {
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
    
    
    
}
