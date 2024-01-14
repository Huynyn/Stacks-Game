//import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.Group;
import javafx.geometry.*; 
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


/**
 * Extends Pane class.  A root that stores certain things to be stored.  By changing root/pane, program can change "windows'
 *
 * This pane is dedicated to showcasing nodes associated with the start menu
 *
 * @author (Huy Nguyen)
 * @version (Jan 9 2024)
 */
public class StartPane extends BorderPane
{

    private Text txtInstructions; 
    private Text txtTitle; 

    private Label lblTitle; 

    private Button btnEasy, btnNormal, btnMedium, btnHard, btnExit; 
    
    private VBox vboxChooseDifficulty; 

    
    public StartPane()
    {
        //INITIALIZE NODES
        this.btnEasy = new Button("PLAY EASY"); 
        this.btnNormal = new Button("PLAY NORMAL"); 
        this.btnMedium = new Button("PLAY MEDIUM"); 
        this.btnHard = new Button("PLAY HARD"); 
        this.btnExit = new Button("EXIT");

        this.txtTitle = new Text("WELCOME TO STACKS"); 

        this.lblTitle = new Label("WELCOME TO STACKS");

        
        this.vboxChooseDifficulty = new VBox(30, new Text(0, 100, "CHOOSE YOUR DIFFICULTY"), btnEasy, btnNormal, btnMedium, btnHard); 
        // this.vboxTxtInstructions = new VBox(new Text("adsfasdfasdfasdfasdfasdfasdfasfasdfsdfsadfsdfasdfasdfasfasdf YOUR DIFFICULTY")); 

        this.txtInstructions = new Text(0,0,"INSTRUCTIONS:\n----------------------------\nThe goal of the game is to build a tower as high as possible.  Sliders will come in from the left and" + 
        " right sides of the screen.  Click either F on the keyboard or left click on mouse to place a slider onto the existing tower.  Try to time it right so that the slider is right on top of the existing" + 
        " tower beneath it.  Any overhanging edges will be lost and the width of your tower will diminish.  On the left, select a difficultly to play!"); 
        
        this.txtInstructions.setWrappingWidth(300); 

        
        //vboxChooseDifficulty.getChildren().addAll(new Text("CHOOSE YOUR DIFFICULTY"), btnEasy,btnNormal, btnMedium, btnHard);

        this.setMargin(vboxChooseDifficulty, new Insets(50));

        //format borderpane

        this.txtTitle.setTextAlignment(TextAlignment.CENTER); 

        this.setTop(this.txtTitle);
        //this.setTop(lblTitle); 

        this.setAlignment(this.txtTitle, Pos.CENTER);
        this.setMargin(this.txtTitle, new Insets(50));
        
        this.setMargin(btnExit, new Insets(50));

        this.setRight(this.txtInstructions);

        this.setLeft(this.vboxChooseDifficulty);
        this.setBottom(this.btnExit); 
        //this.setPrefHeight(); 

        this.vboxChooseDifficulty.setAlignment(Pos.CENTER);
        //this.vboxTxtInstructions.setAlignment(Pos.CENTER); 

        this.setAlignment(this.txtInstructions, Pos.CENTER_LEFT);
        this.setAlignment(this.btnExit, Pos.TOP_CENTER);
        
        //this.setAlignment(vboxChooseDifficulty, Pos.BOTTOM_LEFT);

        
        
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

        
        //ADD ALL NODES TO PANE
        // this.getChildren().addAll(, lblInstructions, btnPlayNormal, btnPlayRush, btnPlaySmall); 
        System.out.println("hello");
    }


 
}
