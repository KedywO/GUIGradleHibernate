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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //File n = new File("C:\\Users\\kodzi\\IdeaProjects\\ProjecktGUI\\src\\sample\\images\\shieldImage.png");
        //Image shieldImage = new Image(n.toURI().toString());
        shieldIcon.setImage(new Image(new File("C:\\Users\\Radek\\IdeaProjects\\GUIGradleHibernate\\src\\main\\java\\gui\\images\\shieldImage.png").toURI().toString()));

    }



    public void loginBtnOnAction(javafx.event.ActionEvent actionEvent){
        if(checkUser(loginField.getText(),passwordField.getText())){
            setUser(loginField.getText(),passwordField.getText());
            closeHandler(actionEvent);
            createHomeForm();
        }
    }
    public void closeBtnButtonOnAction(javafx.event.ActionEvent actionEvent) {
        closeHandler(actionEvent);
    }
    public void registerBtnOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage1 = (Stage) registerBtn.getScene().getWindow();
        stage1.close();
        createRegisterForm();
    }


}
