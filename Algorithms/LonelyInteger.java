import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*

	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘…  Lonely Integer  …'·.,¸¸,.·'´°•„¸¸„›“

    This bit manipulation problem can be found at: https://www.hackerrank.com/challenges/lonely-integer
    
    This problem can easily be solved without bit manipulation, but using it is very simple and very fun.
    First, this must be understood:
            a XOR 0 = a
            a XOR a = 0
            a XOR b XOR a = b
            
     Knowing this, we can see that if we XOR each of the input numbers together, the resulting value will
     be the missing value, which we output.

*/
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int value = 0;
        for (int i = 0; i < n; i++)
            value = value^in.nextInt(); //XOR each of the values to each other
        
        System.out.println(value);
    }

}
