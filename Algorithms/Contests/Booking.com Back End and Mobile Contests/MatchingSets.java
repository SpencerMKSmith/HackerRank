import java.io.*;
import java.util.*;

/*
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘…  Matching Sets …'·.,¸¸,.·'´°•„¸¸„›“

	https://www.hackerrank.com/contests/w22/challenges/box-moving
	
	
*/

public class Solution {

	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		int sizeOfArrays = in.nextInt();
		
		//Read input into the first array and keep running total of the sum
		double[] array1 = new double[sizeOfArrays];
		double sumArray1 = 0;
		for(int i = 0; i < sizeOfArrays; i++)
		{
			array1[i] = in.nextInt();
			sumArray1 += array1[i];
		}
		
		//Read input into the second array and keep running of the sum
		double[] array2 = new double[sizeOfArrays];
		double sumArray2 = 0;
		for(int i = 0; i < sizeOfArrays; i++)
		{
			array2[i] = in.nextInt();
			sumArray2 += array2[i];
		}
		
		//If the sum of the two arrays are not equal then there is no solution to the problem
		if( sumArray1 != sumArray2 )
		{
			System.out.println(-1);
		}
		else //First we sort the arrays.  The answer is the sum of the difference between the values in the array when comparing values at the same index
		{
			Arrays.sort(array1);
			Arrays.sort(array2);
			double operations = 0;
			for(int i = 0; i < sizeOfArrays; i++)
				operations += Math.abs( ((double)array1[i] - (double)array2[i] ) / 2);
			
			System.out.println((long)operations);
		}
		
	}
}