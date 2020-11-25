package gui;

import com.jfoenix.controls.JFXButton;
import com.sun.istack.NotNull;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Criteria;
import javafx.animation.*;
import javafx.util.Duration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;

import static javafx.scene.paint.Color.TRANSPARENT;

public class ProjectMethods extends Application {
    private User user;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 752, 399));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void closeHandler(javafx.event.ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void createRegisterForm(){
        try {
            Parent root = null;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
            Stage registerStage = new Stage();
            //registerStage.setTitle("Register");
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 602, 677));
            registerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public boolean checkUser(String login, String password, EntityManagerFactory entityManagerFactory){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Predicate loginCheck = criteriaBuilder.like(root.get("username"),login);
        Predicate passwordCheck = criteriaBuilder.like(root.get("password"),password);
        criteriaQuery.select(root).where(criteriaBuilder.and(loginCheck,passwordCheck));
        List<User> results = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        if(results.isEmpty()){
            return false;
        }
        else if(!results.isEmpty()){
            return true;
        }
        return false;
    }

    public void setUser(String login, String password, @NotNull EntityManagerFactory entityManagerFactory){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Predicate loginCheck = criteriaBuilder.like(root.get("username"),login);
        Predicate passwordCheck = criteriaBuilder.like(root.get("password"),password);
        criteriaQuery.select(root).where(criteriaBuilder.and(loginCheck,passwordCheck));
        List<User> results = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        this.user = results.get(0);
    }

    public void createHomeForm(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController controller = loader.getController();


            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 923,570));

            controller.setUser(user);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createLoginForm(){
        try {
            Parent root = null;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Stage loginStage = new Stage();
            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.setScene(new Scene(root, 752, 399));
            loginStage.show();
        }
        catch(Exception r){
            r.printStackTrace();
            r.getCause();
        }

    }

    public void rotateButton(JFXButton btn){


            RotateTransition rt=new RotateTransition(Duration.millis(60),btn);
            rt.setByAngle(45);
            rt.setCycleCount(2);
            rt.setAutoReverse(true);
            rt.play();

            /*rt.setOnFinished(event -> {
                RotateTransition rt2=new RotateTransition(Duration.millis(60),btn);
                rt2.setByAngle(-45);
                rt2.setCycleCount(2);
                rt2.setAutoReverse(true);
                rt2.play();
                rt2.setOnFinished(event1 -> rotatedpane =false);
            });*/

    }


}
