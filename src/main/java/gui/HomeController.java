package gui;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class HomeController extends ProjectMethods implements Initializable {
    @FXML
    private Label accountLabel,accountLabel2,emailLabel,chartLabel,ordersLabel,cityLabel,panelName,albumNameLabel,albumAuthorLabel,descriptionField,statusLabel,releaseDateLabel;
    @FXML
    private JFXButton logoutBtn, exitBtn,albumsBtn,changePasswordBtn,newPasswordSaveBtn,accountBtn,addToCartBtn;
    @FXML
    private TextField searchField;
    @FXML
    private AnchorPane accountAPane,albumsAPane;
    @FXML
    private PasswordField confirmNewPasswordField,newPasswordField;
    @FXML
    private ImageView albumImg;
    private User user;
    private EntityManagerFactory entityManagerFactory;
    private ArrayList<String> albumNamesList;
    private List<Albums> results;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hideAllPanes();
        EntityManager em = EMF.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Albums> cr =cb.createQuery(Albums.class);
        Root<Albums> albumsRoot = cr.from(Albums.class);
        cr.select(albumsRoot);
        results = new ArrayList<>();
        results = em.createQuery(cr).getResultList();
        em.close();
        albumNamesList = new ArrayList<>();
        for(Albums a : results){
            albumNamesList.add(a.getAlbumName());
        }
        System.out.println(results.get(0));
        System.out.println(albumNamesList.get(0));



    }

    public void logoutBtnOnAction(javafx.event.ActionEvent actionEvent){
        closeHandler(actionEvent);
        createLoginForm();
    }
    //ACCOUNT PANE
    public void accountBtnOnAction(javafx.event.ActionEvent actionEvent){
        hideAllPanes();
        accountAPane.setVisible(true);
        newPasswordField.setVisible(false);
        confirmNewPasswordField.setVisible(false);
        newPasswordSaveBtn.setVisible(false);
        accountLabel2.setText(user.getUsername());
        emailLabel.setText(user.getMail());
        cityLabel.setText(user.getCity());
        chartLabel.setText(String.valueOf(user.getChart()));
        ordersLabel.setText(String.valueOf(user.getOrders()));
        panelName.setText("Account");

    }

    public void changePasswordBtnOnAction(javafx.event.ActionEvent actionEvent){
        confirmNewPasswordField.setVisible(true);
        newPasswordField.setVisible(true);
        newPasswordSaveBtn.setVisible(true);
    }

    public void setNewPasswordSaveBtnOnAction(ActionEvent actionEvent){
        if(newPasswordField.getText().equals(confirmNewPasswordField.getText())&&newPasswordField.getText().length()>2){

            EntityManager em = EMF.createEntityManager();
            em.getTransaction().begin();
            User pushUser = new User();
            pushUser = em.find(User.class, user.getId());
            em.merge(pushUser);
            pushUser.setPassword(newPasswordField.getText());
            em.getTransaction().commit();
            em.close();
            newPasswordField.clear();
            confirmNewPasswordField.clear();
            newPasswordSaveBtn.setVisible(false);
            newPasswordField.setVisible(false);
            confirmNewPasswordField.setVisible(false);

        }

    }

    public void albumsBtnOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        hideAllPanes();
        albumsAPane.setVisible(true);
        panelName.setText("Albums");
        startSearch();
        setMovieData(new Random().nextInt(results.size()));
    }

    public void exitBtnOnAction(javafx.event.ActionEvent actionEvent){
        closeHandler(actionEvent);
        EMF.emfDestroy();
    }

    public void setUser(User user){
        this.user = user;
        accountLabel.setText(user.getUsername());
    }

    public void hideAllPanes(){
        accountAPane.setVisible(false);
        albumsAPane.setVisible(false);
    }
    public void setMovieData(int movieIndex){
            albumNameLabel.setText(results.get(movieIndex).getAlbumName());
            albumAuthorLabel.setText(results.get(movieIndex).getAlbumAuthor());
            albumImg.setImage(new Image(new File(results.get(movieIndex).getAlbumImg()).toURI().toString()));
            descriptionField.setWrapText(true);
            descriptionField.setText(results.get(movieIndex).getAlbumDescription());
            statusLabel.setText(results.get(movieIndex).getStatus());
            releaseDateLabel.setText(String.valueOf(results.get(movieIndex).getOutYear()));
    }
    public void startSearch(){
        AutoCompletionBinding<String> autoCompletionBinding = TextFields.bindAutoCompletion(searchField, albumNamesList);
        autoCompletionBinding.setOnAutoCompleted(event ->
                {
                    setMovieData(albumNamesList.indexOf(searchField.getText()));
                }
                );
    }



    public void onEnterPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            for(String s : albumNamesList){
                if(s.equalsIgnoreCase(searchField.getText())){
                    setMovieData(albumNamesList.indexOf(s));
                }
                else if(keyEvent.getCode()==KeyCode.DOWN && searchField.getText().isEmpty()){
                    searchField.setText(" ");
                }

            }
        }
    }
}
