import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * Inherits javaFx application class. This is basically the window of the application and it will display and give functionality to the scenes in the program -- the sole 
 * functionality this class is used for.
 * 
 *
 * @author (Huy Nguyen)
 * @version (Jan 9 2024)
 */
public class GraphicsInterface extends Application
{ 
    //final static variables that define the heigh and width of the window in pixels
    public final static short HEIGHT = 700;
    public final static short WIDTH = 600; 
    private final static String TITLE = "Stacks by Huy Nguyen"; 
    
    //scene of javafx application -> container for stuff to put on window (stage)
    private Scene scene; 
    
    //layout managers for components and nodes
    private StartBorderPane startPaneBorder; //custom pane border for the start menu screen
    private GamePane gamePane; //custum pane for the game screen 
    
    
    //override application default start method to have it have creation of layout componenets, setting the scene, and more 
    //stage is passed in from caller
    @Override
    public void start(Stage stage)
    {
        //intialize layout components 
        this.startPaneBorder = new StartBorderPane();
        this.gamePane = new GamePane(); 
        //intialize scene, passing in the starting root node to be on this scene's scene graph and the scene's width and height 
        this.scene = new Scene(startPaneBorder, WIDTH, HEIGHT);
    
        //set pertinent variables for passed in stage
        stage.setScene(scene); //set the container of the stage
        stage.setResizable(false); //make sure player can't resize the stage 
        stage.setTitle(TITLE); //set the title of the stage 

        //required to completely terminate java virtual machine, otherwise java virtual machine will keep running and javafx application cannot be run again  
        
        //for some reason the java virtual machine keeps running even when clicking the x at the top right. 
        //by default closing the last stage invokes platform.exit() which doesn't work
        //thus, System.exit(0) is used to terminate the java virtual machine
        //integer value of 0 Sytem.exit is simply to indicate that is was an intended normal termination
        stage.setOnCloseRequest(e -> System.exit(0));

        stage.show(); //show the stage
    }
    
    //method switches the layout components of the scene, thereby switching what is displayed
    public void switchPane()
    {
        //check what the current root node is 
        //and change it to the other layout component accordingly 
        if (scene.getRoot().equals(startPaneBorder))
        {
            this.scene.setRoot(gamePane); 
        }
        else
        {
            this.scene.setRoot(startPaneBorder);  
        }
    }
    
    
    //returns an observable list of GamePane's children
    //basically gives the list of nodes in the layout manager, gamePane
    //allows caller to add nodes, remove nodes, and manipulate existing nodees 
    public ObservableList<Node> getGamePaneChildren()
    {
        return this.gamePane.getChildren(); 
    }
    
    //display the losing screen by calling showLosingScreen in gamePane
    public void showLosingGameScreen()
    {
        this.gamePane.showLosingScreen(); 
    }
    
    //reset the game scree by calling resetScreen in gamePane 
    public void resetGameScreen()
    {
        this.gamePane.resetScreen(); 
    }

    //set the highscore by calling setHighScore in gamePane
    public void setHighScore(String strScore)
    {
        this.gamePane.setHighScore(strScore); 
    }
    
    //set the score by calling score in gamePane
    public void setScore(String strScore)
    {
        this.gamePane.setScore(strScore); 
    }
    
    //terminates the java virtual machine 
    public void quit()
    {
        System.exit(0); 
    }

}
