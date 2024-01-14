
/**
 * Write a description of class DefaultSlider here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NormalSlider extends Slider
{
    //ask mrs gilfillan if it's bad to have chilren classes that look identical
    private static final byte STARTING_SPEED = 3; 
    private static final byte MAX_SPEED = 10; 
    private static final byte INCREMENT_SPEED = 2; 
    private static byte bytChildSpeed = STARTING_SPEED;
    
    
    public NormalSlider(byte b)
    {
        super(b); 
        super.addRectangleToScreen(); 
        
    }
    
    public NormalSlider(byte b, boolean bolSide)
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
