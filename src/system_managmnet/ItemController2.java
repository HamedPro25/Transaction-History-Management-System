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

public class ItemController2 implements Initializable {
    @FXML
    ImageView img;
    @FXML
    public HBox itemC ;
    @FXML
    private Label price,curren,type,date,time;
    private int id;
    @FXML
    Button view;
    boolean clicked=false;
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

    
      public void change_names(String type,String date,String time,String price,String curr,int id,Character cap) throws FileNotFoundException  {
        this.type.setText(type);
        this.date.setText(date);
        double prix=Double.parseDouble(price);
        this.time.setText(time);
        String s=String.format("%.2f", prix);
        this.price.setText(s);
        this.curren.setText(curr);
        this.id=id;
         String cc="";
        if(!Character.isUpperCase(cap))  cc=String.valueOf(cap).toUpperCase();
        else cc=String.valueOf(cap);
        String c="src/icons/Alphabet/"+cc+".png";
        Image image = new Image(new FileInputStream(c));
        img.setImage(image);
    }
      public int getId() {return this.id;}
        public void changest()
    {
        if(clicked)
        {
            type.setStyle("-fx-text-fill:white");
            time.setStyle("-fx-text-fill:white");
            price.setStyle("-fx-text-fill:white");
            curren.setStyle("-fx-text-fill:white");
            date.setStyle("-fx-text-fill:white");
        }
        else{
            type.setStyle("-fx-text-fill:#4a4b4d");
            time.setStyle("-fx-text-fill:#4a4b4d");
            price.setStyle("-fx-text-fill:#4a4b4d");
            curren.setStyle("-fx-text-fill:#4a4b4d");
            date.setStyle("-fx-text-fill:#4a4b4d");
        }
    }
}
