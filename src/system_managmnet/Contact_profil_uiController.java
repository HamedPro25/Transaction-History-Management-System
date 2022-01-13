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
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class Contact_profil_uiController implements Initializable {
    public int id=1,transitid=-1,tr=-1,index;
    public String currency="DZ";
    @FXML
    ImageView img;
    @FXML
    Label nom,prenom,tel,curr,adress,devi1,devi,empty,dtt;
    @FXML
    Button nouveau_button,close_menu1;
    @FXML
    ListView<HBox> transacts;
    @FXML
    TextField value,value1;
    @FXML
    RadioButton achat,vendre,achat1,vendre1,dz1,aed1,aed,dz;
    @FXML
    AnchorPane slider,slider1,main_slider,third_pane;
    @FXML
    AnchorPane transat;
    @FXML
    Line line;
    @FXML 
    ImageView flag;
    String nomm,prenomm;
    List<String[]> r=new ArrayList<>(),m;
    List<Integer> indexec=new ArrayList<>();
    List<ItemController2> contrls;
    boolean isDz=false,adpane=false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        animation();
        
    }
    public void animation()
    {
       
        transat.setOnMouseClicked(event -> {
            changeselect();
            transitid=-1;
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider1);        
            slide.setToX(-510);
            slide.play();
            slider1.setTranslateX(-510);
            
            TranslateTransition slide2 = new TranslateTransition();
            slide2.setDuration(Duration.seconds(0.4));
            slide2.setNode(slider);        
            slide2.setToX(0);
            slide2.play();
            slider1.setTranslateX(0);
            slide.setOnFinished((ActionEvent e)-> {
                //nouveau_button.setVisible(false);
                transat.setVisible(false);
                line.setVisible(true);
                
            });
        });

        close_menu1.setOnMouseClicked(event -> {
            changeselect();
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider1);
            slide.setToX(-510);
            slide.play();
            slider1.setTranslateX(-510);
            slide.setOnFinished((ActionEvent e)  -> {
                transat.setVisible(false);
            });
            TranslateTransition slide2 = new TranslateTransition();
            slide2.setDuration(Duration.seconds(0.4));
            slide2.setNode(slider);
            slide2.setToX(0);
            slide2.play();
            slider.setTranslateX(0);
            for(int i=0;i<contrls.size();i++)
            {
                contrls.get(i).clicked=false;
                contrls.get(i).changest();
                contrls.get(i).itemC.setStyle("-fx-background-color:white");
            
            }
        });
    }
    public void get_id(int number) throws FileNotFoundException, IOException
    {
        id=number;
        System.out.println(""+id);
        List<String[]> rr;
        String tell=null,adresse=null,curren=null;
        try (CSVReader reader = new CSVReader(new FileReader("src/System_managmnet/Book1.csv"))) 
        {
            rr = reader.readAll();
            rr.forEach(x -> System.out.println(Arrays.toString(x)));
            
            for(int i=0;i<rr.size();i++)
            {
                String[] s =rr.get(i);
                int id_current=Integer.parseInt(s[0]);
                if (id_current==id) {System.out.println("Found");nomm=s[1];prenomm=s[2];tell=s[3];adresse=s[4];curren=s[5];break; }
            }
        String cc="";
        if(!Character.isUpperCase(nomm.charAt(0)))  cc=String.valueOf(nomm.charAt(0)).toUpperCase();
        else cc=String.valueOf(nomm.charAt(0));
        String c="src/icons/Alphabet/"+cc+".png";
        Image images = new Image(new FileInputStream(c));
        img.setImage(images);
            System.out.println(""+nomm);
            nom.setText(""+nomm);
            prenom.setText(""+prenomm);
            adress.setText(""+tell);
            tel.setText(""+curren);
            curr.setText(""+adresse);
            Font fontt = Font.loadFont("file:src/fonts/OpenSans-SemiBold.ttf", 18);
            //nom.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            nom.setFont(fontt);prenom.setFont(fontt);
            Font fonttt = Font.loadFont("file:src/fonts/OpenSans-Regular.ttf", 16);
            adress.setFont(fontt);tel.setFont(fontt);curr.setFont(fontt);
            //prenom.setFont(fontt);
            if("DZ".equals(adresse))
            {
                Image image = new Image(new FileInputStream("src/icons/iconfinder_2634506_algeria_ensign_flag_nation_icon_64px.png"));
                flag.setImage(image);
                isDz=true;
            }
            currency=curren;
        }
        showtransat();
    }
    public void showtransat()
    {
         try (CSVReader reader = new CSVReader(new FileReader("src/System_managmnet/Transaction.csv"))) 
        {
            m = reader.readAll(); 
            m.forEach(x -> System.out.println(Arrays.toString(x)));
            
            //aedcontacts=getContact("AED",r);
           String ii=String.valueOf(id);
            for(int i=0;i<m.size();i++)
           {
               if(m.get(i)[0] == null ? ii == null : m.get(i)[0].equals(ii))
               {
                   r.add(m.get(i)); 
                   indexec.add(i);
               }
            }
            listShow();
            if (r.isEmpty()) empty.setVisible(true);
            else  empty.setVisible(false);
           

    }   catch (FileNotFoundException ex) { 
            Logger.getLogger(Contact_profil_uiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Contact_profil_uiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void listShow() throws IOException
    {
        contrls=new ArrayList<>();
        
        for (index=0;index<r.size();index++) 
        {
            String[] r1=r.get(index);
            FXMLLoader loader = new FXMLLoader( getClass().getResource("item_2.fxml" ));

            HBox pane;
            pane = (HBox) loader.load();
            ItemController2 controller =loader.<ItemController2>getController();
            controller.change_names(r1[5], r1[1], r1[2], r1[3], r1[4],index,nomm.charAt(0));
            contrls.add(controller);
            
            pane.getChildren().get(6).setOnMouseClicked((MouseEvent event) -> {
                changeselect();
                if(!controller.clicked) controller.clicked=true; pane.setStyle("-fx-background-color: #4E5669;");
                transitid=controller.getId();tr=controller.getId();
                controller.changest();
                String val = String.format("%.2f", Double.parseDouble(r1[3]));
                value1.setText(""+val);dtt.setText("Date de transaction :"+r1[1]+"  "+r1[2]);
                if("Achat".equals(r1[5])) {achat1.setSelected(true); achatselect1();}
                else {vendre1.setSelected(true);vendselect1();}
                if("DZ".equals(r1[4])) {dz1.setSelected(true); devi1.setText("DZD"); dzselect1();}
                else {aed1.setSelected(true);devi1.setText("AED");aedselect1();}
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(slider);
                slide.setToX(-510);
                slide.play();
                slider1.setTranslateX(-510);
                slide.setOnFinished((ActionEvent e)-> {
                    line.setVisible(true);
                    transat.setVisible(true);
                });
                      
                TranslateTransition slide2 = new TranslateTransition();
                slide2.setDuration(Duration.seconds(0.4));
                slide2.setNode(slider1);
                slide2.setToX(0);
                slide2.play();
                slider1.setTranslateX(0);
                
            });
            transacts.getItems().add(pane);
       
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
    public void add() throws FileNotFoundException, IOException
    {
        if(isDigitt(value.getText()))
        {
            
            value.setStyle("-fx-border-color:none");
             List<String[]> rr;
            try (CSVReader reader = new CSVReader(new FileReader("src/System_managmnet/Transaction.csv"))) 
            {
                rr = reader.readAll();
                rr.forEach(x -> System.out.println(Arrays.toString(x)));
                reader.close();
            }
            String type = "Achat",devise = "DZ";
            if (achat.isSelected()) type=achat.getText();
            else type=vendre.getText();
            if (dz.isSelected()) devise="DZ";
            else devise="AED";
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
            Date date = new Date(); 
            SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");  
            Date date2 = new Date(); 
            //System.out.println(formatter.format(date)); 
            String[] result={String.valueOf(id),formatter.format(date),formatter2.format(date2),String.valueOf(value.getText()),devise,type};
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setContentText("Voulez-vous vraiment ajouter Cette transaction");

            Optional<ButtonType> results = alert.showAndWait();
            if (results.get() == ButtonType.OK)
            {
                addNot(false, result);
                m.add(result);
                rewrite();
                m.clear();
                r.clear();
                transacts.getItems().clear();
                showtransat();
               
                
                
                empty.setVisible(false);
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(slider);
                slide.setToX(0);
                slide.play();
                slider.setTranslateX(0);
                slide.setOnFinished((ActionEvent e)-> {
                    transat.setVisible(false);
                });
                value.setText("");dz.setSelected(true);vendre.setSelected(true);
            }           
        }
        else 
        {
            value.setStyle("-fx-border-color:red");
        }
    }
    public void edit() throws IOException
    {
        if(isDigitt(value1.getText()))
        {
            value.setStyle("-fx-border-color:none");
            String action,devise;
            if(dz1.isSelected()) devise="DZ";
            else devise="AED";
            if(achat1.isSelected()) action="Achat";
            else action="Vendre";
            String[] elem={r.get(transitid)[0],r.get(transitid)[1],r.get(transitid)[2],value1.getText(),devise,action};
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setContentText("Voulez-vous vraiment modifier Cette transaction");

            Optional<ButtonType> results = alert.showAndWait();
            if (results.get() == ButtonType.OK)
            {
                m.set(indexec.get(transitid), elem);
                r.set(transitid, elem);
                rewrite();
                addNot(true, elem);
                transacts.getItems().clear();
                listShow();
                
                line.setVisible(false);
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(slider1);
                slide.setToX(-510);
                slide.play();
                slide.setOnFinished((ActionEvent e) ->{
                    transat.setVisible(false);
                });
                TranslateTransition slide2 = new TranslateTransition();
                slide2.setDuration(Duration.seconds(0.4));
                slide2.setNode(slider);
                slide2.setToX(0);
                slide2.play();
                slider.setTranslateX(0);

            }
        } 
        else
        {
            value1.setStyle("-fx-border-color:red");
        }
    }
    public void rewrite() throws IOException
    {
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter("src/System_managmnet/Transaction.csv")))
        {
            csvWriter.writeAll(m);
            csvWriter.flush();
        }
    }
     public void changeselect()
     {
         if(tr!=-1)
         {
            contrls.get(tr).clicked=false;
            contrls.get(tr).changest();
            contrls.get(tr).itemC.setStyle("-fx-background-color:white");
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
        vendre.setStyle("-fx-text-fill:#212d3a;");
        achat.setStyle("-fx-text-fill:#bcbcbe;");
        
    }
    public void achatselect()
    {
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
    public void addNot(boolean edit,String [] res) throws IOException
     {
         String notif="";
         String nom=nomm+" "+prenomm;
         if(edit)
         {
            notif="Changement de contenu de trnasaction de contact\""+nom+"\" Au:" ;
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
        for(int i=0;i<contrls.size();i++)
        {
            contrls.get(i).clicked=false;
            contrls.get(i).changest();
            contrls.get(i).itemC.setStyle("-fx-background-color:white");
            
        }
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider1);
        slide.setToX(+465);
        slide.play();
        slider1.setTranslateX(0);
        transat.setVisible(false);
     }
}
