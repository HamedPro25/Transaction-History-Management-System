/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System_managmnet;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 *
 * @author DELL
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane change_pane,big_pane;
    @FXML
    private Label contact_lab;
    FXMLLoader fxmlLoader;
    AnchorPane panet;
    @FXML
    HBox drag_pane;
    double mousex=0,mousey=0,x=0,y=0;
    private static final int shadowSize = 50;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        big_pane.setPadding(new Insets(0.5,1.5,1.5,0.5));
        
        
        DropShadow shadow1 = new DropShadow();
        shadow1.setOffsetX(10);
        shadow1.setOffsetY(10);
        //big_pane.setEffect(new DropShadow(20, Color.BLACK));
        draggable();
        
        try {                    
       // URL url2 = getClass().getResource("System_managmnet/Dashboard_ui.fxml");
        //fxmlLoader=new FXMLLoader();
        //fxmlLoader.setLocation(url2);
        //fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        panet= (AnchorPane) FXMLLoader.load(getClass().getResource("Dashboard_ui.fxml")); 
        //panet.prefWidth(change_pane.widthProperty().doubleValue());
        //panet.prefHeight(change_pane.heightProperty().doubleValue());
        
        //panet.setStyle("-fx-margin:0px;"+  "-fx-padding: 0px;\n" +               "-fx-border-insets: 0px;\n" +              "-fx-background-insets: 0px;");
        change_pane.getChildren().add(panet);
        
        //panet.autosize();
    } 
    catch (IOException e) {
    }
    }    

    /**
     *
     */
    public void change_contacts()
    {
        
        System.out.println("done");
        try {                    
        //URL url2 = getClass().getResource("System_managmnet/Conctacts_veiw_ui.fxml");
        //fxmlLoader=new FXMLLoader();
        //fxmlLoader.setLocation(url2);
        //fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        panet.getChildren().clear();
        panet = (AnchorPane) FXMLLoader.load(getClass().getResource("Conctacts_veiw_ui.fxml")); 
        panet.prefWidth(change_pane.widthProperty().doubleValue());
        panet.prefHeight(change_pane.heightProperty().doubleValue());
        panet.setStyle("-fx-margin:0px;"+
                "-fx-padding: 0px;\n" +
                "-fx-border-insets: 0px;\n" +
                "-fx-background-insets: 0px;");
        change_pane.getChildren().clear();///name of pane where you want to put the fxml.s
        change_pane.getChildren().add(panet);
        
        
        //panet.autosize();
        
    } 
    catch (IOException e) {
    }

    }
    public void load_profil(int number) throws IOException
    {
          
            System.out.println("gjrbf");
            //URL url = getClass().getResource("System_managmnet/contact_profil_ui.fxml");
            //FXMLLoader fxmlLoader = new FXMLLoader();
            //fxmlLoader.setLocation(url);
            //fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            //final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contact_profil_ui.fxml"));
                  /*Parent root = (Parent) fxmlLoader.load();

                  Scene nscene = new Scene(root);
                  Stage tStatge = new Stage();
                  tStatge.setScene(nscene);
                  tStatge.show();*/

            panet.getChildren().clear();
            //panet = (AnchorPane) FXMLLoader.load(getClass().getResource("contact_profil_ui.fxml")); 
            FXMLLoader loader=new FXMLLoader(getClass().getResource("contact_profil_ui.fxml"));
           // Contact_profil_uiController scene2Controller = loader.lo.getController();  
            
            change_pane.getChildren().clear();///name of pane where you want to put the fxml.
            change_pane.getChildren().add(panet);
              /*FXMLLoader loader = new FXMLLoader(getClass().getResource("contact_profil_ui.fxml"));
        Parent root = loader.load();
        Contact_profil_uiController scene2Controller = loader.getController();  
        scene2Controller.get_id(number);
            //panet.autosize();*/
        }
    public void closeup()
    {
        Stage stage = (Stage) change_pane.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    private void draggable()
    {
       drag_pane.setOnMousePressed((event) -> {
           x=event.getSceneX();
           y=event.getSceneY();
       });
       drag_pane.setOnMouseDragged((event) -> {
           Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
           stage.setX(event.getScreenX()-x);
           stage.setY(event.getScreenY()-y);
       });
    }
        
    public void statOpen() throws IOException
    {
        panet.getChildren().clear();
        panet = (AnchorPane) FXMLLoader.load(getClass().getResource("Stat.fxml")); 
        panet.prefWidth(change_pane.widthProperty().doubleValue());
        panet.prefHeight(change_pane.heightProperty().doubleValue());
        /*panet.setStyle("-fx-margin:0px;"+
                 "-fx-padding: 0px;\n" +
                 "-fx-border-insets: 0px;\n" +
                 "-fx-background-insets: 0px;");*/
        change_pane.getChildren().clear();///name of pane where you want to put the fxml.s
        change_pane.getChildren().add(panet);
    }
        public void notifOpen() throws IOException
    {
        panet.getChildren().clear();
        panet = (AnchorPane) FXMLLoader.load(getClass().getResource("Notification.fxml"));
        /*panet.setStyle("-fx-margin:0px;"+
                 "-fx-padding: 0px;\n" +
                 "-fx-border-insets: 0px;\n" +
                 "-fx-background-insets: 0px;");*/
        change_pane.getChildren().clear();///name of pane where you want to put the fxml.s
        change_pane.getChildren().add(panet);
    }
    public void transitOpen() throws IOException
    {
        panet.getChildren().clear();
        panet = (AnchorPane) FXMLLoader.load(getClass().getResource("Transition.fxml"));
        /*panet.setStyle("-fx-margin:0px;"+
                 "-fx-padding: 0px;\n" +
                 "-fx-border-insets: 0px;\n" +
                 "-fx-background-insets: 0px;");*/
        change_pane.getChildren().clear();///name of pane where you want to put the fxml.s
        change_pane.getChildren().add(panet);
    }
        public void dashOpen() throws IOException
    {
        panet.getChildren().clear();
        panet = (AnchorPane) FXMLLoader.load(getClass().getResource("Dashboard_ui.fxml"));
        /*panet.setStyle("-fx-margin:0px;"+
                 "-fx-padding: 0px;\n" +
                 "-fx-border-insets: 0px;\n" +
                 "-fx-background-insets: 0px;");*/
        change_pane.getChildren().clear();///name of pane where you want to put the fxml.s
        change_pane.getChildren().add(panet);
    }
}
        
    

