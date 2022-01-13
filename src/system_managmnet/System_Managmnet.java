/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System_managmnet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author DELL
 */
public class System_Managmnet extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        //root.sets
        
        
        Scene scene = new Scene(root);
         stage.resizableProperty().setValue(Boolean.FALSE);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            //stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        
        
       
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public void draged()
    {
        //setManaged(false);
        
    }
    
}
