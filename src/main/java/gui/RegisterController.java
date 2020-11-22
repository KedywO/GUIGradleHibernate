package gui;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController extends ProjectMethods implements Initializable {
    @FXML
    private JFXButton registerBtn,backBtn,cancelBtn;
    @FXML
    private ImageView registerImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(new File("C:\\Users\\Radek\\IdeaProjects\\GUIGradleHibernate\\src\\main\\java\\gui\\images\\registrationImage.png").toURI().toString());
        registerImage.setImage(image);       
    }
    public void registerBtnOnAction(ActionEvent actionEvent) {

    }
    public void backBtnOnAction(ActionEvent actionEvent) {
        closeHandler(actionEvent);
        createLoginForm();
    }
    public void cancelBtnOnAction(ActionEvent actionEvent) {
        closeHandler(actionEvent);
    }


}






















