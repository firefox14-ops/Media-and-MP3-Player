
package mediaplayer;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;


public class FXMLDocumentController implements Initializable {
    
    private String path;
    private MediaPlayer mediaPlayer;
    private Media media;
    @FXML
    private MediaView mediaView;
    @FXML
    private Slider progressBar;
    @FXML
    private Slider volume;
    @FXML
    private Label Lb1;
    private File directory;

    private File[] files;

    private ArrayList<File> songs;

    private int songNumber;
    private double i=1;

    @FXML
    private void closeVideo(ActionEvent event){
        System.exit(0);
    }
    
    public void play(ActionEvent event){
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    } 
    public void pause(ActionEvent event){
        mediaPlayer.pause();
    }
    public void stop(ActionEvent event){
        mediaPlayer.stop();
    }
    
    
    
     public void skip10(ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(10)));
    }
    public void back10(ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(-10)));
    }
    
    
     public void chooseFileMethod(ActionEvent event){
        FileChooser fileChooser= new FileChooser(); 
        File file = fileChooser.showOpenDialog(null);
        path=file.toURI().toString();
        mediaPlayer.stop();
        mediaPlayer.dispose();
        if(path !=null){
            Media media=new Media(path);
            mediaPlayer =new MediaPlayer(media);
         
            //mediaPlayer.dispose();
            mediaView.setMediaPlayer(mediaPlayer);
            Lb1.setText(" ");
            DoubleProperty widthProp = mediaView.fitWidthProperty();
            DoubleProperty heightProp = mediaView.fitHeightProperty();
            
            widthProp.bind(Bindings.selectDouble( mediaView.sceneProperty(), "width"));
            heightProp.bind(Bindings.selectDouble( mediaView.sceneProperty(), "height"));
            
            
            
            
            
            
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 progressBar.setValue(newValue.toSeconds());
                }
            });
            
            
            
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
           
             progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
             
             mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration total=media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
             });
             
             
             volume.setValue(mediaPlayer.getVolume()*100);
             volume.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volume.getValue()/100);
                }
             } );
            mediaPlayer.play();
        }
        
    }
    
    
    public void slowRate(ActionEvent event){
        mediaPlayer.setRate(i=i-0.25);
    }
    public void fastForward(ActionEvent event){
        mediaPlayer.setRate(i=i+0.25);
    }
    
    
    public void previousMedia(ActionEvent event) {
                i=1;
		if(songNumber > 0) {


			songNumber--;


			mediaPlayer.stop();
                        mediaPlayer.dispose();

			
			
			media = new Media(songs.get(songNumber).toURI().toString());

			mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.setOnEndOfMedia(new Runnable() {
       public void run() {
         media = new Media(songs.get(songNumber).toURI().toString());

			mediaPlayer = new MediaPlayer(media);
                        playMedia();
       }
   });
                        Lb1.setText(songs.get(songNumber).getName());
                            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 progressBar.setValue(newValue.toSeconds());
                 
                }
            });
                            
            
            
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
           
             progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
             
             mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration total=media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
             });
                    volume.setValue(mediaPlayer.getVolume()*100);
             volume.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volume.getValue()/100);
                }
             } );

			playMedia();

		}

		else {

			songNumber = songs.size() - 1;

			mediaPlayer.stop();
                        mediaPlayer.dispose();
			
			

			media = new Media(songs.get(songNumber).toURI().toString());

			mediaPlayer = new MediaPlayer(media);
                        
                        
                        Lb1.setText(songs.get(songNumber).getName());
                          mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 progressBar.setValue(newValue.toSeconds());
                }
            });
            
            
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
           
             progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
             
             mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration total=media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
             });
                    
                    volume.setValue(mediaPlayer.getVolume()*100);
             volume.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volume.getValue()/100);
                }
             } );
             
			playMedia();

		}
                mediaPlayer.setOnEndOfMedia(new Runnable() {
       public void run() {
         if(songNumber < songs.size() - 1) {

			songNumber++;

			mediaPlayer.stop();
                        mediaPlayer.dispose();

			
			media = new Media(songs.get(songNumber).toURI().toString());

			mediaPlayer = new MediaPlayer(media);
                        
                        Lb1.setText(songs.get(songNumber).getName());
                        
                        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 progressBar.setValue(newValue.toSeconds());
                }
            });
            
            
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
           
             progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
             
             mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration total=media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
             });

                    volume.setValue(mediaPlayer.getVolume()*100);
             volume.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volume.getValue()/100);
                }
             } );

			playMedia();

		}

		else {

			songNumber = 0;

			mediaPlayer.stop();
                        mediaPlayer.dispose();

			media = new Media(songs.get(songNumber).toURI().toString());

			mediaPlayer = new MediaPlayer(media);
                        
                        Lb1.setText(songs.get(songNumber).getName());
                            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 progressBar.setValue(newValue.toSeconds());
                }
            });
            
            
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
           
             progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
             
             mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration total=media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
             });
             
                    volume.setValue(mediaPlayer.getVolume()*100);
             volume.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volume.getValue()/100);
                }
             } );

			playMedia();

		}
       }
   });

	}
    
    
    public void nextMedia(ActionEvent event) {
                
                i=1; 
		if(songNumber < songs.size() - 1) {

			songNumber++;

			mediaPlayer.stop();
                        mediaPlayer.dispose();

			
			media = new Media(songs.get(songNumber).toURI().toString());

			mediaPlayer = new MediaPlayer(media);
                        
                        Lb1.setText(songs.get(songNumber).getName());
                        
                        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 progressBar.setValue(newValue.toSeconds());
                }
            });
            
            
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
           
             progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
             
             mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration total=media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
             });

                    volume.setValue(mediaPlayer.getVolume()*100);
             volume.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volume.getValue()/100);
                }
             } );

			playMedia();

		}

		else {

			songNumber = 0;

			mediaPlayer.stop();
                        mediaPlayer.dispose();

			media = new Media(songs.get(songNumber).toURI().toString());

			mediaPlayer = new MediaPlayer(media);
                        
                        Lb1.setText(songs.get(songNumber).getName());
                            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 progressBar.setValue(newValue.toSeconds());
                }
            });
            
            
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
           
             progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
             
             mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration total=media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
             });
             
                    volume.setValue(mediaPlayer.getVolume()*100);
             volume.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volume.getValue()/100);
                }
             } );

			playMedia();

		}
                mediaPlayer.setOnEndOfMedia(new Runnable() {
       public void run() {
         if(songNumber < songs.size() - 1) {

			songNumber++;

			mediaPlayer.stop();
                        mediaPlayer.dispose();

			
			media = new Media(songs.get(songNumber).toURI().toString());

			mediaPlayer = new MediaPlayer(media);
                        
                        Lb1.setText(songs.get(songNumber).getName());
                        
                        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 progressBar.setValue(newValue.toSeconds());
                }
            });
            
            
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
           
             progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
             
             mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration total=media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
             });

                    volume.setValue(mediaPlayer.getVolume()*100);
             volume.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volume.getValue()/100);
                }
             } );

			playMedia();

		}

		else {

			songNumber = 0;

			mediaPlayer.stop();
                        mediaPlayer.dispose();

			media = new Media(songs.get(songNumber).toURI().toString());

			mediaPlayer = new MediaPlayer(media);
                        
                        Lb1.setText(songs.get(songNumber).getName());
                            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 progressBar.setValue(newValue.toSeconds());
                }
            });
            
            
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
           
             progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
             
             mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration total=media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
             });
             
                    volume.setValue(mediaPlayer.getVolume()*100);
             volume.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volume.getValue()/100);
                }
             } );

			playMedia();

		}
       }
   });

	
    }
    
   
   
   
   
    
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
            songs = new ArrayList<File>();

		directory = new File("music");

		files = directory.listFiles();

		if(files != null) {

			for(File file : files) {

				songs.add(file);

			}



		}

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            
            Lb1.setText(songs.get(songNumber).getName());
            
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 progressBar.setValue(newValue.toSeconds());
                }
            });
            
            
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
           
             progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
             
             mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration total=media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
             });
             
             volume.setValue(mediaPlayer.getVolume()*100);
             volume.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volume.getValue()/100);
                }
             } );
             
             mediaPlayer.setOnEndOfMedia(new Runnable() {
       public void run() {
         if(songNumber < songs.size() - 1) {

			songNumber++;

			mediaPlayer.stop();
                        mediaPlayer.dispose();

			
			media = new Media(songs.get(songNumber).toURI().toString());

			mediaPlayer = new MediaPlayer(media);
                        
                        Lb1.setText(songs.get(songNumber).getName());
                        
                        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 progressBar.setValue(newValue.toSeconds());
                }
            });
            
            
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
           
             progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
             
             mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration total=media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
             });

                    volume.setValue(mediaPlayer.getVolume()*100);
             volume.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volume.getValue()/100);
                }
             } );

			playMedia();

		}

		else {

			songNumber = 0;

			mediaPlayer.stop();
                        mediaPlayer.dispose();

			media = new Media(songs.get(songNumber).toURI().toString());

			mediaPlayer = new MediaPlayer(media);
                        
                        Lb1.setText(songs.get(songNumber).getName());
                            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 progressBar.setValue(newValue.toSeconds());
                }
            });
            
            
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
           
             progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue())); 
                }
            });
             
             mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration total=media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
             });
             
                    volume.setValue(mediaPlayer.getVolume()*100);
             volume.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volume.getValue()/100);
                }
             } );

			playMedia();

		}
       }
   });
    }    

    private void playMedia() {
        mediaPlayer.play();
    }
    
}
