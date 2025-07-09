import javafx.stage.Stage;
import javafx.scene.Node;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*; 
import java.io.*;

/**
 * The game essentially runs from this class.  This class is a static class, meaning there is nowhere in the code that is 
 * has been initialized.  There is one gameManager and it manages other classes in program and mediates their interactions between each other.  
 *this is a test
 * @author (Huy Nguyen)
 * @version (Jan 9 2024)
 */
public class GameManager
{
    //final variables that set important values in the program
    private static final byte NUM_STARTING_SLIDERS = 13; //number of starting sliders, basically the height of the starting tower... add one to how tall you really want
    //the starting tower to be because it will shift down one slider once a moving slider is added
    private final static String HIGHSCORE_LOCATION = "scores.txt";  //location of where highscores are saved    

    private static GraphicsInterface graphicsInterface; //class that is the javafx application 
    private static ArrayList<Slider> lstSliders = new ArrayList<Slider>(); //stores sliders
    private static boolean bolSide; //used to store what side the slider comes in from -> true: left, false: right 

    //keeps track of the current gamemeode, each gamemode corresponding to a byte value
    private static byte bytGameMode; //0 easy, 1 normal, 2 medium, 3 hard

    //high scores depending on each score for each gamemode 
    private static short shtHighScoreEasy = 0; //easy
    private static short shtHighScoreNormal = 0; //normal 
    private static short shtHighScoreMedium = 0;  //medium
    private static short shtHighScoreHard = 0; //hard

    //stores the current score of the user 
    private static short shtCurrentScore; 

    //Essentially launches the program 
    public static void launch()
    {
        bolSide = true; //sets side to be true, meaning when the program runs, the first game played, the slider will always come in from the left
        //Slider.setColors(HelperMethods.createColourGradient((short)23)); //set the colour gradient of the Slider class. Parameter can be changed to change
        //how drastically the colour changes each slider.  Highest value: 255
        loadHighScore(); 
        graphicsInterface = new GraphicsInterface(); //intitializes the application class that displays the gui's of the game
        graphicsInterface.start(new Stage()); //call start method in application class to launch the window
    }

    //method switches pane by calling switchPane() method in graphcisInterface
    public static void switchPane()
    {
        graphicsInterface.switchPane(); 
    }
    
    //quits the game by calling the graphics.interface quit method
    public static void quit()
    {
        graphicsInterface.quit(); 
    }

    //method called to start the game 
    //pass in the gamemode as the setup for the game will differ depending on the game mode
    public static void gameStart(byte gameMode)
    {
        if (gameMode == -1) //-1 is passed in when there is no change in gamemode, recall the gameStart method.
        {
            gameStart(bytGameMode);
        }
        else {
            //reset the game before starting the game to make sure the game is reset
            gameReset();

            //set that is saved in the class to passed in game mode
            bytGameMode = gameMode; 

            switch (gameMode) //depending on the gamemode
            {
                    //for game mode easy
                case 0:
                    //create starting tower NUMBER_STARTING_SLIDERS number of times
                    //b starts at 1 because the y location of the slider is derived from a multiple (b) of the heigh of the slider
                    for (byte b = 1; b <= NUM_STARTING_SLIDERS; b++)
                    {
                        lstSliders.add(new EasySlider(b)); //add easy sliders to the list of sliders
                        //add this newly created slider's rectangle node to the screen
                        graphicsInterface.getGamePaneChildren().add(lstSliders.get(b-1).getRectangle()); 

                    }

                    //reset slider duration/reset its speed
                    //during the game, a slider's speed will increase incremently by incrementing the same variable... reset this variable  
                    EasySlider.resetDuration(); 
                    //set the high score displayed on the screen to the highscore associated with this current mode
                    graphicsInterface.setHighScore(shtHighScoreEasy + "");

                    break;
                    //for game mode normal
                case 1: 
                    //create starting tower NUMBER_STARTING_SLIDERS number of times
                    //b starts at 1 because the y location of the slider is derived from a multiple (b) of the heigh of the slider
                    for (byte b = 1; b <= NUM_STARTING_SLIDERS; b++)
                    {
                        lstSliders.add(new NormalSlider(b)); //normal hard sliders to the list of sliders
                        //add this newly created slider's rectangle node to the screen
                        graphicsInterface.getGamePaneChildren().add(lstSliders.get(b-1).getRectangle()); 
                    }

                    //reset slider duration/reset its speed
                    //during the game, a slider's speed will increase incremently by incrementing the same variable... reset this variable 
                    NormalSlider.resetDuration(); 

                    //set the high score displayed on the screen to the highscore associated with this current mode
                    graphicsInterface.setHighScore(shtHighScoreNormal + "");
                    break; 
                    //for game mode medium
                case 2: 
                    //create starting tower NUMBER_STARTING_SLIDERS number of times
                    //b starts at 1 because the y location of the slider is derived from a multiple (b) of the heigh of the slider
                    for (byte b = 1; b <= NUM_STARTING_SLIDERS; b++)
                    {
                        lstSliders.add(new MediumSlider(b)); //add medium sliders to the list of sliders
                        //add this newly created slider's rectangle node to the screen
                        graphicsInterface.getGamePaneChildren().add(lstSliders.get(b-1).getRectangle()); 
                    }

                    //reset slider duration/reset its speed
                    //during the game, a slider's speed will increase incremently by incrementing the same variable... reset this variable 
                    MediumSlider.resetDuration(); 

                    //set the high score displayed on the screen to the highscore associated with this current mode
                    graphicsInterface.setHighScore(shtHighScoreMedium + "");
                    break; 
                    //for game mode hard 
                case 3: 
                    //create starting tower NUMBER_STARTING_SLIDERS number of times 
                    //b starts at 1 because the y location of the slider is derived from a multiple (b) of the heigh of the slider
                    for (byte b = 1; b <= NUM_STARTING_SLIDERS; b++)
                    {
                        lstSliders.add(new HardSlider(b)); //add hard sliders to the list of sliders
                        //add this newly created slider's rectangle node to the screen
                        graphicsInterface.getGamePaneChildren().add(lstSliders.get(b-1).getRectangle()); 
                    }

                    //reset slider duration/reset its speed
                    //during the game, a slider's speed will increase incremently by incrementing the same variable... reset this variable 
                    HardSlider.resetDuration(); 

                    //set the high score displayed on the screen to the highscore associated with this current mode
                    graphicsInterface.setHighScore(shtHighScoreHard + "");
                    break; 
                    //ask teacher if you need default even if you know it's never going to run
            }
            addSlider(); //now add a moving slider to the screen
        }
    }

    //resets the game by basically clearing the stuff that was left behind by the previous running of the game
    private static void gameReset()
    {
        //reset the all sliders which returns their width to the starting width and sets the x location of the previous slider
        Slider.resetSlider(); 
        //resets the score to 0
        shtCurrentScore = 0; 
        //sets the score on the screen back to the recently reset score, 0 
        graphicsInterface.setScore(shtCurrentScore + ""); 

        //keep removing the slider while sliders remain
        while (lstSliders.size() > 0)
        {
            //remove the first slider's rectangle node from the screen
            graphicsInterface.getGamePaneChildren().remove(lstSliders.get(0).getRectangle()); 
            //remove the slider from the list of sliders
            lstSliders.remove(0);
        }

        graphicsInterface.resetGameScreen(); 
    }

    //calls when the game has been lost
    public static void gameLost()
    { 
        //show the losing screen which is basically just showing certain nodes to the screen that let the user to actions after they have lost
        graphicsInterface.showLosingGameScreen();

        bolSide = !bolSide; //changed the side the sider comes in from

        //depending on the game mode
        switch (bytGameMode)
        {
            case 0:
                //check if the score of the player exceeded the score of this gamemode
                if (shtCurrentScore > shtHighScoreEasy) //if so
                {
                    shtHighScoreEasy = shtCurrentScore; //set the a new high sccore for the score of this gamemode
                    saveHighScore(); //save the newly updated highscores the file by calling the method that does this
                }
                break;
            case 1:

                //check if the score of the player exceeded the score of this gamemode
                if (shtCurrentScore > shtHighScoreNormal) //if so
                {
                    shtHighScoreNormal = shtCurrentScore; //set the a new high sccore for the score of this gamemode
                    saveHighScore(); //save the newly updated highscores the file by calling the method that does this
                }
                break;
            case 2:

                //check if the score of the player exceeded the score of this gamemode
                if (shtCurrentScore > shtHighScoreMedium) //if so
                {
                    shtHighScoreMedium = shtCurrentScore; //save the newly updated highscores the file by calling the method that does this
                    saveHighScore(); //save the newly updated highscores the file by calling the method that does this
                }
                break;
            case 3:
                //check if the score of the player exceeded the score of this gamemode
                if (shtCurrentScore > shtHighScoreHard) //if so
                {
                    shtHighScoreHard = shtCurrentScore; //save the newly updated highscores the file by calling the method that does this
                    saveHighScore(); //save the newly updated highscores the file by calling the method that does this
                }
                break;
        }

    }

    //method places a slider onto the screen    
    public static void placeSlider()
    {   
        //call the placeSlider method on the slider currently moving (the latest slider in the list), passing in the side the slider came in from 
        if(lstSliders.get(lstSliders.size() - 1).placeSlider(bolSide, (byte)(NUM_STARTING_SLIDERS))) //placeSlider returns a boolean. if true, slider successfully place
        {
            bolSide = !bolSide; //changed the side the sider comes in from

            shtCurrentScore++; //increase score
            graphicsInterface.setScore(shtCurrentScore + ""); //show increased score on display

            //adds a new slider as the current one has been placed
            addSlider();
        }
        else //slider not successfully placed
        {
            //that means the game was lost, so call gameLost() function 
            gameLost(); 
        }

    }

    //add a *moving* slider from the screen 
    private static void addSlider()
    {
        switch (bytGameMode) //depending on the game mdoe
        {
            case 0: //easy
                //add an easy slider to the screen, passing number of sliders plus 1 to put it one above the exisiting tower and the side it should come in from
                lstSliders.add(new EasySlider((byte)(NUM_STARTING_SLIDERS + 1), bolSide)); 
                break;
            case 1:  //normal
                //add an normal slider to the screen, passing number of sliders plus 1 to put it one above the exisiting tower and the side it should come in from
                lstSliders.add(new NormalSlider((byte)(NUM_STARTING_SLIDERS + 1), bolSide)); 
                break;
            case 2: // medium
                //add an medium slider to the screen, passing number of sliders plus 1 to put it one above the exisiting tower and the side it should come in from
                lstSliders.add(new MediumSlider((byte)(NUM_STARTING_SLIDERS + 1), bolSide)); 
                break;
            case 3: //hard
                //add an hard slider to the screen, passing number of sliders plus 1 to put it one above the exisiting tower and the side it should come in from
                lstSliders.add(new HardSlider((byte)(NUM_STARTING_SLIDERS + 1), bolSide)); 
                break; 
        }
        //add this newly created slider's rectangle to the scren
        graphicsInterface.getGamePaneChildren().add(lstSliders.get(lstSliders.size() - 1).getRectangle());
        //shift the entire tower down so it doesn't go above the screen
        shiftTowerDown(); 
    }

    //method shifts all the sliders on the screen down by one slider
    private static void shiftTowerDown()
    {
        //remove the slider at the bottom of the tower because it's going offscreen now -- no need to waste memory 
        graphicsInterface.getGamePaneChildren().remove(lstSliders.get(0).getRectangle());
        lstSliders.remove(0); 

        //for all the remaining sliders, call their shiftDown method. 
        for (Slider s : lstSliders)
        {
            s.shiftDown(); 
        }
        //graphicsInterface.getGamePaneRoot().remove(1); 
    }

    //save high scores
    private static void saveHighScore()
    {
        //set name of printwriter
        PrintWriter out;

        try {
            //intitialize print writer using file location of scores
            out = new PrintWriter(new FileWriter(HIGHSCORE_LOCATION));

            //have to send them out every time because text file needs to be formatted: score easy, score normal, score medium, score hard
            out.println(shtHighScoreEasy);
            out.println(shtHighScoreNormal);
            out.println(shtHighScoreMedium);
            out.println(shtHighScoreHard);

            //close printwriter   
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Cannot open file for writing");
        } catch (IOException e) {
            System.out.println("Error: Cannot write to file");
        }
    }

    //load the high scores from the fiel
    public static void loadHighScore()
    {
        //set name of scanner
        Scanner in; 
        try {
            //initialize scanner using with text file location of scores
            in = new Scanner(new FileReader(HIGHSCORE_LOCATION));

            //grab highscores using scanner
            shtHighScoreEasy = in.nextShort(); 
            shtHighScoreNormal = in.nextShort(); 
            shtHighScoreMedium = in.nextShort(); 
            shtHighScoreHard = in.nextShort(); 

            //close scanner
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Cannot open file for reading");
        } catch (NoSuchElementException e) {
            System.out.println("Error: EOF encountered, file may be corrupt");
        } catch (IOException e) {
            System.out.println("Error: Cannot read from file");
        }
    }

}