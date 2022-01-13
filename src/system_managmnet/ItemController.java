/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System_managmnet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemController implements Initializable {
    @FXML
    ImageView img;
    @FXML
    private HBox itemC ;
    @FXML
    private Button view;
    @FXML
    private Label nom,prenom,adress,tel,currency;
    public int id=1;
    boolean clicked=false;
    Character cap;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
                itemC.setOnMouseEntered(event -> {
                    itemC.setStyle("-fx-background-color : #CDCDCD");
                });
                itemC.setOnMouseExited(event -> {
                    if(!clicked) itemC.setStyle("-fx-background-color : white");
                    else  itemC.setStyle("-fx-background-color :#4E5669");
                });
                itemC.setOnMousePressed(event -> {
                    clicked=!clicked;
                    if (clicked)  itemC.setStyle("-fx-background-color :  #4E5669;"); 
                    else itemC.setStyle("-fx-background-color : white");
                    
                });
        }

    


    public void change_names(String nom,String prenom,String adress,String curr,String tel,Character cap) throws FileNotFoundException  {
        this.nom.setText(nom);
        this.prenom.setText(prenom);
        this.adress.setText(adress);
        this.currency.setText(curr);
        this.tel.setText(tel);
        String cc="";
        if(!Character.isUpperCase(cap))  cc=String.valueOf(cap).toUpperCase();
        else cc=String.valueOf(cap);
        String c="src/icons/Alphabet/"+cc+".png";
        Image image = new Image(new FileInputStream(c));
        img.setImage(image);
                
        
    }
     
}
