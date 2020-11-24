package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends ProjectMethods implements Initializable {
    @FXML
    private Label accountLabel;
    @FXML
    private JFXButton logoutBtn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void logoutBtnOnAction(javafx.event.ActionEvent actionEvent){
        closeHandler(actionEvent);
        createLoginForm();

    }


    public void setAccountLabel(User user){
        accountLabel.setText(user.getUsername());
    }
}
