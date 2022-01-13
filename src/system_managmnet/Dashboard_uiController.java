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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class Dashboard_uiController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label transatNb,contactNb,dzprofit,aedprofit,dz,aed,todtransat;
    @FXML
    AnchorPane panet;
    List<String[]> r,r2;
    double dzgrow,aedgrow;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try 
        {
            CSVReader reader = new CSVReader(new FileReader("src/System_managmnet/Book1.csv"));
            CSVReader reader2 = new CSVReader(new FileReader("src/System_managmnet/Transaction.csv"));
            r = reader.readAll();
            r.forEach(x -> System.out.println(Arrays.toString(x)));
            contactNb.setText(""+String.valueOf(r.size()));
            r2 = reader2.readAll();
            r2.forEach(x -> System.out.println(Arrays.toString(x)));
             dz.setText(""+nb("DZ"));
            aed.setText(""+nb("AED"));
            todtransat.setText(""+nbt());
            transatNb.setText(""+String.valueOf(r2.size()));       
            ProfiteMonth();
            
            dz.setText(""+nb("DZ"));
            aed.setText(""+nb("AED"));
            
            
        }catch (FileNotFoundException ex) {    
            Logger.getLogger(Dashboard_uiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dashboard_uiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
            

    }
   public void GOContacts() throws IOException
   {
        panet.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Conctacts_veiw_ui.fxml")); 
        pane.prefWidth(panet.widthProperty().doubleValue());
        pane.prefHeight(panet.heightProperty().doubleValue());
        pane.setStyle("-fx-margin:0px;"+
                "-fx-padding: 0px;\n" +
                "-fx-border-insets: 0px;\n" +
                "-fx-background-insets: 0px;");
       
        panet.getChildren().add(pane);
   }
      public void GOTransaction() throws IOException
   {
        panet.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Transition.fxml")); 
        pane.prefWidth(panet.widthProperty().doubleValue());
        pane.prefHeight(panet.heightProperty().doubleValue());
        pane.setStyle("-fx-margin:0px;"+
                "-fx-padding: 0px;\n" +
                "-fx-border-insets: 0px;\n" +
                "-fx-background-insets: 0px;");
       
        panet.getChildren().add(pane);
   }
    public void GOAddContact() throws IOException
   {
        panet.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Add_Contact_ui.fxml")); 
        pane.prefWidth(panet.widthProperty().doubleValue());
        pane.prefHeight(panet.heightProperty().doubleValue());
        pane.setStyle("-fx-margin:0px;"+
                "-fx-padding: 0px;\n" +
                "-fx-border-insets: 0px;\n" +
                "-fx-background-insets: 0px;");
       
        panet.getChildren().add(pane);
   }
    public void GOStat() throws IOException
   {
        panet.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Stat.fxml")); 
        pane.prefWidth(panet.widthProperty().doubleValue());
        pane.prefHeight(panet.heightProperty().doubleValue());
        pane.setStyle("-fx-margin:0px;"+
                "-fx-padding: 0px;\n" +
                "-fx-border-insets: 0px;\n" +
                "-fx-background-insets: 0px;");
       
        panet.getChildren().add(pane);
   }
    public void ProfiteMonth()
    {
        int jour1,jour2;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = new Date();
        for (int i=0;i<r2.size();i++) 
        {
            String jourrr=r2.get(i)[1];
            String[] sss=jourrr.split("\\/");
            String jourr=sss[0]+"/"+sss[1];
            jour1=Integer.valueOf(sss[0]);
            jour2=jour1;
            String[] r1;
            double d1=dzgrow,d2=aedgrow,d=0;
            int j=i;
            while(jour1==jour2 && i<r2.size())
            {
                r1=r2.get(i);
                System.out.println(r1[1]);
                d=Double.parseDouble(r1[3]);
                
                if("DZ".equals(r1[4]) && "Vendre".equals(r1[5])) 
                {
                    dzgrow=d+dzgrow;}
                if("AED".equals(r1[4])&& "Vendre".equals(r1[5])) 
                {
                    aedgrow=aedgrow+d;
                }
                if("DZ".equals(r1[4]) && "Achat".equals(r1[5])) {
                    //dz.add(r1);
                    //dzachat.add(d);
                    dzgrow=dzgrow-d;}
                if("AED".equals(r1[4])&& "Achat".equals(r1[5])) 
                {
                    aedgrow=aedgrow-d;
                }
                i++;
                if(i<r2.size()){String[] ss=r2.get(i)[1].split("\\/");
                jour2=Integer.parseInt(ss[0]);r1=r2.get(i);}



            }
            if (i!=r2.size()-1) i--;
            if(!isInMonth(df.format(dateobj), jourrr))
            {
                dzgrow=d1;aedgrow=d2;
            }

        }
        String totdz = String.format("%.2f", dzgrow);
        dzprofit.setText(""+totdz);
        String totaed = String.format("%.2f", aedgrow);
        aedprofit.setText(""+totaed);
       
    }
    boolean isInMonth(String date1,String date2)
    {
        boolean res=false;
        String[] dates1=date1.split("\\/");
        String[] dates2=date2.split("\\/");
        if(Integer.parseInt(dates1[0])>=Integer.parseInt(dates2[0]) && Integer.parseInt(dates1[1])==Integer.parseInt(dates2[1]))
        {
           res=true;         
        }
        if(Integer.parseInt(dates1[0])<=Integer.parseInt(dates2[0]) && Integer.parseInt(dates1[1])==(Integer.parseInt(dates2[1]))+1)
        {
           res=true;         
        }
        return res;
    }
    public int nb(String cont)
    {
        int sum=0;
        for(int i=0;i<r.size();i++)
        {
            if(r.get(i)[4] == null ? cont == null : r.get(i)[4].equals(cont)) sum++;
        }
        return sum;
    }
    public int nbt()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date(); 
        int sum=0;
        for(int i=0;i<r2.size();i++)
        {
            if(r2.get(i)[1] == null ? formatter.format(date) == null : r2.get(i)[1].equals(formatter.format(date))) sum++;
        }
        return sum;
    }
}
