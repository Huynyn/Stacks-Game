/**
 * Write a description of class DefaultSlider here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MediumSlider extends Slider
{
    //ask mrs gilfillan if it's bad to have chilren classes that look identical
    private static final byte STARTING_SPEED = 5; 
    private static final byte MAX_SPEED = 15; 
    private static final byte INCREMENT_SPEED = 3; 
    private static byte bytChildSpeed = STARTING_SPEED;
    
    
    public MediumSlider(byte b)
    {
        super(b); 
        super.addRectangleToScreen(); 
        
    }
    
    public MediumSlider(byte b, boolean bolSide)
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
