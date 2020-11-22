package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ProjectMethods {
    private User user;

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
    public boolean checkUser(String login, String password){
        return true;
    }

    public void setUser(String login, String password){
        this.user = new User();
    }
    public void createHomeForm(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController controller = loader.getController();
            controller.setAccountLabel(user);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 907,600));
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

}
