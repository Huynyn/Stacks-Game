import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;


/**
 * Extends Pane class.  A root that stores certain things to be stored.  By changing root/pane, program can change "windows'
 *
 * This pane is dedicated to showcasing nodes associated with the game
 *
 * @author (Huy Nguyen)
 * @version (Jan 9 2024)
 */
public class GamePane extends Pane
{
    //text nodes
    private Text txtScore; //shows current game score
    private Text txtHighScore; //shows the high score of the current game  being played 
    private Text txtLost; //tells the player they lost the game 

    //button nodes 
    private Button btnHome; //goes back to screen menu window
    //these ones show when the player loses the game
    private Button btnNext; //to go back to screen menu window... there are two buttons that do this but it makes sense to me
                            //because I want a button that always goes back to start menu and one that clearly gives the
                            //player the option once they lose 
    private Button btnPlayAgain; //click to play the game mode again
    
    //a layout component that stacks nodes vertically
    private VBox vBox; 

    public GamePane()
    {
        //INITIALIZE NODES
        //text ones don't need any parameters because their relevant properties are being set elsewhere
        this.txtScore = new Text();
        this.txtHighScore = new Text(); 
        this.txtLost = new Text(); 
        //buttons have their text set in the parameter
        this.btnHome = new Button("HOME"); 
        this.btnNext = new Button("NEXT");
        this.btnPlayAgain = new Button("PLAY AGAIN");
        //initialize vbox 
        this.vBox = new VBox(); 
        //add all nodes to vbox to stack them vertically 
        this.vBox.getChildren().addAll(this.txtScore,this.txtHighScore,this.txtLost,this.btnPlayAgain, this.btnNext);
        
        //put the vbox in the center of the pane
        //put the vbox in the middle of the screen horizontally wise
        //unfortunately pane does not offer functionality to center nodes (ex: vBox) in the root
        //so this is done manually by getting location of center of screen and shifting by a hardcoded valeu
        this.vBox.setLayoutX(GraphicsInterface.WIDTH / 2 - 50); 
        //shift the vbox down by setting its layoutY property 
        this.vBox.setLayoutY(200);

        //make the text alignment in all text nodes to be centerd
        this.txtScore.setTextAlignment(TextAlignment.CENTER); 
        this.txtHighScore.setTextAlignment(TextAlignment.CENTER); 
        this.txtLost.setTextAlignment(TextAlignment.CENTER); 

        //SET BUTTON FUNCTIONALITIES
        this.btnHome.setOnAction(event -> {
                    GameManager.switchPane(); //switch the "window" when you click home
            }); 

        this.btnNext.setOnAction(event -> {
                    GameManager.switchPane(); //switch the "window" when you click next
            }); 

        this.btnPlayAgain.setOnAction(event -> {
                    GameManager.gameStart((byte)-1); //replay the same gamemode, pass in -1 to lett GameManager know that 
            });                                     //you want to play the same game mode again

        
            
        //ACTIVE LISTENERS THAT CALL THE PLACESLIDER METHOD IN GAMEMANAGER TO PLACE THE SLIDER
        //when a key is pressed 
        this.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.F) //only when this pressed key is the f key
                    {
                        GameManager.placeSlider(); 
                    }
            });

            
        //ADD ALL NODES TO PANE
        this.getChildren().addAll(this.btnHome,this.vBox); 

    }
    
    //SETTERS 
    public void setScore(String strScore)
    {
        this.txtScore.setText("SCORE: " + strScore); 
    }

    public void setHighScore(String strScore)
    {
        this.txtHighScore.setText("HIGHSCORE: " + strScore); 
    }
    
    //make nodes that are related to the losing screen visible
    public void showLosingScreen()
    {
        this.txtLost.setVisible(true); 
        this.btnPlayAgain.setVisible(true); 
        this.btnNext.setVisible(true); 
    }
    
    //hide nodes that are related the losing screen
    public void resetScreen()
    {
        this.txtLost.setVisible(false); 
        this.btnPlayAgain.setVisible(false); 
        this.btnNext.setVisible(false); 
    }

}
