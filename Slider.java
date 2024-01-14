
import javafx.scene.shape.Rectangle; 
import javafx.scene.paint.Color;

/**
 * The objective of the game is to build a tower from sliding in rectangles that come in from the sides of the screen.
 * This class is the those rectangles. 
 *
 * @author (Huy Nguyen)
 * @version (Jan 9 2024)
 */
public abstract class Slider
{
    private final static Color[] COLOURS = new Color[]
        {
            Color.rgb(255,0,0),
            Color.rgb(255,23,0),
            Color.rgb(255,46,0),
            Color.rgb(255,69,0),
            Color.rgb(255,92,0),
            Color.rgb(255,115,0),
            Color.rgb(255,138,0),
            Color.rgb(255,161,0),
            Color.rgb(255,184,0),
            Color.rgb(255,207,0),
            Color.rgb(255,230,0),
            Color.rgb(255,253,0),
            Color.rgb(232,253,0),
            Color.rgb(209,253,0),
            Color.rgb(186,253,0),
            Color.rgb(163,253,0),
            Color.rgb(140,253,0),
            Color.rgb(117,253,0),
            Color.rgb(94,253,0),
            Color.rgb(71,253,0),
            Color.rgb(48,253,0),
            Color.rgb(25,253,0),
            Color.rgb(2,253,0),
            Color.rgb(2,253,23),
            Color.rgb(2,253,46),
            Color.rgb(2,253,69),
            Color.rgb(2,253,92),
            Color.rgb(2,253,115),
            Color.rgb(2,253,138),
            Color.rgb(2,253,161),
            Color.rgb(2,253,184),
            Color.rgb(2,253,207),
            Color.rgb(2,253,230),
            Color.rgb(2,253,253),
            Color.rgb(2,230,253),
            Color.rgb(2,207,253),
            Color.rgb(2,184,253),
            Color.rgb(2,161,253),
            Color.rgb(2,138,253),
            Color.rgb(2,115,253),
            Color.rgb(2,92,253),
            Color.rgb(2,69,253),
            Color.rgb(2,46,253),
            Color.rgb(2,23,253),
            Color.rgb(2,0,253),
            Color.rgb(25,0,253),
            Color.rgb(48,0,253),
            Color.rgb(71,0,253),
            Color.rgb(94,0,253),
            Color.rgb(117,0,253),
            Color.rgb(140,0,253),
            Color.rgb(163,0,253),
            Color.rgb(186,0,253),
            Color.rgb(209,0,253),
            Color.rgb(232,0,253),
            Color.rgb(255,0,253),
            Color.rgb(255,0,230),
            Color.rgb(255,0,207),
            Color.rgb(255,0,184),
            Color.rgb(255,0,161),
            Color.rgb(255,0,138),
            Color.rgb(255,0,115),
            Color.rgb(255,0,92),
            Color.rgb(255,0,69),
            Color.rgb(255,0,46),
            Color.rgb(255,0,23),
            Color.rgb(255,0,0)
        }; 

    private static byte bytColour = -1; 

    //private static final byte MINIMUM_WIDTH = 2; 
    private static final short HEIGHT = 30;
    static final short STARTING_WIDTH = 200; 
    private static final short shtMidwayPoint = GraphicsInterface.WIDTH / 2; 

    private static short shtWidth = STARTING_WIDTH; 
    //custom variables that change with children
    //private byte bytIncrementSpeed;
    //private byte bytMaxSpeed; 

    //put these in the children classes because they should update everytime a new slider is created
    private byte bytSpeed; 

    private Rectangle rectangle; 

    private boolean bolPlaced; 
    //CREATION OF SLIDER METHODS
    public Slider(byte b)
    {
        this.rectangle = new Rectangle(); 
        this.rectangle.setHeight(HEIGHT); 
        //this.rectangle.setLayoutX(GraphicsInterface.LENGTH / 2); 
        this.rectangle.setLayoutY(GraphicsInterface.HEIGHT - (b * HEIGHT));
        this.rectangle.setWidth(shtWidth); 

        bytColour++; 
        if (bytColour == COLOURS.length)
        {
            bytColour = 0; 
        }

        this.rectangle.setFill(COLOURS[bytColour]); 

        /*
        this.rectangle.setLayoutX(50);
        this.rectangle.setLayoutY(50);
         */
        //this.rectangle.setHeight(50); 
        //this.rectangle.setWidth(50);

        this.bolPlaced = false; 
    }

    protected void addRectangleToScreen()
    {
        //this.rectangle.setWidth(this.shtWidth);
        this.rectangle.setLayoutX(shtMidwayPoint - (shtWidth / 2));

        this.bolPlaced = true; 
    }

    protected void addRectangleToScreen(boolean bolSide)
    {
        //this.rectangle.setWidth(this.shtWidth);        

        if (bolSide)
        {
            this.rectangle.setLayoutX(0);
        }
        else 
        {
            this.rectangle.setLayoutX(GraphicsInterface.WIDTH - shtWidth);
        }

        this.bolPlaced = false; 
    }

    //going right
    public boolean shift(short shtX1, short shtX2, boolean bolSide)
    {
        System.out.println(bytSpeed);
        //System.out.println("before h: " + this.rectangle.getLayoutX());
        /*
        if (bolSide)
        {
        if (this.rectangle.getLayoutX() > (this.shtMidwayPoint + (STARTING_WIDTH + STARTING_WIDTH/2)))
        {
        GameManager.gameLost(); 
        System.out.println("HELLELUJIA1");  
        System.out.println("mdiway: " + this.shtMidwayPoint); 
        System.out.println("stargin wdith " + STARTING_WIDTH);

        System.out.println("rectangle get layout x " + this.rectangle.getLayoutX());    
        System.out.println("All togetehr: " + (this.shtMidwayPoint + (STARTING_WIDTH + STARTING_WIDTH/2))); 

        return false; 
        }
        this.rectangle.setLayoutX(this.rectangle.getLayoutX() + bytSpeed);

        }
        else 
        {
        if (this.rectangle.getLayoutX() < (this.shtMidwayPoint - (STARTING_WIDTH + STARTING_WIDTH/2)))
        {
        GameManager.gameLost(); 
        System.out.println("HELLELUJIA2");
        System.out.println("mdiway: " + this.shtMidwayPoint); 
        System.out.println("stargin wdith " + STARTING_WIDTH);

        System.out.println("rectangle get layout x " + this.rectangle.getLayoutX());    
        System.out.println("All togetehr: " + (this.shtMidwayPoint - (STARTING_WIDTH + STARTING_WIDTH/2))); 
        System.out.println("HELLELUJIA1");

        return false; 
        }
        this.rectangle.setLayoutX(this.rectangle.getLayoutX() - bytSpeed); 

        }   
         */
        if (bolSide)
        {
            if (this.rectangle.getLayoutX() >= shtX2)
            {
                //GameManager.gameLost(); 
                //System.out.println("HELLELUJIA1");  
                //System.out.println("mdiway: " + this.shtMidwayPoint); 
                //System.out.println("stargin wdith " + STARTING_WIDTH);

                //System.out.println("rectangle get layout x " + this.rectangle.getLayoutX());    
                //System.out.println("All togetehr: " + (this.shtMidwayPoint + (STARTING_WIDTH + STARTING_WIDTH/2))); 

                return false; 
            }
            this.rectangle.setLayoutX(this.rectangle.getLayoutX() + bytSpeed);

        }
        else 
        {
            if (this.rectangle.getLayoutX() <= (shtX1 - shtWidth))
            {
                //GameManager.gameLost(); 
                //System.out.println("HELLELUJIA2");
               // System.out.println("mdiway: " + this.shtMidwayPoint); 
                //System.out.println("stargin wdith " + STARTING_WIDTH);

                //System.out.println("rectangle get layout x " + this.rectangle.getLayoutX());    
                //System.out.println("All togetehr: " + (this.shtMidwayPoint - (STARTING_WIDTH + STARTING_WIDTH/2))); 
                //System.out.println("HELLELUJIA1");

                return false; 
            }
            this.rectangle.setLayoutX(this.rectangle.getLayoutX() - bytSpeed); 

        } 
        return true; 
    }

    public static void resetWidth()
    {
        shtWidth = STARTING_WIDTH; 
    }

    public void shiftDown()
    {
        this.rectangle.setLayoutY(this.rectangle.getLayoutY() + HEIGHT); 
    }

    /*
    public boolean placeSlider(short shtX1, short shtX2)
    {
    this.rectangle.setLayoutX(shtX1);
    this.rectangle.setWidth(shtX2 - shtX1);
    this.shtWidth = (short)(shtX2 - shtX1);

    this.bolPlaced = true; 

    if (rectangle.getWidth() < MINIMUM_WIDTH)
    {
    return false; //failed too small 
    }

    return true; 
    }
     */

    public void placeSlider(short shtX1, short shtX2)
    {
        this.bolPlaced = true; 

        this.rectangle.setLayoutX(shtX1);
        this.rectangle.setWidth(shtX2 - shtX1);
        this.shtWidth = (short)(shtX2 - shtX1);


        /*
        if (rectangle.getWidth() < MINIMUM_WIDTH)
        {
        return false; //failed too small 
        }

        return true; 
         */
    }


    public Rectangle getRectangle()
    {
        return rectangle; 
    }

    protected void setWidth(short s)
    {
        this.shtWidth = s; 
    }

    /*
    protected void setIncrementSpeed(byte b)
    {
    this.bytIncrementSpeed = b; 
    }

    protected void setMaxSpeed(byte b)
    {
    this.bytMaxSpeed = b; 
    }
     */ 

    protected void setSpeed(byte b)
    {
        this.bytSpeed = b; 
    }

    public short getHeight()
    {
        return HEIGHT; 
    }

    public short getWidth()
    {
        return this.shtWidth; 
    }

    public boolean getIfPlaced()
    {
        return this.bolPlaced; 
    }

    /*
    protected byte getMaxSpeed()
    {
    return this.bytMaxSpeed ; 
    }
     */

    /*
    protected byte getIncrementSpeed()
    {
    return this.bytIncrementSpeed; 
    }
     */

}
