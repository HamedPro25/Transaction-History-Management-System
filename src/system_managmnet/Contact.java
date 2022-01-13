/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System_managmnet;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

/**
 *
 * @author DELL
 */
public class Contact {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty last_name;
    private final SimpleIntegerProperty telephone_number;
    private final SimpleStringProperty adress;
    private final SimpleStringProperty Dinar_cuurency;
    private final Button button;
    private final CheckBox check;
    AnchorPane parent;

    public Contact(int id, String name, String last_name, String adress, String Dinar_cuurency,int telephone_number) 
    {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.last_name = new SimpleStringProperty(last_name);
        this.adress = new SimpleStringProperty(adress);
        this.Dinar_cuurency = new SimpleStringProperty(Dinar_cuurency);
        this .telephone_number=new SimpleIntegerProperty(telephone_number);
        
        this.button=new Button();
        this.button.setStyle("-fx-background-color: white; ");
        //this.button.setPrefSize(38, 38);
        
        this.check=new CheckBox();
        button.setVisible(true);
        check.setVisible(true);
    }

    public Integer getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getLast_name() {
        return last_name.get();
    }

    public int getTelephone_number() {
        return telephone_number.get();
    }

    public String getAdress() {
        return adress.get();
    }

    public String getDinar_cuurency() {
        return Dinar_cuurency.get();
    }
    public Button getButton() {
        return button;
    }
    public CheckBox getCheck() {
        return check;
    }
    
    
}
