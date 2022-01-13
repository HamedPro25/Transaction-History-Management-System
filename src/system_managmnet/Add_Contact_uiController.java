/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System_managmnet;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
/**
 * FXML Controller class
 *
 * @author DELL
 */
public class Add_Contact_uiController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label idcont;
    @FXML
    TextField nom,prenom,adress,tel;
    @FXML
    RadioButton dzz,aed;
    @FXML
    ImageView flag;
    @FXML
    AnchorPane nomw,telw,big_pane;
    List<String[]> r1;
    String [] result;
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DropShadow e = new DropShadow();
        e.setWidth(20);
        e.setHeight(20);
        e.setOffsetX(10);
        e.setOffsetY(10);
        //e.setRadius(radius);
        //r.setEffect(e);
    } 
    boolean isDigit(String s)
    {
        boolean numeric = false;
        int num;
         try {
             num= Integer.parseInt(s);
             numeric=true;
        } catch (NumberFormatException e) {
            numeric = false;
        }
         return numeric;
    }
    public void save() throws  IOException 
    {
        
        
        if(nom.getText().isEmpty() || !Character.isLetter(nom.getText().charAt(0)))
        {
            nomw.setVisible(true);
        }
        else
        {
            if(!isDigit(tel.getText()))
            {
                telw.setVisible(true);
            }
            else
            {
                nomw.setVisible(false);
                telw.setVisible(false);
                String pr;
                if(prenom.getText().isEmpty()) pr=" <None>";
                else pr=prenom.getText();
                String ad;
                if(adress.getText().isEmpty()) ad=" <None>";
                else ad=adress.getText();
                 
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information");
                alert.setContentText("Voulez-vous vraiment  Ajouter ce contact ?");

                Optional<ButtonType> results = alert.showAndWait();
                if (results.get() == ButtonType.OK)
                {
                
                    try (CSVReader reader = new CSVReader(new FileReader("src/System_managmnet/Book1.csv"))) 
                    {
                        r1 = reader.readAll();
                        r1.forEach(x -> System.out.println(Arrays.toString(x)));

                        reader.close();
                    }
                    String currenc = "DZ";
                    if (dzz.isSelected()) currenc=dzz.getText();
                    else currenc=aed.getText();
                    int max=0;
                    if(!r1.isEmpty())  max=Integer.parseInt(r1.get(r1.size()-1)[0]);
                    String[] resulti={String.valueOf(max+1),nom.getText(),pr,ad,currenc,tel.getText()};
                    r1.add(resulti);
                    try(CSVWriter csvWriter = new CSVWriter(new FileWriter("src/System_managmnet/Book1.csv")))
                    {
                        csvWriter.writeAll(r1);
                        csvWriter.flush();
                    }
                    addNot(resulti);
                   
                    loadContact();
                }
            }
        }
     
    }
    public void dz() throws FileNotFoundException
    {
        Image image = new Image(new FileInputStream("src/icons/iconfinder_2634506_algeria_ensign_flag_nation_icon_64px.png"));
        flag.setImage(image);
        idcont.setText("+213");
    }
    public void aed() throws FileNotFoundException
    {
        Image image = new Image(new FileInputStream("src/icons/iconfinder_2634449_emirates_ensign_flag_nation_icon_64px.png"));
        flag.setImage(image);
        idcont.setText("+971");
    }
    public void loadContact() throws IOException
    {
           
        big_pane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Conctacts_veiw_ui.fxml")); 
        pane.prefWidth(big_pane.widthProperty().doubleValue());
        pane.prefHeight(big_pane.heightProperty().doubleValue());
        pane.setStyle("-fx-margin:0px;"+
                "-fx-padding: 0px;\n" +
                "-fx-border-insets: 0px;\n" +
                "-fx-background-insets: 0px;");
       
        big_pane.getChildren().add(pane);
   
    }
    public void addNot(String [] res) throws IOException
     {
         String notif="";
        notif="Creation d'un nouveau contact:"+res[1]+" "+res[2];
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date(); 
        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");  
        Date date2 = new Date();
        String[] rres={notif,"Adresse"+res[3]+"\tPays: "+res[4]+"\tNumero de telephone"+res[5],formatter.format(date),formatter2.format(date2)};
        List< String []> rrrrr;
        try(CSVReader csvReader = new CSVReader(new FileReader("src/System_managmnet/Notifications.csv")))
                {
                    rrrrr=csvReader.readAll();
                    rrrrr.forEach(x -> System.out.println(Arrays.toString(x)));
                    rrrrr.add(rres);
                    csvReader.close();
                }
        
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter("src/System_managmnet/Notifications.csv")))
        {
            csvWriter.writeAll(rrrrr);
            csvWriter.flush();
        }
     }
}
