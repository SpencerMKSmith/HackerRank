import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘… Array Roataion …'·.,¸¸,.·'´°•„¸¸„›“

	
	This challenge can be found at: https://www.hackerrank.com/challenges/circular-array-rotation
	
	After first reading the problem statement my first thought was to simply read the input,
	store the values in an array, perform the array rotation one value at a time, and then
	output what is asked. But, by looking at the constraints, it is clear that possibly having
	an array of 1 milion values rotated up to a milion times would not be efficient, or necessary.
	So, the solution is easily reached with some simple index magic which is, instead of changing the
	order of the array at all, just calculate what index you should look at by taking into account
	how many times the array is rotated.  
*/
public class Solution {

    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	int arraySize, rotationAmount, queryAmount;
    	arraySize = in.nextInt();
    	rotationAmount = in.nextInt() % arraySize; //If the array is size 10 and is rotated 1 million times, it is clearly not necessary to handle all of the rotations, simply take the mod of the values.
    	queryAmount = in.nextInt();
    	
    	int[] array = new int[arraySize];
    	for( int i = 0; i < arraySize; i++ )
    		array[i] = in.nextInt();
    	
    	for(int i = 0; i < queryAmount; i++)
    		System.out.println(array[(in.nextInt() - rotationAmount + arraySize) % arraySize]); //Index magic
    }
}