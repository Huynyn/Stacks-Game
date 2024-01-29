import javafx.scene.paint.Color;

/**
 * Helper methods used in the program. The only helper method here is creating an color array for a gradient of colours
 *
 * @author (Huy Nguyen)
 * @version (January 15th)
 */
public class HelperMethods
{
    //returns an array of colors for the color gradient 
    //passes in shtIncrement which is how many steps to go up or down the rgb scale so basically how drastic
    //the gradient changes
    //by increment, I mean going up or down, not solely an addition
    public static Color[] createColourGradient(short shtIncrement)
    {
        //theory behind creating a gradient -- the values to increment to (im calling them levels), to create a gradient 
        //rgb: red value, green value, blue value
        //start at rgb value of level 0: 255, 0, 0
        //increment to level 1: 255, 255, 0
        //increment to level 2: 0, 255, 0
        //increment to level 3: 0, 255, 255
        //increment to level 4: 0, 0, 255
        //increment to level 5: 255, 0, 255
        //increment to level 6: 255, 0, 0

        //the amount of increments that occur to get to the next value of rgb values (ex: 255, 0, 0 -> 255, 255, 0)
        //perfect incrementation to the next level only occurs when shtIncrement is a factor of 255, but program will still work if not
        short shtSteps = (short)(255/shtIncrement); 
        //create a temporary array to store all the colors in the gradient
        //the size of the array is the number of steps of incremetn to get to each level multiplied by number of levels to increment to (6)
        Color [] temp_colors = new Color[shtSteps * 6]; 

        //create shorts that store the rgb values of each colour
        short shtRed = 255; //starts at 255
        short shtGreen = 0; //starts at 0
        short shtBlue = 0; //starts at 0

        //set the first elemetn to be the starting value
        temp_colors[0] = Color.rgb(shtRed, shtGreen, shtBlue); 
        
        //FOR LOOPS;
        //the starting index (b) is a multiple of the number of steps starting from 0 to 5
        //add 1 to all because element 0 has been already set to subsequent ones must start one greator
        //shtIndex counts to less than the starting index (shtIndex) of the next for loop 
        //
        //increment in for loop
        //then set colour in array of colors
        
        //increment to level 1: 255, 255, 0
        for (short shtIndex = (short)(shtSteps * 0 + 1); shtIndex < (shtSteps * (0 + 1)) + 1; shtIndex++)
        {
            shtGreen += shtIncrement; 

            temp_colors[shtIndex] = Color.rgb(shtRed,shtGreen,shtBlue);
        }
        //increment to level 2: 0, 255, 0
        for (short shtIndex = (short)(shtSteps * 1 + 1); shtIndex < (shtSteps * (1 + 1)) + 1; shtIndex++)
        {
            shtRed -= shtIncrement; 

            temp_colors[shtIndex] = Color.rgb(shtRed,shtGreen,shtBlue);
        }
        //increment to level 3: 0, 255, 255
        for (short shtIndex = (short)(shtSteps * 2 + 1); shtIndex < (shtSteps * (2 + 1)) + 1; shtIndex++)
        {
            shtBlue += shtIncrement; 

            temp_colors[shtIndex] = Color.rgb(shtRed,shtGreen,shtBlue);
        }
        //increment to level 4: 0, 0, 255
        for (short shtIndex = (short)(shtSteps * 3 + 1); shtIndex < (shtSteps * (3 + 1)) + 1; shtIndex++)
        {
            shtGreen -= shtIncrement; 

            temp_colors[shtIndex] = Color.rgb(shtRed,shtGreen,shtBlue);
        }
        //increment to level 5: 255, 0, 255
        for (short shtIndex = (short)(shtSteps * 4 + 1); shtIndex < (shtSteps * (4 + 1)) + 1; shtIndex++)
        {
            shtRed += shtIncrement; 

            temp_colors[shtIndex] = Color.rgb(shtRed,shtGreen,shtBlue);
        }
        //increment to level 6: 255, 0, 0
        //this for loop runs one less time than the other ones because the last run will just repeat color 255, 0, 0
        for (short shtIndex = (short)(shtSteps * 5 + 1); shtIndex < (shtSteps * (5 + 1)); shtIndex++)
        {
            shtBlue -= shtIncrement; 

            temp_colors[shtIndex] = Color.rgb(shtRed,shtGreen,shtBlue);
        }
        return temp_colors;  //return the array of colours
    }

}