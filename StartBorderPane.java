//import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.geometry.*; 
import javafx.scene.layout.VBox;

/**
 * Extends Pane class.  A root that stores certain things to be stored.  By changing root/pane, program can change "windows'
 *
 * This borderpane is dedicated to showcasing nodes associated with the start menu
 *
 * @author (Huy Nguyen)
 * @version (Jan 9 2024)
 */

//border pane divises the screen in top, bottom, centre, right and left, letting me put nodes there
public class StartBorderPane extends BorderPane
{
    //text nodes
    private Text txtInstructions; //intructions of the game
    private Text txtTitle; //title of the game

    //buttons for different difficulties 
    private Button btnEasy, btnNormal, btnMedium, btnHard, btnExit; 

    //vbox that stores the difficulty buttons
    private VBox vboxChooseDifficulty; 

    public StartBorderPane()
    {
        //INITIALIZE NODES
        //buttons
        this.btnEasy = new Button("PLAY EASY"); 
        this.btnNormal = new Button("PLAY NORMAL"); 
        this.btnMedium = new Button("PLAY MEDIUM"); 
        this.btnHard = new Button("PLAY HARD"); 
        this.btnExit = new Button("EXIT");

        //intialize and set nodes of 
        this.vboxChooseDifficulty = new VBox(30, new Text(0, 100, "CHOOSE YOUR DIFFICULTY"), this.btnEasy, this.btnNormal, this.btnMedium, this.btnHard); 

        //set text of text nodes
        this.txtTitle = new Text("WELCOME TO STACKS"); 
        this.txtInstructions = new Text("INSTRUCTIONS:\n----------------------------\nThe goal of the game is to build a tower as high as possible.  Sliders will come in from the left and" + 
            " right sides of the screen.  Click F on the keyboard to place a slider onto the existing tower.  Try to time it right so that the slider is right on top of the existing" + 
            " tower beneath it.  Any overhanging edges will be lost and the width of your tower will diminish.  If you're too slow and the slider passes the tower, you lose.  On the left, select a difficultly to play!"); 

        //set the wrapping of txtInstructions, meaning after 300 pixels, it creates a new line
        this.txtInstructions.setWrappingWidth(300); 

        //add elements to each specific region of the border pane.. center is not used
        this.setTop(this.txtTitle); //put the title at the top
        this.setRight(this.txtInstructions); //put the instructions on the right
        this.setLeft(this.vboxChooseDifficulty); //put vbox of difficulties on the left
        this.setBottom(this.btnExit); //put the exit button on the bottom

        //use set margin functionality to create margins in around passed in node inside of the border pane 
        //create 50 pixel wide buffers around the following nodes: vboxChooseDifficulty, txtTitle, btnExit
        this.setMargin(this.vboxChooseDifficulty, new Insets(50));
        this.setMargin(this.txtTitle, new Insets(50));
        this.setMargin(this.btnExit, new Insets(50));
        //An Insets object is a representation of the borders of a container. It specifies the space that a container must leave at each of its edges

        //centre the nodes in the vbox horizontally and vertically
        this.vboxChooseDifficulty.setAlignment(Pos.CENTER);
        //set the text alignment of the title to themiddle 
        this.txtTitle.setTextAlignment(TextAlignment.CENTER); 

        //set the alignment of nodes inside the borderpane
        this.setAlignment(this.txtInstructions, Pos.CENTER_LEFT);// center instructions vertically,  and pushed to the left 
        this.setAlignment(this.btnExit, Pos.TOP_CENTER); //center the btnExit horizontally and push it to the top
        this.setAlignment(this.txtTitle, Pos.CENTER); //center the title horizontally and vertically 

        //SET BUTTON FUNCTIONALITY
        this.btnEasy.setOnAction(event -> {
                    GameManager.switchPane(); 
                    GameManager.gameStart((byte)0); 
                    //tell gamemanger to start game and set mode
            }); 
        this.btnNormal.setOnAction(event -> {
                    GameManager.switchPane(); 
                    GameManager.gameStart((byte)1); 
                    //tell gamemanger to start game and set mode
            }); 
        this.btnMedium.setOnAction(event -> {
                    GameManager.switchPane(); 
                    GameManager.gameStart((byte)2); 
                    //tell gamemanger to start game and set mode
            }); 
        this.btnHard.setOnAction(event -> {
                    GameManager.switchPane(); 
                    GameManager.gameStart((byte)3); 
                    //tell gamemanger to start game and set mode
            }); 
        this.btnExit.setOnAction(event -> {
                    GameManager.quit();  
                    //tell gamemanger to start game and set mode
            }); 
    }

}
