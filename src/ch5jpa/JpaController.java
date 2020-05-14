/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch5jpa;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import static javax.persistence.Persistence.createEntityManagerFactory;
import javax.persistence.Query;
import java.sql.Statement;


/**
 * FXML Controller class
 *
 * @author Abood Sh
 */
public class JpaController implements Initializable {

    @FXML
    private TextField TextFieldId;
    @FXML
    private TextField TextFieldName;
    @FXML
    private TextField TextFieldMajor;
    @FXML
    private TextField TextFieldGrade;
    @FXML
    private TableView<Student> tableV;
    @FXML
    private TableColumn<Student, String> coulmnID;
    @FXML
    private TableColumn<Student, String> coulmnName;
    @FXML
    private TableColumn<Student, String> coulmnMajor;
    @FXML
    private TableColumn<Student, Double> coulmnGrade;
    @FXML
    private Button btnAdd;
       
    @FXML
    private Button showbtn;
    @FXML
    private Button btnEdite;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnAddC;
    @FXML
    private Button ShowCourse;
    @FXML
    private TextArea Query;
    @FXML
    private Button performQuery;
    @FXML
    private Button updateDelBtn;
    @FXML
    private TextField IdStudent;
    @FXML
    private TextField IdCourse;
    @FXML
    private TextField smester;
    @FXML
    private TableView<Registration> tableCourse;
    @FXML
    private TableColumn<Registration, String> idStdC;
    @FXML
    private TableColumn<Registration, String> idCourseC;
    @FXML
    private TableColumn<Registration, String> smesterC;
    EntityManagerFactory emf;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
         coulmnID.setCellValueFactory(new PropertyValueFactory("id"));
        coulmnName.setCellValueFactory(new PropertyValueFactory("name"));
        coulmnMajor.setCellValueFactory(new PropertyValueFactory("major"));
        coulmnGrade.setCellValueFactory(new PropertyValueFactory("grade"));
        idStdC.setCellValueFactory(new PropertyValueFactory("studentid"));
        idCourseC.setCellValueFactory(new PropertyValueFactory("courseid"));
        smesterC.setCellValueFactory(new PropertyValueFactory("smester"));
        this.emf=Persistence.createEntityManagerFactory("Chapter5JPAPU");  
      
    }    

    @FXML
    private void buttonAddHandle(ActionEvent event) {
        Student s = new Student();
        s.setId(TextFieldId.getText());
        s.setName(TextFieldName.getText());
        s.setMajor(TextFieldMajor.getText());
        s.setGrade(Double.parseDouble(TextFieldGrade.getText()));
        EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
        em.close();
    }

    @FXML
    private void buttonShowHandle(ActionEvent event) {
        EntityManager em=this.emf.createEntityManager();
        List<Student> std=em.createNamedQuery("Student.FindALL").getResultList();
        tableV.getItems().clear();
        tableV.getItems().setAll(std);
        em.close();
    }

    @FXML
    private void buttonEditeHandle(ActionEvent event) {
        EntityManager em = this.emf.createEntityManager();
         em.getTransaction().begin();
       Query query = em.createQuery("UPDATE Student s SET s.name= :name ,s.grade= :grade,s.major= :major WHERE s.id= :id");
            query.setParameter("name",TextFieldName.getText());
            query.setParameter("grade", Double.parseDouble(TextFieldGrade.getText()));
            query.setParameter("major", TextFieldMajor.getText());
            query.setParameter("id", TextFieldId.getText());
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
       
    @FXML
    private void buttonDeleteHandle(ActionEvent event) {
           EntityManager em = this.emf.createEntityManager();
         em.getTransaction().begin();
       Query query = em.createQuery("DELETE FROM Student s WHERE s.id= :id");
           query.setParameter("id", TextFieldId.getText());
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @FXML
    private void buttonAddCHandle(ActionEvent event) {
        Registration re=new Registration();
        re.setStudentid(IdStudent.getText());
        re.setCourseid(IdCourse.getText());
        re.setSmester(smester.getText());
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(re);
        em.getTransaction().commit();
        em.close();
    }

    @FXML
    private void ShowCourseHandle(ActionEvent event) {
        EntityManager em=this.emf.createEntityManager();
        List<Registration> reg=em.createNamedQuery("Registration.FindALL").getResultList();
        tableCourse.getItems().clear();
        tableCourse.getItems().setAll(reg);
        em.close();
    }

    @FXML
    private void PerformBtnHandle(ActionEvent event) {
        String sql=Query.getText();
       EntityManager em=this.emf.createEntityManager();
        List<Student> s=em.createQuery(sql).getResultList();
        tableV.getItems().clear();
        tableV.getItems().setAll(s);
        em.close();
        
    }

    @FXML
    private void updateDelHandle(ActionEvent event) {
        String sql=Query.getText();
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(sql);
         query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    

    private void showRegInf(ActionEvent event) {
         EntityManager em = this.emf.createEntityManager();
        try{
        Registration r = (Registration) em.createNamedQuery("registration.FindByID")
                .setParameter("studentid",(IdStudent.getText()))
                .getSingleResult();
        IdStudent.setText(r.getStudentid());
        IdCourse.setText(r.getCourseid());
        smester.setText(r.getSmester()+"");
        }catch(NoResultException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Retrieving");
            alert.setContentText("No records found");
            alert.showAndWait();
        }
        em.close();
    }

    private void showStdInfoHandler(ActionEvent event) {
    
     EntityManager em = this.emf.createEntityManager();
        try{
        Student s = (Student) em.createNamedQuery("Student.FindByID")
                .setParameter("id", (TextFieldId.getText()))
                .getSingleResult();
        TextFieldName.setText(s.getName());
        TextFieldId.setText(s.getId());
        TextFieldMajor.setText(s.getMajor());
        TextFieldGrade.setText(String.valueOf(s.getGrade()));
        }catch(NoResultException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Retrieving");
            alert.setContentText("No records found");
            alert.showAndWait();
        }
        em.close();

    }
}
    

