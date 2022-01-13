/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System_managmnet;


import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class NotificationController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @FXML
    Label contacts,transats,dzcontact,dztransat,aedcontact,aedtransat,empty;
    @FXML
    ListView<HBox> notifications;
    List<String []> r,c;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           
            CSVReader reader = new CSVReader(new FileReader("src/System_managmnet/Transaction.csv"));
            CSVReader readers = new CSVReader(new FileReader("src/System_managmnet/Book1.csv"));
            
            //jour=Integer.parseInt(String.valueOf(df.format(dateobj)).split("/")[0]);
            r = reader.readAll();
            c = readers.readAll();
            dzcontact.setText(""+getNbContact("DZ", c));
            aedcontact.setText(""+getNbContact("AED", c));
            dztransat.setText(""+getNbtransat("DZ", r));
            aedtransat.setText(""+getNbtransat("AED", r));
            transats.setText(""+r.size());
            contacts.setText(""+c.size());
            
            // TODO
            showNotif();
        } catch (IOException ex) {
            Logger.getLogger(NotificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    public void showNotif() throws FileNotFoundException, IOException
    {
        try (CSVReader reader = new CSVReader(new FileReader("src/System_managmnet/Notifications.csv"))) 
        {
               r = reader.readAll(); 
               r.forEach(x -> System.out.println(Arrays.toString(x)));
              //aedcontacts=getContact("AED",r);
              if(r.isEmpty()) empty.setVisible(true);
              else empty.setVisible(false);
        } 
        for (int i=r.size()-1;i>=0;i--) 
        {
            String[] r1=r.get(i);
            FXMLLoader loader = new FXMLLoader( getClass().getResource("notif.fxml" ));

            HBox pane = null;
            pane = (HBox) loader.load();
            
            notifications.getItems().add(pane);
            ItemController3 controller1 = loader.<ItemController3>getController();
            controller1.change_names(r1[0], r1[2], r1[3],r1[1]);
        }


 }
    public int getNbContact(String countrey,List<String []> lists)
    {
        int sum=0;
        sum = lists.stream().filter((list) -> (list[4] == null ? countrey == null : list[4].equals(countrey))).map((_item) -> 1).reduce(sum, Integer::sum);    
        return sum;
    }
      public int getNbtransat(String countrey,List<String []> lists)
    {
        int sum=0;
        sum = lists.stream().filter((list) -> (list[4] == null ? countrey == null : list[4].equals(countrey))).map((_item) -> 1).reduce(sum, Integer::sum);    
        return sum;
    }
}