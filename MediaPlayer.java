/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Ankit
 */
public class MediaPlayer extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Media & MP3 Player");
        
        //stage.getIcons().add(new Image(""));
        
        stage.setFullScreenExitHint("Press ESC or Double click to exit the full Screen");
        
        scene.setOnMouseClicked(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent clicked) {
                if(clicked.getClickCount() == 2){
                    if(!stage.isFullScreen()){
                        stage.setFullScreen(true);
                    }else{
                        stage.setFullScreen(false);
                    }
                    
                }
            }
            
        });
        
        stage.setScene(scene);
        stage.show();
    }
    
    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
