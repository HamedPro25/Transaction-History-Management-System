/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System_managmnet;

import java.util.Date;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author DELL
 */
public class Transaction {
    private final SimpleIntegerProperty id_contact;
    private final Date transat_day;
    private final SimpleFloatProperty  price;
    private final SimpleStringProperty cuurency;
    private final SimpleStringProperty type;

    public int getId_contact() {
        return id_contact.get();
    }

    public Date getTransat_day() {
        return transat_day;
    }

    public float getPrice() {
        return price.get();
    }

    public String getCuurency() {
        return cuurency.get();
    }
    public String getType() {
        return type.get();
    }

    public Transaction(int id_contact, Date transat_day, float price, String cuurency,String type) {
        this.id_contact = new SimpleIntegerProperty(id_contact);
        this.transat_day = transat_day;
        this.price = new SimpleFloatProperty(price);
        this.cuurency = new SimpleStringProperty(cuurency);
        this.type = new SimpleStringProperty(type);
    }
    
    
}
