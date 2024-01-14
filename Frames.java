
/**
 * Write a description of class Frames here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Frames
{
    private boolean bolIsRunning; 
    private byte bytFramesPerSecond; 
    
    
    public void start(byte b)
    {
        System.out.println("started");
        bolIsRunning = true; 
        Running((short)(1000/b));     

    }
    public void stop()
    {
        bolIsRunning = false; 
    }

    
    public void Running(short s)
    {
        System.out.println("runnikng");
        try
        {
            Thread.sleep(s);
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }      
        
        if (bolIsRunning)
        {
            action(); 
            Running(s); 
        }
    }

    public void action()
    {
        
    }

}
