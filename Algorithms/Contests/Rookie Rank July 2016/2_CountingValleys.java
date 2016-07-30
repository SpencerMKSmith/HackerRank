import java.io.*;
import java.util.*;

/*
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘…  Counting Valleys    …'·.,¸¸,.·'´°•„¸¸„›“

	Challenge 2 from the RookieRank challenge: https://www.hackerrank.com/contests/rookierank/challenges/counting-valleys
	
	We are given a string of Us and Ds are are asked to find how many valleys we hike into.

*/
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfSteps = in.nextInt();
        int[] heights = new int[numberOfSteps + 1];
        String steps = in.next();
        char[] c = steps.toCharArray();
        
        int numberOfValleys = 0;
        boolean inAValley = false;
        heights[0] = 0;
		
		//Loop through the array of chars, if a U is found, store in the heights array that we've decended one steps
		//		otherwise, store that we've gone up a step.  If we aren't in a valley and we go into one, increment the
		//		counter, if we leave the valley set the inAValley flag to false so we know that we left.
        for(int i = 0; i < numberOfSteps; i++)
        {
            if(c[i] == 'U')
                heights[i+1] = heights[i] + 1;
            else
                heights[i+1] = heights[i] - 1;
            
            if(heights[i] < 0 && !inAValley )
            {
                numberOfValleys++;
                inAValley = true;
            }
            else if(heights[i] == 0)
                inAValley = false;
        }
        
        System.out.println(numberOfValleys);
    }
}