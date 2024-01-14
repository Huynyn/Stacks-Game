
/**
 * Write a description of class DefaultSlider here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HardSlider extends Slider
{
    //ask mrs gilfillan if it's bad to have chilren classes that look identical
    private static final byte STARTING_SPEED = 7; 
    private static final byte MAX_SPEED = 21;  
    private static final byte INCREMENT_SPEED = 4; 
    private static byte bytChildSpeed = STARTING_SPEED;
    
    
    public HardSlider(byte b)
    {
        super(b); 
        super.addRectangleToScreen(); 
        
    }
    
    public HardSlider(byte b, boolean bolSide)
    {
        super(b); 
        
        
         
        super.setSpeed(bytChildSpeed);     
        super.addRectangleToScreen(bolSide);  
        
        if (bytChildSpeed <= MAX_SPEED)
        {
            bytChildSpeed += INCREMENT_SPEED;   
        }
    }
    
    public static void resetSpeed()
    {
        bytChildSpeed = STARTING_SPEED; 
    }
    
}
