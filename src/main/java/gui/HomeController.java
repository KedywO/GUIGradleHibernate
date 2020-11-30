package gui;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

import static java.awt.Color.red;

public class HomeController extends ProjectMethods implements Initializable {
    @FXML
    private Label accountLabel,priceSumLabel,accountLabel2,emailLabel,chartLabel,ordersLabel,cityLabel,panelName,albumNameLabel,albumAuthorLabel,descriptionField,statusLabel,releaseDateLabel,inChartLabel;
    @FXML
    private JFXButton logoutBtn, exitBtn,albumsBtn,changePasswordBtn,newPasswordSaveBtn,accountBtn,addToCartBtn,chartBtn;
    @FXML
    private TextField searchField;
    @FXML
    private AnchorPane accountAPane,albumsAPane,chartAPane;
    @FXML
    private PasswordField confirmNewPasswordField,newPasswordField;
    @FXML
    private ImageView albumImg;
    @FXML
    private TableView<Albums> ordersTable;
    @FXML
    private TableColumn<Albums,String> columnName,columnQuantitySub,columnQuantityAdd,columnDeleteItem;
    @FXML
    private TableColumn<Albums,Integer> columnQuantity,columnPrice;
    private User user;
    private EntityManagerFactory entityManagerFactory;
    private ArrayList<String> albumNamesList;
    private List<Albums> dbAlbumsList,toChartAlbumList = new ArrayList<>();
    private Albums activeAlbum;
    private ObservableList<Albums> dataForTable=null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hideAllPanes();
        EntityManager em = EMF.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Albums> cr =cb.createQuery(Albums.class);
        Root<Albums> albumsRoot = cr.from(Albums.class);
        cr.select(albumsRoot);
        dbAlbumsList = new ArrayList<>();
        dbAlbumsList = em.createQuery(cr).getResultList();
        em.close();
        albumNamesList = new ArrayList<>();
        for(Albums a : dbAlbumsList){
            albumNamesList.add(a.getAlbumName());
        }


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



    //ALBUMS PANE   *************************************
    public void albumsBtnOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        hideAllPanes();
        inChartLabel.setVisible(false);
        albumsAPane.setVisible(true);
        panelName.setText("Albums");
        startSearch();
        setMovieData(new Random().nextInt(dbAlbumsList.size()));
    }

    public void startSearch(){
        AutoCompletionBinding<String> autoCompletionBinding = TextFields.bindAutoCompletion(searchField, albumNamesList);
        autoCompletionBinding.setOnAutoCompleted(event ->
                {
                    setMovieData(albumNamesList.indexOf(searchField.getText()));
                }
        );
    }

    public void setMovieData(int movieIndex){
        albumNameLabel.setText(dbAlbumsList.get(movieIndex).getAlbumName());
        albumAuthorLabel.setText(dbAlbumsList.get(movieIndex).getAlbumAuthor());
        albumImg.setImage(new Image(new File(dbAlbumsList.get(movieIndex).getAlbumImg()).toURI().toString()));
        descriptionField.setWrapText(true);
        descriptionField.setText(dbAlbumsList.get(movieIndex).getAlbumDescription());
        statusLabel.setText(dbAlbumsList.get(movieIndex).getStatus());
        releaseDateLabel.setText(String.valueOf(dbAlbumsList.get(movieIndex).getOutYear()));
        activeAlbum = dbAlbumsList.get(movieIndex);
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

    public void addToCartBtnOnAction(ActionEvent actionEvent){
        System.out.println("DODAJE  "+ activeAlbum);
        inChartLabel.setVisible(true);
        if(!isInChart(activeAlbum)){
            toChartAlbumList.add(activeAlbum);
            inChartLabel.setText("Produkt dodany do koszyka!");
            inChartLabel.setTextFill(javafx.scene.paint.Color.GREEN);

        }else{
            inChartLabel.setText("Produkt jest już w koszyku!");
            inChartLabel.setTextFill(javafx.scene.paint.Color.RED);

        }
    }



    //CHART PANE    ***************************************
    public void chartBtnOnAction(javafx.event.ActionEvent actionEvent){
        hideAllPanes();
        chartAPane.setVisible(true);
        ordersTable.setEditable(false);
        if(toChartAlbumList != null) {
            dataForTable = FXCollections.observableArrayList(toChartAlbumList);
            priceSumLabel.setText(String.valueOf(calcNewSumPrice(dataForTable)+" PLN"));
        }
        for(Albums a : toChartAlbumList){
            a.setAddBtn(new Button());
            a.setSubBtn(new Button());
            a.setDelBtn(new Button());
            a.getDelBtn().setText("X");
            a.getAddBtn().setText("+");
            a.getSubBtn().setText("-");
        }
        for(Albums a : dataForTable)// Adding action for add and sub btn of quantity value
        {
            a.getAddBtn().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(a.getQuantity()<10) {
                        a.setQuantity(a.getQuantity() + 1);
                        priceSumLabel.setText(String.valueOf(calcNewSumPrice(dataForTable)+" PLN"));
                        System.out.println("OBLICZYLEM:   "+ calcNewSumPrice(dataForTable));
                        System.out.println("Lista:   "+ dataForTable);
                        ordersTable.refresh();
                    }
                }
            });
            a.getSubBtn().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(a.getQuantity()>=2) {
                        a.setQuantity(a.getQuantity() - 1);
                        priceSumLabel.setText(String.valueOf(calcNewSumPrice(dataForTable) + " PLN"));
                        System.out.println("OBLICZYLEM:   "+ calcNewSumPrice(dataForTable));
                        System.out.println("Lista:   "+ dataForTable);
                    }
                    ordersTable.refresh();
                }
            });
            a.getDelBtn().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    deleteItemFromChart(a);
                    priceSumLabel.setText(String.valueOf(calcNewSumPrice(dataForTable)+" PLN"));
                    a.setQuantity(1);
                    ordersTable.refresh();
                }
            });
        }
        configureOrdersTable();
        ordersTable.setItems(dataForTable);

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
        chartAPane.setVisible(false);
    }

    public boolean isInChart(Albums a){
        if(toChartAlbumList!=null) {
            for (Albums s : toChartAlbumList) {
                if (a == s) {
                    return true;
                }
            }
            return false;
        }else return false;

    }

    public void deleteItemFromChart(Albums a){
        a.setQuantity(0);
        dataForTable.remove(a);
        toChartAlbumList.remove(a);
    }

    public double calcNewSumPrice(List<Albums> albums){
        double price=0;
        for (Albums a: albums ){
            price += a.getPrice()*a.getQuantity();
            System.out.println("sumuje:  " + a.getAlbumName());
        }
        return price;
    }

    public void configureOrdersTable(){
        columnName.setCellValueFactory(new PropertyValueFactory<Albums,String>("albumName"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Albums,Integer>("price"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<Albums,Integer>("quantity"));
        columnQuantitySub.setCellValueFactory(new PropertyValueFactory<Albums,String>("subBtn"));
        columnQuantityAdd.setCellValueFactory(new PropertyValueFactory<Albums,String>("addBtn"));
        columnDeleteItem.setCellValueFactory(new PropertyValueFactory<Albums,String>("delBtn"));
        columnDeleteItem.setStyle("-fx-alignment: CENTER-RIGHT;");
        columnName.setStyle( "-fx-alignment: CENTER-LEFT;");
        columnPrice.setStyle( "-fx-alignment: CENTER-RIGHT;");
        columnQuantity.setStyle("-fx-alignment: CENTER;");
        columnQuantitySub.setStyle("-fx-alignment: CENTER-RIGHT;");
        ordersTable.setPlaceholder(new Label("Koszyk jest pusty"));
        ordersTable.setSelectionModel(null);
        ordersTable.setTableMenuButtonVisible(false);
        Pane header = (Pane) ordersTable.lookup("TableHeaderRow");
        if (header.isVisible()){
            header.setMaxHeight(0);
            header.setMinHeight(0);
            header.setPrefHeight(0);
            header.setVisible(false);
        }
    }
}
