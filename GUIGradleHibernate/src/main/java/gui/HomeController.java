package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends ProjectMethods implements Initializable {
    @FXML
    private Label accountLabel;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setAccountLabel(User user){
        accountLabel.setText(user.getUsername());
    }
}
