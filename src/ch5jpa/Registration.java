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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Abood Sh
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Registration.FindALL",
            query="SELECT reg FROM Registration reg"),
    @NamedQuery(name="registration.FindByID",
            query="SELECT reg FROM Registration reg WHERE reg.studentid = :studentid")
})
public class Registration implements Serializable  {
   
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String studentid ;
    @Id 
    private String courseid;
    private String smester;

    public Registration() {
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getSmester() {
        return smester;
    }

    public void setSmester(String smester) {
        this.smester = smester;
    }

   
    
}
