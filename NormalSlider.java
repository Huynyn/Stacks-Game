/**
 * Subclass of slider that has overload constructor to set values and run methods in parent class depending
 * on whether it is a slider on the starting tower or a moving slider
 * Child sliders can control their own difficulty by changing the starting duration of slider, its fastest duration
 * and how quickly the duration increases
 * 
 * this is for a normal difficulty slider
 * 
 *
 * @author (Huy Nguyen)
 * @version (January 14th 2024)
 */
public class NormalSlider extends Slider
{
    //SET SLIDER CUSTOM VARIABLES
    private static final short STARTING_DURATION = 2500;  //how long it takes the slider to move across the first time it's placed.  K
    private static final short FASTEST_DURATION = 1500; //the fastest it can take the slider to move across the screen on it's placed
    private static final short INCREMENT_DURATION = 200; //how much the duration will go down by each time a slider is placed to incrementally increase difficulty by increasing and increasing the speed

    //set child duration to be that of the starting duration by default
    private static short shtChildDuration = STARTING_DURATION;

    //constructor used to simply add a slider when building the initial tower
    public NormalSlider(byte b)
    {
        super(b); //call super constructor
        //call addrectangletoscreen to put rectangle in the middle of the screen
        super.addRectangleToScreen(); 

    }

    //constructor used to simply add a moving slider
    public NormalSlider(byte b, boolean bolSide)
    {
        super(b); //call super constructor
        
        //set duration of slider
        super.setDuration(shtChildDuration); 
        
        //if lowering duration maintains that the duration does not exceed fasted duration
        //this is used rather than a simpler shtChildDuration >= fastestDuration in case hardcoded values do not increment nicely
        //donwards towards fastest duration and then they may surpass the fasted duration 
        if ((shtChildDuration - INCREMENT_DURATION) >= FASTEST_DURATION)
        {
            shtChildDuration -= INCREMENT_DURATION;  //make duration quicker using increment
        }
    
        //addrectangletoscreen to screen by calling overloaded method with bolSide
        ///because I want the version that puts the rectangle on the side of a screen and starts its animation
        super.addRectangleToScreen(bolSide); 

    }

    public static void resetDuration()
    {
        //reset child duration to be that of the starting duration by default
        //add increment duration because the second constructor will take it away
        shtChildDuration = STARTING_DURATION; 
    }

}
