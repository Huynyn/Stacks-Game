
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.application.Platform; 


/**
 * Inherits javaFx application class. This is basically the window of the application and it will display and give functionality to the scenes in the program -- the sole 
 * functionality  this class is used for.
 * 
 *
 * @author (Huy Nguyen)
 * @version (Jan 9 2024)
 */
public class GraphicsInterface extends Application
{ 
    final static short HEIGHT = 800;
    final static short WIDTH = 600; 

    private Scene scene; 
    private StartPane startPane;
    private GamePane gamePane;
    
    public static int counter = 0; 
    @Override
    public void start(Stage stage)
    {
        this.startPane = new StartPane();
        this.gamePane = new GamePane(); 

        this.scene = new Scene(startPane, WIDTH, HEIGHT);

        /*
        this.scene.setOnKeyPressed(e -> {
        System.out.println("keypressed");
        if (e.getCode() == KeyCode.F && scene.getRoot().equals(gamePane)) {
        System.out.println("The 'A' key was pressed");
        GameManager.placeSlider(); 

        }
        });

        this.scene.setOnKeyPressed(e -> {
        System.out.println("keypressed");
        if (e.getCode() == KeyCode.F && scene.getRoot().equals(gamePane)) {
        System.out.println("The 'A' key was pressed");
        GameManager.placeSlider(); 

        }
        });
         */
        
        
        this.scene.setOnKeyPressed(e -> {
            System.out.println("keypressed");
            if (e.getCode() == KeyCode.F) {
                //.pause(); 
                counter++; 
                //System.out.println(counter);
                //System.out.println("The 'A' key was pressed");
                GameManager.placeSlider(); 
               
            }
        });
        
        

        stage.setScene(scene); 
        stage.setResizable(false); 

        System.out.println("here");
        //required to completely terminate java virtual machine, otherwise java virtual machine will keep running and javafx application cannot be run again  
        stage.setOnCloseRequest(event -> {
                    System.exit(0);
                    //Platform.exit(); // Exit the application
            });

        stage.show(); //launch stage
    }

    public void switchPane()
    {
        if (scene.getRoot().equals(startPane))
        {
            this.scene.setRoot(gamePane); 
        }
        else
        {
            this.scene.setRoot(startPane);  
        }
    }

    public ObservableList<Node> getGamePaneRoot()
    {
        return this.gamePane.getChildren(); 
    }

    public void showLosingGameScreen()
    {
        this.gamePane.showLosingScreen(); 
    }

    public void resetGameScreen()
    {
        this.gamePane.resetScreen(); 
    }

    public void setHighScore(String strScore)
    {
        this.gamePane.setHighScore(strScore); 
    }

    public void setScore(String strScore)
    {
        this.gamePane.setScore(strScore); 
    }
    
    public void quit()
    {
        System.exit(0); 
    }
    
    


}
