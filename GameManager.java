import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle; 
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import java.util.Scanner;
import java.util.*; 
import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * The game essentially runs from this class.  This class is a static class, meaning there is nowhere in the code that is 
 * has been initialized.  There is one gameManager and it manages other classes in program and mediates their interactions between each other.  
 *
 * @author (Huy Nguyen)
 * @version (Jan 9 2024)
 */
public class GameManager
{
    private static final byte NUM_STARTING_SLIDERS = 15;
    private final static byte FRAMES_PER_SECOND = 30; 

    private static GraphicsInterface graphicsInterface; 
    private static ArrayList<Slider> lstSliders = new ArrayList<Slider>();
    private final static String HIGHSCORE_LOCATION = "scores.txt"; 
    private static AnimationTimer animationTimer;
    private static Frames frames; 
    public static boolean bolSide; 

    public static byte bytGameMode; //0 easy, 1 normal, 2 medium, 3 hard

    private static short shtHighScoreEasy = 0; 
    private static short shtHighScoreNormal = 0; 
    private static short shtHighScoreMedium = 0; 
    private static short shtHighScoreHard = 0; 

    //create highscores for each type of gamemode
    private static short shtCurrentScore; 

    private static final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    public static long getCurrentThreadCpuTime() {
        return threadMXBean.getCurrentThreadCpuTime();
    }

    public static void launch()
    {
        bolSide = true; 
        graphicsInterface = new GraphicsInterface(); 

        graphicsInterface.start(new Stage());

        setTimer(); 
        //setTimter2(); 

        loadHighScore(); 
        //System.out.println(shtHighScoreEasy); 
    }

    public static void switchPane()
    {
        graphicsInterface.switchPane(); 
    }

    public static void placeSlider()
    {
        animationTimer.stop(); 
        System.out.println("place slider called");
        //TEMPORARY VARIABLES
        short shtX1 = 0;
        short shtX2 = 0; 

        short shtBX1 = (short)lstSliders.get(lstSliders.size() - 2).getRectangle().getLayoutX(); 
        short shtBX2 = (short)(shtBX1 + lstSliders.get(lstSliders.size() - 2).getRectangle().getWidth()); 
        short shtTX1 = (short)lstSliders.get(lstSliders.size() - 1).getRectangle().getLayoutX();  
        short shtTX2 = (short)(shtTX1 + lstSliders.get(lstSliders.size() - 1).getRectangle().getWidth()); 

        //System.out.println("bx1: " + shtBX1);
        //System.out.println("bx2: " + shtBX2);
        //System.out.println("tx1: " + shtTX1);
        //System.out.println("tx2: " + shtTX2);

        if (shtTX2 < shtBX1 || shtTX1 > shtBX2)
        {
            System.out.println("FAILED HERE");
            System.out.println("shtTX2: " + shtTX2 + " < shtBX1" + shtBX1);
            System.out.println("shtTX1: " + shtTX1 + " > shtBX2" + shtBX2);
            gameLost(); 
        }
        else 
        {
            if (shtTX2 < shtBX2)
            {
                //game lost

                shtX1 = shtBX1; 
                shtX2 = shtTX2; 
            }
            else if (shtTX1 > shtBX1)
            {

                shtX1 = shtTX1; 
                shtX2 = shtBX2;

                //System.out.println(shtTX1 + " " + shtBX2);
                //System.out.println(shtX1 + " " + shtX2);
            }
            else
            {
                shtX1 = shtBX1;
                shtX2 = shtBX2; 
            }

            bolSide = !bolSide; 
            lstSliders.get(lstSliders.size() - 1).placeSlider(shtX1, shtX2); 

            shtCurrentScore++; 
            graphicsInterface.setScore(shtCurrentScore + ""); 

            addSlider();
            animationTimer.start(); 
        }

        //animationTimer.start(); 
        //frames.start(FRAMES_PER_SECOND);

        //System.out.println(shtX1 + " " + shtX2);
        //
        //bolSide = !bolSide; 

        /*
        if(lstSliders.get(lstSliders.size() - 1).placeSlider(shtX1, shtX2))
        {
        addSlider();     
        }
        else 
        {
        System.out.println("game lost");
        animationTimer.stop();  
        return false; 
        }
         */
        /*
        lstSliders.get(lstSliders.size() - 1).placeSlider(shtX1, shtX2); 
        addSlider(); 
         */

    }

    public static void pause()
    {
        animationTimer.stop(); 
    }

    private static void setTimer() //MANAGES FRAME RATE (actions that occur x amount of times each second) AND ANIMATION OF GAME
    {       
        final long LNG_INTERVAL = 1000000000/FRAMES_PER_SECOND;// animation timer defaults to nanoseconds -> 1000000000 nanoseconds is 1 second, holds number of nanoseconds needed 

        //to be passes in order to have a frame to run SHT_FRAMESPERSECOND times a second
        animationTimer = new AnimationTimer() { 
            long lngPriorTime = 0;

            @Override
            public void handle(long lngCurrentTime) {
                //MANAGES ACTIONS AT FRAMES_PER_SECOND SPEED 
                if (lngCurrentTime - lngPriorTime >= LNG_INTERVAL) {
                    {
                        //move latest slider across the screen
                        //System.out.println(lstSliders.get(lstSliders.size() - 1).getIfPlaced());
                        //System.out.println("load: " + getCurrentThreadCpuTime());
                        if (!lstSliders.get(lstSliders.size() - 1).getIfPlaced())
                        {
                            System.out.println("Ran");
                            if(!lstSliders.get(lstSliders.size() - 1).shift((short)lstSliders.get(lstSliders.size() - 2).getRectangle().getLayoutX(),(short)(lstSliders.get(lstSliders.size() - 2).getRectangle().getLayoutX() + lstSliders.get(lstSliders.size() - 2).getRectangle().getWidth()), bolSide))
                            {
                                //System.out.println("runned");
                                gameLost(); 
                            };    
                        }

                        //System.out.println("Frames per second"); 
                        lngPriorTime = lngCurrentTime; //set lngPriorTime to lngCurrentTime in order for timer to have to wait another LNG_INTERVAL for lngCurrentTime to be LNG_INTERVAL nanoseconds greator than lngPriorTime                    
                    }
                }
            }
        }; 
    }

    /*
    private static void setTimter2()
    {
    frames = new Frames()
    {
    @Override
    public void action()
    {

    if (!lstSliders.get(lstSliders.size() - 1).getIfPlaced())
    {
    System.out.println("Ran");
    if(!lstSliders.get(lstSliders.size() - 1).shift(bolSide))
    {
    System.out.println("runned");
    gameLost(); 
    };   
    }

    //return true; 
    }
    }; 
    }
     */ 

    public static void gameStart(byte gameMode)
    {
        if (gameMode == -1)
        {
            gameStart(bytGameMode);
        }
        else {
            gameReset();
            bytGameMode = gameMode; 
            switch (gameMode)
            {

                case 0:
                    for (byte b = 1; b <= NUM_STARTING_SLIDERS; b++)
                    {
                        lstSliders.add(new EasySlider(b));
                        graphicsInterface.getGamePaneRoot().add(lstSliders.get(b-1).getRectangle()); 

                    }
                    EasySlider.resetSpeed(); 
                    graphicsInterface.setHighScore(shtHighScoreEasy + "");

                    break; 
                case 1: 
                    for (byte b = 1; b <= NUM_STARTING_SLIDERS; b++)
                    {
                        lstSliders.add(new NormalSlider(b));
                        graphicsInterface.getGamePaneRoot().add(lstSliders.get(b-1).getRectangle()); 

                    }

                    NormalSlider.resetSpeed(); 

                    graphicsInterface.setHighScore(shtHighScoreNormal + "");
                    break; 
                case 2: 
                    for (byte b = 1; b <= NUM_STARTING_SLIDERS; b++)
                    {
                        lstSliders.add(new MediumSlider(b));
                        graphicsInterface.getGamePaneRoot().add(lstSliders.get(b-1).getRectangle()); 

                    }

                    MediumSlider.resetSpeed(); 

                    graphicsInterface.setHighScore(shtHighScoreMedium + "");
                    break; 
                case 3: 
                    for (byte b = 1; b <= NUM_STARTING_SLIDERS; b++)
                    {
                        lstSliders.add(new HardSlider(b));
                        graphicsInterface.getGamePaneRoot().add(lstSliders.get(b-1).getRectangle()); 

                    }

                    HardSlider.resetSpeed(); 

                    graphicsInterface.setHighScore(shtHighScoreHard + "");
                    break; 
                    //ask teacher if you need default even if you know it's never going to run
            }
            animationTimer.start();
            addSlider();
            //frames.start(FRAMES_PER_SECOND); 

        }

    }

    public static void gameLost()
    {
        animationTimer.stop(); 
        //frames.stop(); 
        graphicsInterface.showLosingGameScreen();

        switch (bytGameMode)
        {
            case 0:
                if (shtCurrentScore > shtHighScoreEasy)
                {
                    shtHighScoreEasy = shtCurrentScore; 
                    saveHighScore(); 
                }
                break;
            case 1:
                if (shtCurrentScore > shtHighScoreNormal)
                {
                    shtHighScoreNormal = shtCurrentScore; 
                    saveHighScore(); 
                }
                break;
            case 2:
                if (shtCurrentScore > shtHighScoreMedium)
                {
                    shtHighScoreMedium = shtCurrentScore; 
                    saveHighScore(); 
                }
                break;
            case 3:
                if (shtCurrentScore > shtHighScoreHard)
                {
                    shtHighScoreHard = shtCurrentScore; 
                    saveHighScore(); 
                }
                break;
        }

    }

    private static void gameReset()
    {
        Slider.resetWidth(); 
        shtCurrentScore = 0; 
        graphicsInterface.setScore(0 + ""); 

        while (lstSliders.size() > 0)
        {
            graphicsInterface.getGamePaneRoot().remove(lstSliders.get(0).getRectangle());
            lstSliders.remove(0);
        }

        graphicsInterface.resetGameScreen(); 
    }

    private static void addSlider()
    {
        animationTimer.stop(); 
        System.out.println("SHIFTED DOWN");
        //System.out.println(lstSliders.size()); 

        switch (bytGameMode)
        {
            case 0:
                lstSliders.add(new EasySlider((byte)(NUM_STARTING_SLIDERS + 1), bolSide)); 

                break;
            case 1:
                lstSliders.add(new NormalSlider((byte)(NUM_STARTING_SLIDERS + 1), bolSide)); 

                break;
            case 2:
                lstSliders.add(new MediumSlider((byte)(NUM_STARTING_SLIDERS + 1), bolSide)); 

                break;
            case 3:
                lstSliders.add(new HardSlider((byte)(NUM_STARTING_SLIDERS + 1), bolSide)); 

                break; 
        }

        graphicsInterface.getGamePaneRoot().add(lstSliders.get(lstSliders.size() - 1).getRectangle()); 

        shiftDown(); 
        animationTimer.start();
    }

    private static void shiftDown()
    {
        graphicsInterface.getGamePaneRoot().remove(lstSliders.get(0).getRectangle());
        lstSliders.remove(0); 

        for (Slider s : lstSliders)
        {
            s.shiftDown(); 
        }
        //graphicsInterface.getGamePaneRoot().remove(1); 
    }

    private static void saveHighScore()
    {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(HIGHSCORE_LOCATION));

            //have to send them out every time because text file needs to be formatted: score easy, score normal, score medium, score hard
            out.println(shtHighScoreEasy);
            out.println(shtHighScoreNormal);
            out.println(shtHighScoreMedium);
            out.println(shtHighScoreHard);

            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Cannot open file     for writing");
        } catch (IOException e) {
            System.out.println("Error: Cannot write to     file");
        }
    }

    public static void loadHighScore()
    {
        Scanner in; 
        try {
            in = new Scanner(new FileReader(HIGHSCORE_LOCATION));
            shtHighScoreEasy = in.nextShort(); 
            shtHighScoreNormal = in.nextShort(); 
            shtHighScoreMedium = in.nextShort(); 
            shtHighScoreHard = in.nextShort(); 

            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Cannot open file for reading");
        } catch (NoSuchElementException e) {
            System.out.println("Error: EOF encountered, file may be corrupt");
        } catch (IOException e) {
            System.out.println("Error: Cannot read from file");
        }

    }

    public static void quit()
    {
        graphicsInterface.quit(); 
    }

    /*
    public static void quit()
    {
    graphicsInterfacequit(); 
    }
     */
}