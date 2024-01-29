import javafx.scene.shape.Rectangle; 
import javafx.scene.paint.Color;
import javafx.util.Duration; 
import javafx.animation.Timeline; 
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;

/**
 * The objective of the game is to build a tower from sliding in rectangles that come in from the sides of the screen.
 * This class is the those rectangles. 
 *
 * @author (Huy Nguyen)
 * @version (Jan 9 2024)
 */
public abstract class Slider
{
    //STATIC FNIAL VARIABLES
    private static final short HEIGHT = 30;//HEIGH IN PIXELS OF SLIDER. Could be stored in a byte but this allows the slider to be thick if the programmmer wants it to be
    private static final short STARTING_WIDTH = 100; //WIDTH IN PIXELS OF SLIDER. Again, could be a byte but this allows customizability if it's 
    private static final short shtMidwayPoint = GraphicsInterface.WIDTH / 2; //pixel location of halfway horizontally acorss the screen  

    //array of colours that is used for gradient of tower 
    //private static Color[] arr_colors; 
    //tracks which index of arr_colors the latest slider is
    private static short shtColour = 0; //normal application shouldn't exceed more than what could be stored in a byte.  
    //but because there could be more than 1000 colours depending on the what value
    //is hardcoded (found in gamemanager) that creates the array of colours

    //static value that keeps track of the width of the last placed slider
    //default is the starting width because at the start the width of the last placed slider is the starting width 
    //by default this is just the startind width of the slider class
    private static short shtWidth = STARTING_WIDTH; 

    private Timeline timeline; //allows of custom animation to be created by setting keyframe(s) wherein the timeline has to reach keyframe(s) in a given duration
    private KeyFrame kyfFinalFrame; //the keyframe (state of given) in which the timeline has to reach over duration.    

    private static short shtDuration; //duration of timeline.  shorter duration means the slider will go across the screen faster 
    private static short shtBX1; //x location of last slider placed on the tower.  B for bottom slider and X1 for 1st side from the left (x value of top right corner)
    //place slider elaborated on naming scheme more

    //rectangle node of slider that appears on screen
    private Rectangle rectangle; 

    //CONSTRUCTOR
    //pass in a byte that defines the y value of the slider, as it calculated using the product of its height and B
    public Slider(byte b)
    {
        this.rectangle = new Rectangle();  //create new rectangle
        this.rectangle.setHeight(HEIGHT); //set height of rectangle to give height
        this.rectangle.setLayoutY(GraphicsInterface.HEIGHT - (b * HEIGHT)); //LayoutY property starts from top of window (value 0) to bottom (value > 0), thus 
        //layout y value must be set from taking the height of window (value > 0) and subtracting
        //multiples of HEIGHT. 
        this.rectangle.setWidth(shtWidth); //set width of slider 

        //check if the index of colour will exceeded bounds of array if you add one to it
        //being greater than .length only occurs if the programmer hardcoded a value of shtColor (starting index) out of bounds 
        /*
        if (shtColour >= arr_colors.length -1)
        {
            //so reset back to zero
            shtColour = 0; 
        }
        else 
        {
            //increment the colour so next slider has the next colour in the array of colours
            shtColour++;   
        }
        */

        //set the colour of the rectangle 
        //this.rectangle.setFill(arr_colors[shtColour]); 
    }

    //method places the rectangle in the middle of the screen
    protected void addRectangleToScreen()
    {
        //since layoutX property is defined at the top right corner of node, you have to shift it over by half its width to really put it in the middle -> -(shtWidth / 2)
        this.rectangle.setLayoutX(shtMidwayPoint - (shtWidth / 2));
    }

    //method places the rectangle on the side of the screen depending on the passed in boolean
    //if true, put on left side of the screen
    //if false, put on right side of the screen
    protected void addRectangleToScreen(boolean bolSide)
    {
        if (bolSide)
        {
            this.rectangle.setLayoutX(0);
        }
        else 
        {
            //shift leftwards by its width because otherwise it would go offscreen
            //because layoutX property is defined at the top right corner of node
            this.rectangle.setLayoutX(GraphicsInterface.WIDTH - shtWidth);
        } 
        //once the rectangle has been put on one side
        //start its movement 
        animate(bolSide); 
    }

    public void animate(boolean bolSide)
    {
        this.timeline = new Timeline(); //create timeline class
        this.timeline.setOnFinished((e) -> GameManager.gameLost()); //when the timeline has finished, the player missed their chance to place it, so game lost

        if (bolSide) //left side
        {
            this.kyfFinalFrame = new KeyFrame(Duration.millis(shtDuration), new KeyValue(this.rectangle.layoutXProperty(), shtMidwayPoint + (STARTING_WIDTH/2))); 

            //set the final key frame to occur after duration in timeline
            //sets the keyframes keyvalue(s) -> what occurs at that keyframe by defining the property to be manipulated, and its value at keyvalue
            //in this case, set the time until keyframe to be shtDuration and the keyvalue of the keyframe to manipulate the LayouXProperty of rectangle to half width of slider past halfway on the screen
            //this means the slider has just gone past the tower at keyframe
        }
        else //right side
        {
            this.kyfFinalFrame = new KeyFrame(Duration.millis(shtDuration), new KeyValue(this.rectangle.layoutXProperty(), shtMidwayPoint - (STARTING_WIDTH/2 + shtWidth))); 

            //set the final key frame to occur after duration in timeline
            //sets the keyframes keyvalue(s) -> what occurs at that keyframe by defining the property to be manipulated, and its value at keyvalue
            //in this case, set the time until keyframe to be shtDuration and the keyvalue of the keyframe to manipulate the LayouXProperty of rectangle to half width of slider before halfway on the screen
            //this means the slider has just gone past the tower at keyframe
        }

        //add keyframe to timeline 
        //timeline will manipulate nodes as defined in keyframe until they reach the keyvalue of keyframe
        timeline.getKeyFrames().add(kyfFinalFrame);

        //play the animation
        timeline.play();
    }

    //resets the slider's starting values
    public static void resetSlider()
    {
        //sets width back to starting width
        shtWidth = STARTING_WIDTH; 
        //sets x location of previous slider back to what it would be if a slider of width starting width were placed in the middle of the screen
        shtBX1 = shtMidwayPoint - STARTING_WIDTH/2; 
    }

    //shift slider down by its height
    public void shiftDown()
    {
        //add HEIGHT to rectangle's current layoutY property to shift it down by HEIGHT amounts of pixels
        this.rectangle.setLayoutY(this.rectangle.getLayoutY() + HEIGHT); 
    }

    //called when slider is attempted to be placed
    //returns false if player missed the tower and placed the slider too early
    //returns true if the player successfully placed the slider on the tower
    public boolean placeSlider(boolean bolSide, byte b)
    {
        //right away, call the pause method in the slider that is being placed
        //want to make sure that the animation stop right away so there is no difference in time between
        //calling placeSlider and animation being stopped due to code being runned in between
        //that's why variable creation comes after, though this is against convention
        timeline.pause();  

        //location for the left side (shtX1) and right side(shtX2) of the slider once it's placed 
        short shtX1 = 0; 
        short shtX2 = 0; 

        //T for top slider -> slider to be placed or slider currently moving
        //B for bottom slider -> slider on the bottom of top slider once they are placed aka slider currently on the tower
        //X1 for left side x value
        //X2 for right side x value

        short shtTX1 = (short)this.rectangle.getLayoutX(); //get the shtTX1 from the layoutX property of rectangle
        short shtTX2 = (short)(shtTX1 + shtWidth); //the shtTX2 is simply a slider width away
        short shtBX2 = (short)(shtBX1 + shtWidth); //the shtBX2 is simply a slider width away

        //if right side of top slider is more left than left side of bottom slider player clicked too early
        //if left side of top slider is more right than right side of bottom slider player clicked too early
        if (shtTX2 < shtBX1 || shtTX1 > shtBX2)
        {
            return false;  //player lost
        }
        else 
        {
            //if right side of top is more left than right side of bottom, that means overhang is on left side
            if (shtTX2 < shtBX2)
            {
                shtX1 = shtBX1; //left side of soon to be placed slider is the same as the bottom slider
                shtX2 = shtTX2; //right side of soon to be placed slider is the same as the top slider's
            }
            //if left side of top is more right than let side of bottom, that means overhand is on right side
            else if (shtTX1 > shtBX1)
            {
                shtX1 = shtTX1; //left side of soon to be placed slider is the same as the top's slider
                shtX2 = shtBX2;//right side of soon to be placed slider is the same as the bottom slider's
            }
            else //player made perfect placement so just set right and left sides to be the same as the bottom's
            {
                shtX1 = shtBX1;
                shtX2 = shtBX2;
            }
        }

        //set the width to be the difference between the locations of right and left sides
        shtWidth = (short)(shtX2 - shtX1); 

        //set the layoutx property of the rectangle to be that of the left side
        this.rectangle.setLayoutX(shtX1); 
        //set its width 
        this.rectangle.setWidth(shtWidth);
        //set left x location of previous slider (not previous yet but another one will be created shortly in gamemanager class) to be that of the just placed slider
        //which is the bottom slider once a new slider is created 
        shtBX1 = shtX1;

        return true; //return true to let caller know slider was successfully created

    }

    //gets rectangle
    public Rectangle getRectangle()
    {
        return this.rectangle; 
    }

    //set duration of animation
    protected void setDuration(short s)
    {
        shtDuration = s; 
    }

    //set the array of colours
    public static void setColors(Color[] colors)
    {
        //arr_colors = colors; 
    }
}
