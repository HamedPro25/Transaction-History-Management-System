/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System_managmnet;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class TransitionControleer implements Initializable {
    public String currency="DZ";
    public int transitid=-1,tr=-1,indexee;
    @FXML
    Button close_menu1,close_menu,ajouter;
    @FXML
    ListView<HBox> transacts;
    @FXML
    TextField value,value1;
    @FXML
    Label devi,devi1,dest,dtt,empty;
    @FXML
    RadioButton achat,vendre,achat1,vendre1,dz,dz1,aed,aed1;
    @FXML
    AnchorPane slider,slider1;
    @FXML
    AnchorPane transat;
    @FXML
    Line line;
    @FXML
    AnchorPane panes;
    @FXML
    ComboBox<String> combo;
    List<String[]> r=new ArrayList<>(),c,t;
    List<ItemController4> controls;
    List<Integer> indexe=new ArrayList<>();
    boolean isDz=false,adpane=false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ajouter.setVisible(false);
        slider1.setTranslateX(+465);
        
        
        animation();
        showtransat();
        remplir();
    }
    public void animation()
    {
        close_menu1.setOnMouseClicked(event -> {
           
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider1);
                        
            
            slide.setToX(+465);
            slide.play();
            
            
            
            slider1.setTranslateX(0);
            slide.setOnFinished((ActionEvent e)-> {
                ajouter.setVisible(false);
            });

  
        });
       
    }
   
    public void showtransat()
    {
         try 
         {
            CSVReader reader = new CSVReader(new FileReader("src/System_managmnet/Transaction.csv"));
            t = reader.readAll(); 
            t.forEach(x -> System.out.println(Arrays.toString(x)));
            CSVReader reader2 = new CSVReader(new FileReader("src/System_managmnet/Book1.csv"));
            c = reader2.readAll(); 
            c.forEach(x -> System.out.println(Arrays.toString(x)));
            if(t.isEmpty()) empty.setVisible(true);
            else empty.setVisible(false);
            matching();
            //aedcontacts=getContact("AED",r);
            listShow(r);
           

    }   catch (FileNotFoundException ex) { 
            Logger.getLogger(TransitionControleer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TransitionControleer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int findindex(String index)
    {
        for(int i=0;i<c.size();i++)
            if(c.get(i)[0] == null ? index == null : c.get(i)[0].equals(index)) return i;
        return  -1;
    }
    public void matching()
    {
        for(int i=0;i<t.size();i++)
        {
            int index=findindex(t.get(i)[0]);
            String [] rr1=c.get(index);
            Double d=Double.parseDouble(t.get(i)[3]);
            String dd=String.format("%.2f", d);
            String [] rr={rr1[1],rr1[2],rr1[5],t.get(i)[1],t.get(i)[2],dd,t.get(i)[4],t.get(i)[5]};
            r.add(rr);
        }
    }
     public void listShow(List<String []> rr) throws IOException
    {
        controls=new ArrayList<>();
        for (indexee=0;indexee<rr.size();indexee++) 
        {
            String[] r1=rr.get(indexee);
            FXMLLoader loader = new FXMLLoader( getClass().getResource("Transit.fxml" ));

            HBox pane;
            pane = (HBox) loader.load();
            ItemController4 controller =loader.<ItemController4>getController();
            controller.change_names(r1[0],r1[1],r1[7], r1[2], r1[3], r1[4], r1[5],r1[6],indexee,r1[0].charAt(0));
            controls.add(controller);
           
            pane.getChildren().get(7).setOnMouseClicked((MouseEvent event) -> {
                changeselect();
                controller.changest();
                dtt.setText("Date de transaction: "+r1[3]+" "+ r1[4]);
                if(!controller.clicked) controller.clicked=true;pane.setStyle("-fx-background-color: #4E5669;");
                slider1.setVisible(true);
                transitid=controller.getId();tr=controller.getId();
                String val = String.format("%.2f", Double.parseDouble(r1[5]));
                value1.setText(""+val);
                if("Achat".equals(r1[7])) {achat1.setSelected(true);achatselect1();}
                else {vendre1.setSelected(true);vendselect1();}
                if("DZ".equals(r1[6])) {dz1.setSelected(true); devi1.setText("DZD");dzselect1();}
                else {aed1.setSelected(true);devi1.setText("AED");aedselect1();}
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(slider1);
                slide.setToX(0);
                slide.play();
                ajouter.setVisible(true);
                panes.setTranslateX(0);            
                slider1.setTranslateX(-490);
                slide.setOnFinished((ActionEvent e)-> {
                    ajouter.setVisible(true);
                });
               
            });
            //test.getItems().add(pane);
            transacts.getItems().add(pane);
       
            }
    }
    public void add() throws FileNotFoundException, IOException
    {
        if(combo.getValue()!=null && isDigitt(value.getText()))
        {
            value.setStyle("-fx-border-color:none");
            List<String[]>  rr;
           try (CSVReader reader = new CSVReader(new FileReader("src/System_managmnet/Transaction.csv"))) 
           {
               rr = reader.readAll();
               rr.forEach(x -> System.out.println(Arrays.toString(x)));
               reader.close();
           }
           String type = "Achat";
           if (achat.isSelected()) type=achat.getText();
           if (vendre.isSelected()) type=vendre.getText();
           SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
           Date date = new Date(); 
           SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");  
           Date date2 = new Date();
           if(aed.isSelected()) currency="AED";
           else currency="DZ";
           //System.out.println(formatter.format(date)); 
           String[] result={String.valueOf(combo.getSelectionModel().getSelectedIndex()+1),formatter.format(date),formatter2.format(date2),String.valueOf(value.getText()),currency,type};

           Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setContentText("Voulez-vous vraiment ajouter Cette transaction?");

            Optional<ButtonType> results = alert.showAndWait();
            if (results.get() == ButtonType.OK){
                t.add(result);
                 empty.setVisible(false);
                try(CSVWriter csvWriter = new CSVWriter(new FileWriter("src/System_managmnet/Transaction.csv")))
                {
                    csvWriter.writeAll(t);
                    csvWriter.flush();
                }   
                value.setText("");dz.setSelected(true);vendre.setSelected(true);combo.getSelectionModel().clearSelection();
                dzselect();vendselect();
                transacts.getItems().clear();t.clear();r.clear();c.clear();
                showtransat();
                addNot(false, result);
                
            }
            else
            {
                
            }
          
     
        }
        else
        {
            if(!isDigitt(value.getText()))
            {
                value.setStyle("-fx-border-color:red");
            }
            else
            {
                if(combo.getValue()==null)
                {
                    value.setStyle("-fx-border-color:green");
                    Alert a = new Alert(AlertType.NONE);
                    a.setAlertType(AlertType.INFORMATION);
                    a.setContentText("PLease selectionner un contact");
                   // Alert alert = new Alert(AlertType.CONFIRMATION, "Please slectionner un contact " + selection + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                    a.showAndWait();
                }
            }
        }
    }

    public boolean isDigitt(String text)
    {
        boolean numeric=true;
        Double num = null;
         try {
             num= Double.parseDouble(text);
        } catch (NumberFormatException e) {
            numeric = false;}
        return numeric;
    }
    public void edit() throws IOException 
    {
           
        if(isDigitt(value1.getText()))
        {
            value1.setStyle("-fx-border-color:none");
 
           String type = "Achat",devise = "DZ";
           if (achat1.isSelected()) type=achat1.getText();
           else  type=vendre1.getText();
           if (aed1.isSelected()) devise=aed1.getText();
           else devise="DZ";
           
           //System.out.println(formatter.format(date)); 
           String[] elem={t.get(transitid)[0],t.get(transitid)[1],t.get(transitid)[2],value1.getText(),devise,type};
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setContentText("Voulez-vous Changer Cette transaction ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
              
                t.set(transitid,elem);
                try(CSVWriter csvWriter = new CSVWriter(new FileWriter("src/System_managmnet/Transaction.csv")))
                {
                    csvWriter.writeAll(t);
                    csvWriter.flush();
                }
                transacts.getItems().clear();
                t.clear();c.clear();r.clear();
                showtransat();
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(slider1);

                slide.setToX(+510);
                slide.play();
                addNot(true, elem);
                slider1.setTranslateX(0);
                slide.setOnFinished((ActionEvent e)->{
                    ajouter.setVisible(false);
                });
            

            } else {
                // ... user chose CANCEL or closed the dialog
            }

           
        }
        else
        {
            value.setStyle("-fx-border-color:red");                      
        }
       
        
 
    }
    public void dzselect()
    {
        devi.setText("DZD");
        dz.setStyle("-fx-text-fill:#212d3a;");
        aed.setStyle("-fx-text-fill:#bcbcbe;");
        
    }
    public void aedselect()
    {
        devi.setText("AED");
        aed.setStyle("-fx-text-fill:#212d3a;");
        dz.setStyle("-fx-text-fill:#bcbcbe;");
    }
    public void vendselect()
    {
        dest.setText("Á");
        vendre.setStyle("-fx-text-fill:#212d3a;");
        achat.setStyle("-fx-text-fill:#bcbcbe;");
        
    }
    public void achatselect()
    {
        dest.setText("De");
        achat.setStyle("-fx-text-fill:#212d3a;");
        vendre.setStyle("-fx-text-fill:#bcbcbe;");
    }
     public void dzselect1()
    {
        devi1.setText("DZD");
        dz1.setStyle("-fx-text-fill:#212d3a;");
        aed1.setStyle("-fx-text-fill:#bcbcbe;");
        
    }
    public void aedselect1()
    {
        devi1.setText("AED");
        aed1.setStyle("-fx-text-fill:#212d3a;");
        dz1.setStyle("-fx-text-fill:#bcbcbe;");
    }
    public void vendselect1()
    {
        vendre1.setStyle("-fx-text-fill:#212d3a;");
        achat1.setStyle("-fx-text-fill:#bcbcbe;");
        
    }
    public void achatselect1()
    {
        achat1.setStyle("-fx-text-fill:#212d3a;");
        vendre1.setStyle("-fx-text-fill:#bcbcbe;");
    }


    public void remplir()
    {
        for(int i=0;i<c.size();i++)
        {
            combo.getItems().add(c.get(i)[1]+"  "+c.get(i)[2]);
        }
    }
     public void changeselect()
     {
         if(tr!=-1)
         {
            controls.get(tr).clicked=false;
            controls.get(tr).changest();
            controls.get(tr).itemC.setStyle("-fx-background-color:white");
         }
     }
    
     void supp() throws IOException
    {
        List< String [] > res=new ArrayList<>();
        for(int i=0;i<controls.size();i++)
        {
            if(!controls.get(i).clicked) res.add(r.get(i));
        }
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter("src/System_managmnet/Transaction.csv")))
                {
                    csvWriter.writeAll(res);
                    csvWriter.flush();
                }
        listShow(res);
    }

     public void delete() throws IOException
     {
          Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Warning");
        alert.setContentText("Voulez-vous vraiment supprimer ces contacts");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            supp();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
     }
     public void addNot(boolean edit,String [] res) throws IOException
     {
         String notif="";
         String nom=c.get(Integer.parseInt(res[0]))[1]+" "+c.get(Integer.parseInt(res[0]))[2];
         if(edit)
         {
            notif="Changement de contenu de trnasaction de contact \""+nom+"\" Au:" ;
         }
         else
         {
            notif="Creation nouveau trnasaction de contact "+nom+":";
         }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date(); 
        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");  
        Date date2 = new Date();
        String[] rres={notif,"Type: "+res[5]+"\t Prix: "+res[3]+" "+res[4],formatter.format(date),formatter2.format(date2)};
        List< String []> rrrrr=new ArrayList<>();
        try(CSVReader csvReader = new CSVReader(new FileReader("src/System_managmnet/Notifications.csv")))
        {

            rrrrr=csvReader.readAll();
            csvReader.close();
        }
        rrrrr.add(rres);
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter("src/System_managmnet/Notifications.csv")))
        {
            csvWriter.writeAll(rrrrr);
            csvWriter.flush();
        }
     }
    public void deselect()
    {
        for(int i=0;i<controls.size();i++)
        {
            controls.get(i).clicked=false;
            controls.get(i).changest();
            controls.get(i).itemC.setStyle("-fx-background-color:white");
            
        }
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider1);
        slide.setToX(+465);
        slide.play();
        slider1.setTranslateX(0);
        ajouter.setVisible(false);
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
               transacts.getItems().clear();
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
                        transacts.getItems().clear();
                        listShow(res);
                    }
                    else
                    {
                        transacts.getItems().clear();
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
                            transacts.getItems().clear();
                            listShow(res);
                        }
                        else
                        {
                            transacts.getItems().clear();
                            listShow(r);
                        } 
                    }
                }
            }
        }
        else 
        {
            transacts.getItems().clear();
            listShow(r);
        }
    }*/
}
