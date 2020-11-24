package gui;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController extends ProjectMethods implements Initializable {
    @FXML
    private JFXButton loginBtn, closeBtn,registerBtn;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXTextField loginField;
    @FXML
    private ImageView shieldIcon;
    private EntityManagerFactory entityManagerFactory;
    @FXML
    private Label loginInfoLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        shieldIcon.setImage(new Image(new File("C:\\Users\\kodzi\\IdeaProjects\\GUIGradleHibernate\\src\\main\\java\\gui\\images\\shieldImage.png").toURI().toString()));
        loginBtn.setDisable(true);
        new Thread(()->{
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GUIGradleHibernate");
            setCon(entityManagerFactory);
            loginBtn.setDisable(false);
        }).start();


    }



    public void loginBtnOnAction(javafx.event.ActionEvent actionEvent) throws InterruptedException {
        if(checkUser(loginField.getText(),passwordField.getText(),getCon())){
            setUser(loginField.getText(),passwordField.getText(),getCon());
            closeHandler(actionEvent);
            createHomeForm();
            getCon().close();
        }
        else{
            loginInfoLabel.setVisible(false);
            loginInfoLabel.setText("Login unsuccessful!");
            loginInfoLabel.setTextFill(Color.RED);
            loginInfoLabel.setVisible(true);
            rotateButton(loginBtn);
        }
    }
    public void closeBtnButtonOnAction(javafx.event.ActionEvent actionEvent) {
        closeHandler(actionEvent);
        getCon().close();
    }
    public void registerBtnOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage1 = (Stage) registerBtn.getScene().getWindow();
        stage1.close();
        createRegisterForm();
        getCon().close();
    }
    public void setCon(EntityManagerFactory em){
        this.entityManagerFactory=em;
    }
    public EntityManagerFactory getCon(){
        return entityManagerFactory;
    }


}
