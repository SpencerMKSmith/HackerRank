import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘…   Kangaroo   …'·.,¸¸,.·'´°•„¸¸„›“
	
	This problem can be found here: https://www.hackerrank.com/challenges/kangaroo
	
	The problem is very simple, there are two kargaroos that start at different places
		on a line that they jump on.  Both hop different lengths each time.  Find out
		whether the two kangaroos will ever hop on the same place at the same time.  
		Simply, we are solving for a variable.  We look at the solution in the form:
				v1*hops + x1 = v2*hops + x2
				
		And we look if there is a value of hops such that they land in the same place
		at the same time.  So we find the value of hops, if it is a positive integer,
		then they do, else they don't.
	
*/
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double x1 = in.nextDouble();
        double v1 = in.nextDouble();
        double x2 = in.nextDouble();
        double v2 = in.nextDouble();

        double possibleMeetingValue = (x2-x1)/(v1-v2);
        
        if( possibleMeetingValue<=0 || (possibleMeetingValue % 1 != 0 ) ) 
            System.out.println("NO");
        else
            System.out.println("YES");
    }
}
