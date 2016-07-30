import java.io.*;
import java.util.*;

/* 
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘…  Birthday Candles   …'·.,¸¸,.·'´°•„¸¸„›“

    This is the first challenge of the RookieRank contest found here: https://www.hackerrank.com/contests/rookierank/challenges/birthday-cake-candles
  
  I simply count the number of candles with the greatest height on the candle, although
  poor Colleen should just stand on a stool so that she can blow all of her candles out and
  get her wish...
*/

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfCandles = in.nextInt();
        int maxHeight = 0;
        int numberOfMaxHeight = 0;
        int[] candles = new int[numberOfCandles];
        
        for(int i = 0; i < numberOfCandles; i++)
        {
            candles[i] = in.nextInt();
            
            if( candles[i] > maxHeight )
            {
                numberOfMaxHeight = 0;
                maxHeight = candles[i];
            }
            
            if(candles[i] == maxHeight )
                numberOfMaxHeight++;
        }
        
        System.out.println(numberOfMaxHeight);
    }
}