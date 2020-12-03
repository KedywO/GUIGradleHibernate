package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.stage.StageStyle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController extends ProjectMethods implements Initializable {
    @FXML
    private JFXButton registerBtn,backBtn,cancelBtn;
    @FXML
    private ImageView registerImage;
    @FXML
    private JFXTextField cityField, usernameField, emailField;
    @FXML
    private Label registerInfoLabel;
    @FXML
    private JFXPasswordField passwordField,confirmPasswordField;
    private  EntityManagerFactory entityManagerFactory;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(new File("C:\\Users\\kodzi\\IdeaProjects\\GUIGradleHibernate\\src\\main\\java\\gui\\images\\registrationImage.png").toURI().toString());
        registerImage.setImage(image);
    }

    public void registerBtnOnAction(ActionEvent actionEvent) {
        if(emailField.getText().contains("@")&&usernameField.getText()!=""&&emailField.getText()!=""&&passwordField.getText()!=""&&passwordField.getText().equals(confirmPasswordField.getText()))
        {
            User user = new User();
            user.setUsername(usernameField.getText());
            user.setPassword(passwordField.getText());
            user.setCity(cityField.getText());
            user.setMail(emailField.getText());

            EntityManager em = EMF.createEntityManager();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            Predicate mail = criteriaBuilder.like(root.get("mail"),emailField.getText());
            Predicate username = criteriaBuilder.like(root.get("username"),usernameField.getText());
            criteriaQuery.select(root).where(criteriaBuilder.or(mail,username));
            List<User> results = em.createQuery(criteriaQuery).getResultList();
            System.out.println(results);
            if(!results.isEmpty()&&results.get(0).getUsername().equals(usernameField.getText())){
                registerInfoLabel.setTextFill(Color.RED);
                registerInfoLabel.setText("Username taken!");
                em.close();
            }
            else if(!results.isEmpty()){
                registerInfoLabel.setTextFill(Color.RED);
                registerInfoLabel.setText("Email taken!");
                em.close();
            }
            else if(results.isEmpty()){
                registerInfoLabel.setTextFill(Color.GREEN);
                registerInfoLabel.setText("Registered successfully!");
                em.getTransaction().begin();
                System.out.println(user);
                em.persist(user);
                em.getTransaction().commit();
                em.close();
                usernameField.clear();
                passwordField.clear();
                confirmPasswordField.clear();
                emailField.clear();
                cityField.clear();
            }



        }
        else if(usernameField.getText()==""||emailField.getText()=="") {
            registerInfoLabel.setTextFill(Color.RED);
            registerInfoLabel.setText("Username or email field are empty!");


        }
        else if(!emailField.getText().contains("@")){

            registerInfoLabel.setTextFill(Color.RED);
            registerInfoLabel.setText("Make sure email contains right marks!");
        }
        else if(!passwordField.getText().equals(confirmPasswordField.getText())){
            registerInfoLabel.setTextFill(Color.RED);
            registerInfoLabel.setText("Passwords doesn't match!");

        }

    }

    public void backBtnOnAction(ActionEvent actionEvent) {
        closeHandler(actionEvent);
        createLoginForm();
    }

    public void cancelBtnOnAction(ActionEvent actionEvent) {
        closeHandler(actionEvent);
        EMF.emfDestroy();
    }
}






















