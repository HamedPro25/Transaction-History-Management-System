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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LoginController implements Initializable {

    @FXML
    TextField username,username1;
    @FXML
    PasswordField password;
    @FXML
    CheckBox vis;
    @FXML 
    AnchorPane big_pane,us,ps;
    @FXML
    ImageView war1,war2;
    private static final int shadowSize = 50;
    double x,y;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        draggable();
        big_pane.setPadding(new Insets(1,4,4,1));
        //big_pane.setStyle("-fx-border-raduis:25");
    } 
    public void closeup()
    {
        Stage stage = (Stage) username.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
    public void login() throws IOException
    {
        if(vis.isSelected())  password.setText(username1.getText());
        if("Admin".equals(username.getText()) && "Admin".equals(password.getText()))
        {
            us.setStyle("-fx-border-color:#F3F6FB");
            war1.setVisible(false);
            ps.setStyle("-fx-border-color:#F3F6FB");
            war2.setVisible(false);
            Stage st = (Stage) username.getScene().getWindow();
            // do what you have to do
            st.close();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            //root.setStyle("-fx-background-color: white;" +
              //      "-fx-background-radius: 25;"+
                //    "-fx-effect:dropshadow(gaussianBlur, rgba(188, 188, 190,0.8), 5, 0, 0, 0);" );
            
            Stage stage = new Stage();
        //root.sets
            
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.TRANSPARENT);
            //scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
        
        
            stage.resizableProperty().setValue(Boolean.FALSE);
            
            stage.show();
        }
        else{
            if(!"Admin".equals(username.getText()))
            {
                us.setStyle("-fx-border-color:red");
                us.setStyle("-fx-border-radius:25");
                us.setStyle("-fx-border-width:1px");
                war1.setVisible(true);
            }
            else{
                 us.setStyle("-fx-border-color:#F3F6FB");
                 us.setStyle("-fx-border-radius:25");
                 us.setStyle("-fx-border-width:1px");
                 war1.setVisible(false);
            }
            if(!"Admin".equals(password.getText()))
            {
                ps.setStyle("-fx-border-color:red");
                ps.setStyle("-fx-border-radius:25");
                ps.setStyle("-fx-border-width:1px");
                war2.setVisible(true);
            }
            else{
                 ps.setStyle("-fx-border-color:#F3F6FB");
                 ps.setStyle("-fx-border-radius:25");
                 ps.setStyle("-fx-border-width:1px");
                 war2.setVisible(false);
            }
        }
    }
    public void visiblee()
    {
        if (vis.isSelected()) { username1.setText(password.getText());password.setVisible(false);username1.setVisible(true);}
        else {password.setVisible(true);username1.setVisible(false);}

    }
    private void draggable()
    {
       big_pane.setOnMousePressed((event) -> {
           x=event.getSceneX();
           y=event.getSceneY();
       });
       big_pane.setOnMouseDragged((event) -> {
           Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
           stage.setX(event.getScreenX()-x);
           stage.setY(event.getScreenY()-y);
       });
    }
}
