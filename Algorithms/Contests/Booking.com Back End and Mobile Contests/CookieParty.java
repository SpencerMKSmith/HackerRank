import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘…  Cookie Party …'·.,¸¸,.·'´°•„¸¸„›“


	https://www.hackerrank.com/contests/w22/challenges/cookie-party

*/

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        
        if( n == m || m % n == 0 )
            System.out.println( 0 );
        else if( n > m )
            System.out.println( n - m );
        else
            System.out.println( Math.abs( m % n - n) );
    }
}
