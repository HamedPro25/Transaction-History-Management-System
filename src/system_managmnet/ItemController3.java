/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System_managmnet;

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

public class ItemController3 implements Initializable {

    @FXML
    private HBox itemC ;
    @FXML
    private Label content,time,date,content2;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
                itemC.setOnMouseEntered(event -> {
                    itemC.setStyle("-fx-background-color : #CDCDCD");
                });
                itemC.setOnMouseExited(event -> {
                    itemC.setStyle("-fx-background-color : white");
                });
        }

    
    public void change_names(String content,String date,String time,String content2)  {
        this.content.setText(content);
        this.date.setText(date);
        this.time.setText(time);
        this.content2.setText(content2);
    }
}
