
/**
 * Write a description of class DefaultSlider here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EasySlider extends Slider
{
    //ask mrs gilfillan if it's bad to have chilren classes that look identical
    private static final byte STARTING_SPEED = 2; 
    private static final byte MAX_SPEED = 6; 
    private static final byte INCREMENT_SPEED = 2; 
    private static byte bytChildSpeed = STARTING_SPEED;
    
    
    public EasySlider(byte b)
    {
        super(b); 
        
        
        super.addRectangleToScreen(); 
        
    }
    
    //on the sides.. a new slider
    public EasySlider(byte b, boolean bolSide)
    {
        super(b); 
        
        //set custom modifiers 
        //super.setWidth(width); 
        //super.setIncrementSpeed((byte)2); 
        //super.setMaxSpeed((byte)10); 
        
        /*
        if (this.bytChildSpeed <= super.getMaxSpeed())
        {
          this.bytChildSpeed += super.getIncrementSpeed();  
        }*/
        
    
        super.setSpeed(bytChildSpeed); 
        if (bytChildSpeed <= MAX_SPEED)
        {
            bytChildSpeed += INCREMENT_SPEED;   
        }
        
        //super.createRectangle(); 
        
        super.addRectangleToScreen(bolSide); 

        
    }
    
    public static void resetSpeed()
    {
        bytChildSpeed = STARTING_SPEED; 
    }
    
}
