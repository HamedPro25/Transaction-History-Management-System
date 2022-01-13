/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System_managmnet;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class Conctacts_veiw_uiController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button delet;
    @FXML
    AnchorPane parent_panel;
    public Image img;
    //ListView<HBox> test;
    @FXML
    ListView<HBox> contacts;
    List<String[]> r;
    List<String[]> dzcont=new ArrayList<>();
    List<String[]> aedcont=new ArrayList<>();
    //List<String[]> aedcont=new ArrayList<>();
    List<Boolean> selected=new ArrayList<>();
    List<ItemController> contrls=new ArrayList<>();;
    List<String> lasnames,names;
    int defcont=1,defsort=1;
    @FXML
    Label dz,aed,tous,tousnb,dznb,aednb,empty;
    int  nbdz=0,nbaed=0;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //contacts.setPannable(true);
        names=new ArrayList<>();
        lasnames=new ArrayList<>();
        contacts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //getNames();
        //getLastNames();
        //java.util.Collections.sort(lasnames);
        //java.util.Collections.sort(names);
        try (CSVReader reader = new CSVReader(new FileReader("src/System_managmnet/Book1.csv"))) 
        {
            r = reader.readAll(); 
            r.forEach(x -> System.out.println(Arrays.toString(x)));
            tousnb.setText(""+r.size());
            if(r.isEmpty()) { empty.setVisible(true); contacts.setVisible(false);}
            else { empty.setVisible(false); contacts.setVisible(true);}
           //aedcontacts=getContact("AED",r);
            listShow(r);
           dznb.setText(""+nbdz);
           aednb.setText(""+nbaed);
            //System.out.println(dzcontactts);
            reader.close();
        } catch (FileNotFoundException ex) { 
            Logger.getLogger(Conctacts_veiw_uiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Conctacts_veiw_uiController.class.getName()).log(Level.SEVERE, null, ex);
        }  
         contactset(r,dzcont,"DZ");
         contactset(r,aedcont,"AED");
    }    
    
    public void new_contact() throws IOException
    {

        FXMLLoader loader = new FXMLLoader( getClass().getResource("Add_Contact_ui.fxml" ));
        AnchorPane pane = (AnchorPane) loader.load();
        parent_panel.getChildren().clear();
        parent_panel.getChildren().add(pane);
    }
    
    public void Go_Profile(int ide) throws IOException
    {
        FXMLLoader loader = new FXMLLoader( getClass().getResource("contact_profil_ui.fxml" ));
        AnchorPane pane = (AnchorPane) loader.load();
        parent_panel.getChildren().clear();
        parent_panel.getChildren().add(pane);
        
        Contact_profil_uiController controller =loader.<Contact_profil_uiController>getController();
        controller.get_id(ide);
    }
    
    public void listShow(List<String []> rr) throws IOException
    {
        for (int i=0;i<rr.size();i++) 
        {
            
            String[] r1=rr.get(i);
            if("DZ".equals(r1[4])) nbdz++;
            else nbaed++;
            FXMLLoader loader = new FXMLLoader( getClass().getResource("item.fxml" ));

            HBox pane = null;
            pane = (HBox) loader.load();
            ItemController controller =loader.<ItemController>getController();
            contrls.add(controller);
            selected.add(false);
            controller.change_names(r1[1], r1[2], r1[3], r1[4], r1[5],r1[1].charAt(0));
            pane.getChildren().get(6).setOnMouseClicked((MouseEvent event) -> {
                //throw new UnsupportedOperationException("Not supported yet.");
                parent_panel.getChildren().clear();
                FXMLLoader loader1 = new FXMLLoader(Conctacts_veiw_uiController.this.getClass().getResource("contact_profil_ui.fxml"));
                AnchorPane pane1 = null;
                try {
                    pane1 = (AnchorPane) loader1.load();
                }catch (IOException ex) {
                    Logger.getLogger(Conctacts_veiw_uiController.class.getName()).log(Level.SEVERE, null, ex);
                }
                parent_panel.getChildren().clear();
                parent_panel.getChildren().add(pane1);
                Contact_profil_uiController controller1 = loader1.<Contact_profil_uiController>getController();
                try {
                    controller1.get_id(Integer.parseInt(r1[0])); //To change body of generated methods, choose Tools | Templates.
                }catch (IOException ex) {
                    Logger.getLogger(Conctacts_veiw_uiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            //test.getItems().add(pane);
            contacts.getItems().add(pane);
       
            }
    }
    public void contactset(List<String []> list,List<String []> list2,String flag)
    {
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i)[4].equals(flag)) {
                list2.add(list.get(i));
                System.out.println("here");}
        }
    }
    public void getNames()
    {
        r.forEach((r1) -> {
            names.add(r1[1]);
        });
    }
    public void getLastNames()
    {
        r.forEach((r1) -> {
            lasnames.add(r1[1]);
        });
    }
    public void tousContact() throws IOException
    {
        if(defcont!=1)
        {
            contacts.getItems().clear();
            listShow(r);
            defcont=1;
            tous.setStyle("-fx-text-fill:#5b6166;fx-font-size:14px;-fx-font-weight: bold;");
            dz.setStyle("-fx-text-fill:#bcbcbe;fx-font-size:12px;-fx-font-weight: normal;");
            aed.setStyle("-fx-text-fill:#bcbcbe;fx-font-size:14px;-fx-font-weight: normal;");
            tousnb.setStyle("-fx-text-fill:#5b6166;fx-font-size:14px;-fx-font-weight: bold;");
            dznb.setStyle("-fx-text-fill:#bcbcbe;fx-font-size:12px;-fx-font-weight: normal;");
            aednb.setStyle("-fx-text-fill:#bcbcbe;fx-font-size:14px;-fx-font-weight: normal;");
        }
    }
    public void dzContact() throws IOException
    {
        if(defcont!=2)
        {
            contacts.getItems().clear();
            listShow(dzcont);
            defcont=2;
            dz.setStyle("-fx-text-fill:#5b6166;fx-font-size:14px;-fx-font-weight: bold;");
            tous.setStyle("-fx-text-fill:#bcbcbe;fx-font-size:12px;-fx-font-weight: normal;");
            aed.setStyle("-fx-text-fill:#bcbcbe;fx-font-size:12px;-fx-font-weight: normal;");
            dznb.setStyle("-fx-text-fill:#5b6166;fx-font-size:14px;-fx-font-weight: bold;");
            tousnb.setStyle("-fx-text-fill:#bcbcbe;fx-font-size:12px;-fx-font-weight: normal;");
            aednb.setStyle("-fx-text-fill:#bcbcbe;fx-font-size:12px;-fx-font-weight: normal;");
        }
    }
    public void aedContact() throws IOException
    {
        if(defcont!=3)
        {
            contacts.getItems().clear();
            listShow(aedcont);
            defcont=3;
            aed.setStyle("-fx-text-fill:#5b6166;fx-font-size:14px;-fx-font-weight: bold;");
            dz.setStyle("-fx-text-fill:#bcbcbe;fx-font-size:12px;-fx-font-weight: normal;");
            tous.setStyle("-fx-text-fill:#bcbcbe;fx-font-size:12px;-fx-font-weight: normal;");
            aednb.setStyle("-fx-text-fill:#5b6166;fx-font-size:14px;-fx-font-weight: bold;");
            dznb.setStyle("-fx-text-fill:#bcbcbe;fx-font-size:12px;-fx-font-weight: normal;");
            tousnb.setStyle("-fx-text-fill:#bcbcbe;fx-font-size:12px;-fx-font-weight: normal;");
        }
    }
 
    public void addselect()
    {
        boolean trv=false;
        for(int i=0;i<contrls.size();i++)
            if(contrls.get(i).clicked) {trv=true;break;}
        if (trv) delet.setDisable(false);
        else delet.setDisable(true);
    }
    void supp() throws IOException
    {
        int nb=0;
        List< String [] > res=new ArrayList<>();
        for(int i=0;i<contrls.size();i++)
        {
            if(!contrls.get(i).clicked) res.add(r.get(i));
            else 
            {
                nb++;
                int id=Integer.parseInt(r.get(i)[0]);
                dele(id);
            }
        }
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter("src/System_managmnet/Book1.csv")))
                {
                    csvWriter.writeAll(res);
                    csvWriter.flush();
                }
        addNot(nb);
        listShow(res);
        
    }
    public void delete() throws IOException
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setContentText("Voulez-vous vraiment supprimer ces contacts avec tous leur transactions");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            supp();
            
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
   /* public void search() throws IOException
    {
        List<String []> res=new ArrayList<>();
        String mot=searchBar.getText();
        if(!mot.isEmpty())
        {
            for(int i=0;i<r.size();i++)
            {
                if(r.get(i)[1].contains(mot)) res.add(r.get(i));
            }
            if(!res.isEmpty())
            {
               contacts.getItems().clear();
                listShow(res);
            }
            else
            {
                if(Character.isUpperCase(mot.charAt(mot.length() - 1)))
                {
                    char[] myNameChars = mot.toCharArray();
                    myNameChars[mot.length() - 1] = Character.toLowerCase(myNameChars[mot.length() - 1]);
                    mot = String.valueOf(myNameChars);
                    for(int i=0;i<r.size();i++)
                    {
                        if(r.get(i)[1].contains(mot)) res.add(r.get(i));
                    }
                    if(!res.isEmpty())
                    {
                        contacts.getItems().clear();
                        listShow(res);
                    }
                    else
                    {
                        contacts.getItems().clear();
                        listShow(r);
                    }
                }
                else
                {
                    if(Character.isLowerCase(mot.charAt(mot.length() - 1)))
                    {
                        char[] myNameChars = mot.toCharArray();
                        myNameChars[mot.length() - 1] = Character.toUpperCase(myNameChars[mot.length() - 1]);
                        mot = String.valueOf(myNameChars);
                        for(int i=0;i<r.size();i++)
                        {
                            if(r.get(i)[1].contains(mot)) res.add(r.get(i));
                        }
                        if(!res.isEmpty())
                        {
                            contacts.getItems().clear();
                            listShow(res);
                        }
                        else
                        {
                            contacts.getItems().clear();
                            listShow(r);
                        } 
                    }
                }
            }
        }
        else 
        {
            contacts.getItems().clear();
            listShow(r);
        }
    }*/
    public void addNot(int nb) throws IOException
     {
         String notif="";
        if(nb==1) notif="Vous avez supprimer un contact";
        else notif="Vous avez supprimer des+"+nb+" contacts";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date(); 
        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");  
        Date date2 = new Date();
        String[] rres={notif," ",formatter.format(date),formatter2.format(date2)};
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
    public void dele(int id) throws IOException
    {
        List<String []> rr,res=new ArrayList<>();
         try (CSVReader reader = new CSVReader(new FileReader("src/System_managmnet/Transaction.csv"))) 
           {
               rr = reader.readAll();
               rr.forEach(x -> System.out.println(Arrays.toString(x)));
           }
        for(int i=0;i<rr.size();i++)
        {
            if(Integer.parseInt(rr.get(i)[0])!=id) res.add(rr.get(i));

        }
        try (CSVWriter writer = new CSVWriter(new FileWriter("src/System_managmnet/Transaction.csv"))) 
           {
               writer.writeAll(res);
               writer.flush();
           }
    }
}

