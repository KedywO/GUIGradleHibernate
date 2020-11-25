package gui;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends ProjectMethods implements Initializable {
    @FXML
    private Label accountLabel,accountLabel2,emailLabel,chartLabel,ordersLabel,cityLabel;
    @FXML
    private JFXButton logoutBtn, exitBtn,albumsBtn,changePasswordBtn,newPasswordSaveBtn,accountBtn;
    @FXML
    private AnchorPane accountAPane;
    @FXML
    private PasswordField confirmNewPasswordField,newPasswordField;
    private User user;
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountAPane.setVisible(false);

    }

    public void logoutBtnOnAction(javafx.event.ActionEvent actionEvent){
        closeHandler(actionEvent);
        createLoginForm();

    }

    public void accountBtnOnAction(javafx.event.ActionEvent actionEvent){
        accountAPane.setVisible(true);
        newPasswordField.setVisible(false);
        confirmNewPasswordField.setVisible(false);
        newPasswordSaveBtn.setVisible(false);
        accountLabel2.setText(user.getUsername());
        emailLabel.setText(user.getMail());
        cityLabel.setText(user.getCity());
        chartLabel.setText(String.valueOf(user.getChart()));
        ordersLabel.setText(String.valueOf(user.getOrders()));

    }

    public void changePasswordBtnOnAction(javafx.event.ActionEvent actionEvent){
        confirmNewPasswordField.setVisible(true);
        newPasswordField.setVisible(true);
        newPasswordSaveBtn.setVisible(true);
        new Thread(()->{
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GUIGradleHibernate");
            setCon(entityManagerFactory);
        }).start();
    }

    public void setNewPasswordSaveBtnOnAction(ActionEvent actionEvent){
        if(newPasswordField.getText().equals(confirmNewPasswordField.getText())&&newPasswordField.getText().length()>2){

            EntityManager em = getCon().createEntityManager();
            em.getTransaction().begin();
            User pushUser = new User();
            pushUser = em.find(User.class, user.getId());
            em.merge(pushUser);
            pushUser.setPassword(newPasswordField.getText());
            em.getTransaction().commit();
            em.close();
            getCon().close();
            newPasswordField.clear();
            confirmNewPasswordField.clear();
            newPasswordSaveBtn.setVisible(false);
            newPasswordField.setVisible(false);
            confirmNewPasswordField.setVisible(false);

        }

    }

    public void albumsBtnOnAction(javafx.event.ActionEvent actionEvent) {

    }

    public void exitBtnOnAction(javafx.event.ActionEvent actionEvent){
        closeHandler(actionEvent);
    }

    public void setCon(EntityManagerFactory em){
        this.entityManagerFactory=em;
    }

    public EntityManagerFactory getCon(){
        return entityManagerFactory;
    }

    public void setUser(User user){
        this.user = user;
        accountLabel.setText(user.getUsername());
    }
}
