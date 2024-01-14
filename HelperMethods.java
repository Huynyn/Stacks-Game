import javafx.scene.paint.Color;

/**
 * Write a description of class HelperMethods here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HelperMethods
{
    public static String[] colorGradientGenerator()
    {
        Color [] temp = new Color[31];
         
        short shtRed = 255;
        short shtGreen = 0;
        short shtBlue = 0;

        byte bytIncrement = 23; 
        byte bytRange = (byte)(255/bytIncrement); 
        
        String[] strings = new String[6 * bytRange + 1];

        strings[0] = String.format("Color.rgb(%d,%d,%d)", shtRed,shtGreen,shtBlue);

        for (int b = (bytRange * 0) + 1; b < (bytRange * (0 + 1)) + 1; b++)
        {
            shtGreen += bytIncrement; 

            strings[b] = String.format("Color.rgb(%d,%d,%d)", shtRed,shtGreen,shtBlue);
        }
        for (int b = (bytRange * 1) + 1; b < (bytRange * (1 + 1)) + 1; b++)
        {
            shtRed -= bytIncrement; 

            strings[b] = String.format("Color.rgb(%d,%d,%d)", shtRed,shtGreen,shtBlue);
        }
        for (int b = (bytRange * 2) + 1; b < (bytRange * (2 + 1)) + 1; b++)
        {
            shtBlue += bytIncrement; 

            strings[b] = String.format("Color.rgb(%d,%d,%d)", shtRed,shtGreen,shtBlue);
        }
        for (int b = (bytRange * 3) + 1; b < (bytRange * (3 + 1)) + 1; b++)
        {
            shtGreen -= bytIncrement; 

            strings[b] = String.format("Color.rgb(%d,%d,%d)", shtRed,shtGreen,shtBlue);
        }
        for (int b = (bytRange * 4) + 1; b < (bytRange * (4 + 1)) + 1; b++)
        {
            shtRed += bytIncrement; 

            strings[b] = String.format("Color.rgb(%d,%d,%d)", shtRed,shtGreen,shtBlue);
        }for (int b = ((bytRange * 5) + 1); b < (bytRange * (5 + 1)) + 1; b++)
        {
            shtBlue -= bytIncrement; 

            strings[b] = String.format("Color.rgb(%d,%d,%d)", shtRed,shtGreen,shtBlue);
        }

        
        return strings;
    }

    public static void main(String[] args)
    {
        for (String s : colorGradientGenerator())
        {
            System.out.println(s + ","); 
        }
    }
}

    /*
    for (byte b = 1; b < 6; b++)
    {
    shtGreen += 51;   
    strings[b] = String.format("Color.rgb(%d,%d,%d)", shtRed,shtGreen,shtBlue);
    }
    for (byte b = 6; b < 11 ; b++)
    {
    shtRed -= 51; 
    strings[b] = String.format("Color.rgb(%d,%d,%d)", shtRed,shtGreen,shtBlue);
    }
    for (byte b = 11; b < 16; b++)
    {
    shtBlue += 51; 
    strings[b] = String.format("Color.rgb(%d,%d,%d)", shtRed,shtGreen,shtBlue);
    }
    for (byte b = 16; b < 21; b++)
    {
    shtGreen -= 51; 
    strings[b] = String.format("Color.rgb(%d,%d,%d)", shtRed,shtGreen,shtBlue);
    }
    for (byte b = 21; b < 26; b++)
    {
    shtRed += 51; 
    strings[b] = String.format("Color.rgb(%d,%d,%d)", shtRed,shtGreen,shtBlue);
    }
    for (byte b = 26; b < 31; b++)
    {
    shtBlue -= 51;  
    strings[b] = String.format("Color.rgb(%d,%d,%d)", shtRed,shtGreen,shtBlue);
    } 
    }
     */ 

