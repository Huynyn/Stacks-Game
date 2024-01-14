import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.*; 





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
    private Text txtScore;
    private Text txtHighScore; 
    private Text txtLost;

    private Button btnHome; 
    private Button btnNext; 
    private Button btnPlayAgain;

    private VBox vBox; 

    
    public GamePane()
    {
        //INITIALIZE NODES
        this.txtScore = new Text(0,50,"Score: 0");
        this.txtHighScore = new Text(0, 100,"Highscore: 0"); 
        this.txtLost = new Text(0, 150, "YOU LOST!!!"); 
        this.btnHome = new Button("HOME"); 
        this.btnNext = new Button("NEXT");
        this.btnPlayAgain = new Button("PLAY AGAIN");

        this.vBox = new VBox(); 
        this.vBox.getChildren().addAll(this.txtScore,this.txtHighScore,this.txtLost,this.btnPlayAgain, this.btnNext); 
        this.vBox.setAlignment(Pos.CENTER); 

        this.vBox.setLayoutY(200);
        
        //create layout of elements
        this.txtScore.setTextAlignment(TextAlignment.CENTER); 
        this.txtHighScore.setTextAlignment(TextAlignment.CENTER); 
        this.txtLost.setTextAlignment(TextAlignment.CENTER); 

        
        //SET BUTTON FUNCTIONALITY
        this.btnHome.setOnAction(event -> {
                    GameManager.switchPane(); 
            }); 

        this.btnNext.setOnAction(event -> {
                    GameManager.switchPane(); 
            }); 

        this.btnPlayAgain.setOnAction(event -> {
                    GameManager.gameStart((byte)-1); 
            }); 

        /*
        this.setOnKeyPressed(e -> {
        System.out.println("keypressed");
        if (e.getCode() == KeyCode.F) {
        System.out.println("The 'A' key was pressed");
        GameManager.placeSlider(); 

        }
        });
         */

        this.setOnMouseClicked(e -> {
                    System.out.println("keypressed");
                    GameManager.placeSlider(); 

            });
        //ADD ALL NODES TO PANE
        this.getChildren().addAll(this.btnHome,this.vBox); 
        this.vBox.setLayoutX(GraphicsInterface.WIDTH / 2 - 50); 
        System.out.println("hello");
    }

    public void setScore(String strScore)
    {
        this.txtScore.setText("SCORE: " + strScore); 
    }

    public void setHighScore(String strScore)
    {
        this.txtHighScore.setText("HIGHSCORE: " + strScore); 
    }

    public void showLosingScreen()
    {
        this.txtLost.setVisible(true); 
        this.btnPlayAgain.setVisible(true); 
        this.btnNext.setVisible(true); 
    }

    public void resetScreen()
    {
        this.txtLost.setVisible(false); 
        this.btnPlayAgain.setVisible(false); 
        this.btnNext.setVisible(false); 
    }

}
