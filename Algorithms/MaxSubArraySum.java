import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘… Max Sub Array  …'·.,¸¸,.·'´°•„¸¸„›“

	This dynamic programming challenge can be found here: https://www.hackerrank.com/challenges/maxsubarray
	
	This challenge can be solved in O(n) efficiency using this solution.  It should be noted that
	part of my solution was created using Kadane's algorithm.  Originally I attempted to solve this problem
	by creating a 2d array (as I am used to with most dynamic programming challenges), I quickly realized
	that the solution was not that complicated and simplified the solution by using Kadane's algorithm to
	form lines 41 and 42 below.  The rest of the solution is my own.  The idea is that as long as the sum
	of a subarray is greater than 0, it is worth keeping the subarray, even if there are negative values there.
	
	
*/

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numberOfTests = in.nextInt();
		
		for( int i = 0; i < numberOfTests; i++ )
		{
			int arraySize = in.nextInt();
			int[] valueArray = new int[arraySize];
			int maxSumOfContiguousSubArray = -2000000; 	//Initialize to extremely small numbers
			int maxSumOfNoncontiguousSubArray = -2000000;			
			int sumToHere = 0;
			
			//Read input into the valueArray, for each input value, check if the sum using that value is the new
			//		max sum value up to this point
			for( int j = 0; j < arraySize; j++ )	
			{
				valueArray[j] = in.nextInt();
				sumToHere = Math.max( valueArray[j], sumToHere + valueArray[j] );
				maxSumOfContiguousSubArray = Math.max(maxSumOfContiguousSubArray, sumToHere);
				
				//There are two possibilities, either the largest value is in the latest input value (if only negative values are input, this will return
				//	the greatest negative.  Or, there are positive values which are each incremented to maxSumOfNoncontiguousSubArray
				maxSumOfNoncontiguousSubArray = Math.max(valueArray[j], maxSumOfNoncontiguousSubArray + (valueArray[j] > 0 ? valueArray[j] : 0) );
			}
			
			System.out.println(maxSumOfContiguousSubArray + " " + maxSumOfNoncontiguousSubArray);
		}
	}
}