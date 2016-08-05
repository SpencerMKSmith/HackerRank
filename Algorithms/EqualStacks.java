import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘…  Equal Stacks    …'·.,¸¸,.·'´°•„¸¸„›“

    This challenge can be found here: https://www.hackerrank.com/challenges/equal-stacks
    
    Basically what the question is asking is, give me the highest point where the columns can be equal after
    removing any number of cylinders.  This can easily be found if we have an array of total column heights for each
    cylinder.  For the first column, the input is [3, 2, 1 , 1, 1, 0].  We then calculate the overall height and the array
    then looks like this: [8, 5, 3, 2, 1, 0] So index 0 gives the overall height at the top of the stack.  Index 1 gives
    the height if we removed the first cylinder.  
    
    After we have all three of these height arrays, we simply start at the top and look for the first instance of where
    each of the three heights are equal.

*/

public class Solution {

    public static void main(String[] args) {
        
        // Read Input start
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int n3 = in.nextInt();
        
        int h1[] = new int[n1 + 1];
        for(int h1_i=0; h1_i < n1; h1_i++)
            h1[h1_i] = in.nextInt();

        int h2[] = new int[n2 + 1];
        for(int h2_i=0; h2_i < n2; h2_i++)
            h2[h2_i] = in.nextInt();
    
        int h3[] = new int[n3 + 1];
        for(int h3_i=0; h3_i < n3; h3_i++)
            h3[h3_i] = in.nextInt();

        //Read Input end
        
        
        //Now we will modify the array that we read the input into.  We will start from the last index(the bottom of the column)
        //      and change the array so that the values reflect the overall height of the column at each index.
        for(int i=n1-1; i >= 0; i--)
            h1[i] = h1[i] + h1[i + 1];

        for(int i=n2-1; i >= 0; i--)
            h2[i] = h2[i] + h2[i + 1];
        
        for(int i=n3-1; i >= 0; i--)
            h3[i] = h3[i] + h3[i + 1];
        
        //Now we will start from the top of the stack.  If we find three heights that are the same, we print it and end
        //Otherwise, we decrement one of the values that is larger than the other and retry.
        
        int col1 = 0; //This values reflect what cylinder index we are looking at in each column
        int col2 = 0;
        int col3 = 0; 
        while( h1[col1] != h2[col2] || h2[col2] != h3[col3] ) //While the heights that we are looking at aren't equal, decrement one
        {         
            if( h1[col1] > h2[col2] || h1[col1] > h3[col3] )
                col1++;
            else if( h2[col2] > h1[col1] || h2[col2] > h3[col3] )
                col2++;
            else if( h3[col3] > h2[col2] || h3[col3] > h1[col1])
                col3++;
        }
        
        System.out.println(h1[col1]); //Once they're equal, print this

    }
}
