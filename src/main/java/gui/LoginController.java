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
import javafx.scene.layout.AnchorPane;
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
    private JFXTextField loginField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXButton loginBtn, closeBtn,registerBtn;
    @FXML
    private ImageView shieldIcon;
    private EntityManagerFactory entityManagerFactory;
    @FXML
    private Label loginInfoLabel;
    @FXML
    private AnchorPane loginPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        shieldIcon.setImage(new Image(new File("C:\\Users\\kodzi\\IdeaProjects\\GUIGradleHibernate\\src\\main\\java\\gui\\images\\shieldImage.png").toURI().toString()));
        loginPane.setFocusTraversable(true);
    }

    public void loginBtnOnAction(javafx.event.ActionEvent actionEvent) throws InterruptedException {
        //setUser("radek","zxc");
        //closeHandler(actionEvent);
        //createHomeForm();
        if(checkUser(loginField.getText(),passwordField.getText())){
            setUser(loginField.getText(),passwordField.getText());
            closeHandler(actionEvent);
            createHomeForm();

        }
        else{
            loginInfoLabel.setVisible(false);
            loginInfoLabel.setText("Logowanie sie nie powiod\u0142o!");
            loginInfoLabel.setTextFill(Color.RED);
            loginInfoLabel.setVisible(true);
            rotateButton(loginBtn);
        }
    }

    public void closeBtnButtonOnAction(javafx.event.ActionEvent actionEvent) {
        closeHandler(actionEvent);
        EMF.emfDestroy();
    }

    public void registerBtnOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage1 = (Stage) registerBtn.getScene().getWindow();
        stage1.close();
        createRegisterForm();
    }



}
