package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javax.persistence.EntityManager;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;


public class CheckOutController extends ProjectMethods implements Initializable {
    @FXML
    private JFXButton SavaDataBtn,confimDataBtn,exitBtn;
    @FXML
    private Label termsLabel, zipCodeLabel,fNameLabel, lNameLabel, adressLabel, cityLabel,priceLabelCheckout;
    @FXML
    private JFXTextField zipCodeField,fNameField, lNameField, adressField, cityField;
    @FXML
    private JFXCheckBox termsBox;
    @FXML
    private AnchorPane pane, homePane;

    private List<Albums> albumsList;
    private User user;
    private boolean saved;
    public static boolean checkoutDone;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saved = false;
        checkoutDone=false;
        termsLabel.setVisible(false);

    }

    public void SavaDataBtnOnAction(ActionEvent actionEvent){
        if(zipCodeField.getText()!=""&&fNameField.getText()!=""&&lNameField.getText()!=""&&adressField.getText()!=""&&cityField.getText()!=""){
            fNameLabel.setText(fNameField.getText());
            lNameLabel.setText(lNameField.getText());
            adressLabel.setText(adressField.getText());
            cityLabel.setText(cityField.getText());
            zipCodeLabel.setText(zipCodeField.getText());
            saved = true;
        }
    }

    public void confimDataBtnOnAction(ActionEvent actionEvent){
        if(termsBox.isSelected()&&saved == true){
            termsLabel.setVisible(false);
            EntityManager em = EMF.createEntityManager();
            em.getTransaction().begin();
            for(Albums a : albumsList){
                Order order = new Order();
                order = setOrderData(a);
                em.persist(order);
            }
            em.getTransaction().commit();
            em.close();
            checkoutDone = true;
            homePane.setDisable(false);
            closeHandler(actionEvent);
        }else{
            termsLabel.setVisible(true);
        }
    }

    public void exitBtnOnAction(ActionEvent actionEvent){
        homePane.setDisable(false);
        closeHandler(actionEvent);
    }

   /* public void setData(List<Albums> list, double price, User user, AnchorPane pane, HomeController homeController ){
        this.albumsList = list;
        this.user=user;
        this.homePane= pane;
        this.homeController = homeController;
    }

    */

    public Order setOrderData(Albums a){
        Order order = new Order();
        order.setfName(fNameLabel.getText());
        order.setlName(lNameLabel.getText());
        order.setAdres(adressLabel.getText());
        order.setAlbumId(a.getAlbumId());
        order.setCity(cityLabel.getText());
        order.setPrice(a.getQuantity()*a.getPrice());
        order.setZip(zipCodeLabel.getText());
        order.setUserId(user.getId());
        return order;
    }


    public void setData(List<Albums> albums, double price, User user1, AnchorPane pane) {
        this.albumsList = albums;
        this.user=user1;
        this.homePane= pane;
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        priceLabelCheckout.setText(String.valueOf(decimalFormat.format(calcNewSumPrice(albumsList)))+" PLN");
    }

    public double calcNewSumPrice(List<Albums> albums){
        double price=0;
        for (Albums a: albums ){
            price += a.getPrice()*a.getQuantity();
        }
        return price;
    }

}
