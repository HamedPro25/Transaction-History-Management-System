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
import java.time.LocalDate;
import static java.time.LocalDate.now;
import static java.time.LocalDate.now;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class StatController implements Initializable {

    /**
     * Initializes the controller class.
     */
    List<String[]> r,c,dz,aed;
    Boolean isDz=true;
    List<Double> dzachat,aedachat,dzvendre,aedvendre,dzdiffernce,aeddiffernce,dzdifferncea,aeddifferncea;
    double dzsumachat=0,dzsumvendre=0,aedsumvendre=0,aedsumachat=0,dzgrow=0,aedgrow=0,dzgrowa=0,aedgrowa=0;
    List<String> days,monthsList;
    String[] months={"Jan","Fev","Mars","Apr","Mai","June","Jui","Aout","Sep","Oct","Nov","Dec"};
    @FXML
    LineChart<String, Number> chart;
    @FXML
    LineChart<String, Number> chart1;
    @FXML
    Label contacts,transats,dzcontact,dztransat,aedcontact,aedtransat,totaldz,totalaed;;
    @FXML
    Button mois,annee;
    @FXML
    RadioButton dzdevise,aeddevise;
   boolean month=true,selectdz=true;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        dz=new ArrayList<>();
        aed=new ArrayList<>();
        dz=new ArrayList<>();
        days=new ArrayList<>();
        monthsList=new ArrayList<>();
        dzdiffernce=new ArrayList<>();
        aeddiffernce=new ArrayList<>();
        dzdifferncea=new ArrayList<>();
        aeddifferncea=new ArrayList<>();
      
        try  
        {
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
            ProfiteMonth();
            ProfiteYear();
            drawChart(dzdiffernce,days);
//            dzsumachat=dzachat.stream().mapToDouble(Double::doubleValue).sum();
  //          dzsumvendre=dzvendre.stream().mapToDouble(Double::doubleValue).sum();
    //        aedsumachat=aedachat.stream().mapToDouble(Double::doubleValue).sum();
      //      aedsumvendre=aedvendre.stream().mapToDouble(Double::doubleValue).sum();
        } catch (FileNotFoundException ex) {    
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    public String getLastMonth() {
        LocalDate earlier = now().minusMonths(1);
        String s=String.valueOf(earlier);
        return s;
    }
    public LocalDate getLastYear() {
        LocalDate earlier = now().minusYears(1);
        
        return earlier;
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
    boolean isYear(String date1,String date2)
    {
        boolean res=false;
        String[] dates1=date1.split("\\/");
        String[] dates2=date2.split("\\/");
        if(Integer.parseInt(dates1[0])>=Integer.parseInt(dates2[0]) && Integer.parseInt(dates1[1])==Integer.parseInt(dates2[1]) &&  Integer.parseInt(dates1[2])==Integer.parseInt(dates2[2]))
        {
           res=true;         
        }
        if(Integer.parseInt(dates1[1])>Integer.parseInt(dates2[1]) &&  Integer.parseInt(dates1[2])==Integer.parseInt(dates2[2]))
        {
           res=true;         
        }
        if(Integer.parseInt(dates1[1])==Integer.parseInt(dates2[1]) && Integer.parseInt(dates1[0])<=Integer.parseInt(dates2[0]) && Integer.parseInt(dates1[2])==(Integer.parseInt(dates2[2]))+1)
        {
           res=true;         
        }
        if(Integer.parseInt(dates1[1])<Integer.parseInt(dates2[1])  && Integer.parseInt(dates1[2])==(Integer.parseInt(dates2[2]))+1)
        {
           res=true;         
        }
        return res;
    }
    public void ProfiteMonth()
    {
        int jour1,jour2;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = new Date();
        for (int i=0;i<r.size();i++) 
        {
            String jourrr=r.get(i)[1];
            String[] sss=jourrr.split("\\/");
            String jourr=sss[0]+"/"+sss[1];
            jour1=Integer.valueOf(sss[0]);
            jour2=jour1;
            String[] r1;
            double d1=dzgrow,d2=aedgrow,d=0;
            int j=i;
            while(jour1==jour2 && i<r.size())
            {
                r1=r.get(i);
                System.out.println(r1[1]);
                d=Double.parseDouble(r1[3]);
                
                if("DZ".equals(r1[4]) && "Vendre".equals(r1[5])) 
                {
                    dz.add(r1);
                    //dzvendre.add(d);
                    dzgrow=d+dzgrow;}
                if("AED".equals(r1[4])&& "Vendre".equals(r1[5])) 
                {
                    aed.add(r1);//aedvendre.add(d);
                    aedgrow=aedgrow+d;
                }
                if("DZ".equals(r1[4]) && "Achat".equals(r1[5])) {
                    //dz.add(r1);
                    //dzachat.add(d);
                    dzgrow=dzgrow-d;}
                if("AED".equals(r1[4])&& "Achat".equals(r1[5])) 
                {
                    aed.add(r1);
                    //aedachat.add(d);
                    aedgrow=aedgrow-d;
                }
                i++;
                if(i<r.size()){String[] ss=r.get(i)[1].split("\\/");
                jour2=Integer.parseInt(ss[0]);r1=r.get(i);}



            }
            if (i!=r.size()-1) i--;
            if(isInMonth(df.format(dateobj), jourrr))
            {
                dzdiffernce.add(dzgrow);days.add(jourr);
                aeddiffernce.add(aedgrow);
            }
            else
            {    
                dzgrow=d1;aedgrow=d2;
            }

        }
       
    }
    public void ProfiteYear()
    {
        int jour1,jour2;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = new Date();
        for (int i=0;i<r.size();i++) 
        {
            String jourrr=r.get(i)[1];
            String[] sss=jourrr.split("\\/");
            jour1=Integer.valueOf(sss[1]);
            jour2=jour1;
            String[] r1;
            double d1=dzgrowa,d2=aedgrowa,d=0;
            while(jour1==jour2 && i<r.size())
            {
                r1=r.get(i);
                System.out.println(r1[1]);
                d=Double.parseDouble(r1[3]);
                
                if("DZ".equals(r1[4]) && "Vendre".equals(r1[5])) 
                {
                    dzgrowa=d+dzgrowa;
                }
                if("AED".equals(r1[4])&& "Vendre".equals(r1[5])) 
                {
                    aedgrowa=aedgrowa+d;
                }
                if("DZ".equals(r1[4]) && "Achat".equals(r1[5])) 
                {
                    dzgrowa=dzgrowa-d;
                }
                if("AED".equals(r1[4])&& "Achat".equals(r1[5])) 
                {
                    aedgrowa=aedgrowa-d;
                }
                i++;
                if(i<r.size()){String[] ss=r.get(i)[1].split("\\/");
                jour2=Integer.parseInt(ss[1]);r1=r.get(i);}

            }
            if (i!=r.size()-1) i--;

            if(isYear(df.format(dateobj), jourrr))
            {
                dzdifferncea.add(dzgrowa);monthsList.add(months[jour1]);
                aeddifferncea.add(aedgrowa);
            }
            else {dzgrowa=d1;aedgrowa=d2;}
            

        }

    }
    public void drawChart(List<Double> profit,List<String> days)
    {
        chart.getXAxis().setTickLabelRotation(90);
        XYChart.Series<String, Number> series = new XYChart.Series<String,Number>();
        chart.getXAxis().setAutoRanging(true);
        chart.getYAxis().setAutoRanging(true);
        if(selectdz) chart.setTitle("Profit history(en DZ)");
        else chart.setTitle("Profit history(en AED)");
        chart.getData().clear();
        for(int i=0;i<profit.size();i++)
        {
            
            series.getData().add(new XYChart.Data<>(days.get(i), profit.get(i)));
        }
        //chart.getData().clear();
        //chart.getXAxis()
        chart.getData().add(series);
        result();
        
        
    }
    public void drawChart1(List<Double> profit,List<String> days)
    {
        //chart1.getXAxis().setTickLabelRotation(90);
        XYChart.Series<String, Number> series = new XYChart.Series<String,Number>();
        chart1.getXAxis().setAutoRanging(true);
        chart1.getYAxis().setAutoRanging(true);
        if(selectdz) chart1.setTitle("Profit history(en DZ)");
        else chart1.setTitle("Profit history(en AED)");
        chart1.getData().clear();
        for(int i=0;i<profit.size();i++)
        {
            
            series.getData().add(new XYChart.Data<>(days.get(i), profit.get(i)));
        }
        //chart.getData().clear();
        //chart.getXAxis()
        chart1.getData().add(series);
        result();
        
        
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
    public void selectaed()
    {
        if(selectdz)
        {
            selectdz=false;
            chart.getData().clear();
            if(month)drawChart(aeddiffernce, days);
            else drawChart1(aeddifferncea, monthsList);
            dzdevise.setStyle("-fx-text-fill:#bcbcbe");
            aeddevise.setStyle("-fx-text-fill:#5b6166");
            
        }
    }
    public void selectan()
    {
        if(month)
        {
            chart.setVisible(false);chart1.setVisible(true);
            month=false;
            if(selectdz) drawChart1(dzdifferncea, monthsList);
            else drawChart1(aeddifferncea, monthsList);
            mois.setStyle("-fx-background-color:#bcbcbe");
            annee.setStyle("-fx-background-color:#5b6166");
            result();
            
        }
    }
    public void selectm()
    {
        if(!month)
        {
            chart.setVisible(true);chart1.setVisible(false);
            month=true;
            if(selectdz) drawChart(dzdiffernce, days);
            else drawChart(aeddiffernce, days);
            annee.setStyle("-fx-background-color:#bcbcbe");
            mois.setStyle("-fx-background-color:#5b6166");
            result();
            
        }
    }
    public void selectdz()
    {
        if(!selectdz)
        {
            selectdz=true;
            chart.getData().clear();
            if(month) drawChart(dzdiffernce, days);
            else drawChart1(dzdifferncea, monthsList);
            dzdevise.setStyle("-fx-text-fill:#5b6166");
            aeddevise.setStyle("-fx-text-fill:#bcbcbe");
            
        }
    }
    public void result()
    {
        if(month)
        {
            String totdz = String.format("%.2f", dzgrow);
            totaldz.setText(""+totdz+" DZ");
            String totaed = String.format("%.2f", aedgrow);
            totalaed.setText(""+totaed+" AED");   
        }
        else
        {
            String totdz = String.format("%.2f", dzgrowa);
            totaldz.setText(""+totdz+" DZ");
            String totaed = String.format("%.2f", aedgrowa);
            totalaed.setText(""+totaed+" AED");
        }
    }
}
