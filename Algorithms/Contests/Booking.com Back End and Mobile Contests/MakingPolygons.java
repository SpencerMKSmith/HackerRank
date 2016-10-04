import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘…  Making Polygons …'·.,¸¸,.·'´°•„¸¸„›“

	https://www.hackerrank.com/contests/w22/challenges/polygon-making
*/

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<Double> sizeLengths = new ArrayList<Double>();
        int sumOfAllLengths = 0;
        for(int i=0; i < n; i++){
            sizeLengths.add(in.nextDouble());
            sumOfAllLengths += sizeLengths.get(i);
        }
                
        int numberOfCuts = 0;
        int numberOfSticks = sizeLengths.size();
        
        if(numberOfSticks == 1) //If there is only one stick given then you must cut it twice to create a polygon
        {
            System.out.println(2);
            System.exit(0);
        }
        else if(numberOfSticks == 2 && Double.compare(sizeLengths.get(0), sizeLengths.get(1) ) == 0 ) //If only two sticks are given of the same length, you must cut them twice to make a polygon
        {
            System.out.println(2);
            System.exit(0);
        }
        else
        {
            for( int i = 0; i < numberOfSticks; i++ )
            {
                if(sizeLengths.get(i) >= .5 * sumOfAllLengths)
                {
                    double length = sizeLengths.get(i);
                    double newLength = length / 2;
                    sizeLengths.remove(i);
                    sizeLengths.add(newLength);
                    sizeLengths.add(length - newLength);
                    numberOfCuts++;
                }
            }
        }
        System.out.println(numberOfCuts);
    }
}
