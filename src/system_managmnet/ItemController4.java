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

public class ItemController4 implements Initializable {
    boolean clicked=false;
    @FXML
    ImageView img;
    @FXML
    public HBox itemC ;
    @FXML
    private Label nom,time,date,price,tel,type,curren;
    private int id; 
    @FXML
    Button view;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
                
    itemC.setOnMouseEntered(event -> {
                    itemC.setStyle("-fx-background-color : #CDCDCD");
                });
                itemC.setOnMouseExited(event -> {
                    if(!clicked) itemC.setStyle("-fx-background-color : white");
                    else  itemC.setStyle("-fx-background-color :#4E5669");
                });
               
                view.setOnMousePressed(event ->{ 
                    clicked=true;
                    itemC.setStyle("-fx-background-color :  #4E5669;"); 
                    
                    
                });
        }

    
    public void change_names(String nom,String prenom,String type,String tel,String date,String time,String price,String curren,int id,Character cap) throws FileNotFoundException  {
        this.nom.setText(nom+"\n"+prenom);
        this.type.setText(type);
        this.tel.setText(tel);
        this.date.setText(date);
        this.time.setText(time);
        this.price.setText(price);
        this.curren.setText(curren);
        this.id=id;
        String cc="";
        if(!Character.isUpperCase(cap))  cc=String.valueOf(cap).toUpperCase();
        else cc=String.valueOf(cap);
        String c="src/icons/Alphabet/"+cc+".png";
        Image image = new Image(new FileInputStream(c));
        img.setImage(image);
    }
    public int getId() { return this.id;}
    
    public void changest()
    {
        if(clicked)
        {
            nom.setStyle("-fx-text-fill:white");
            type.setStyle("-fx-text-fill:white");
            tel.setStyle("-fx-text-fill:white");
            time.setStyle("-fx-text-fill:white");
            price.setStyle("-fx-text-fill:white");
            curren.setStyle("-fx-text-fill:white");
            date.setStyle("-fx-text-fill:white");
        }
        else{
            nom.setStyle("-fx-text-fill:#4a4b4d");
            type.setStyle("-fx-text-fill:#4a4b4d");
            tel.setStyle("-fx-text-fill:#4a4b4d");
            time.setStyle("-fx-text-fill:#4a4b4d");
            price.setStyle("-fx-text-fill:#4a4b4d");
            curren.setStyle("-fx-text-fill:#4a4b4d");
            date.setStyle("-fx-text-fill:#4a4b4d");
        }
    }
}
