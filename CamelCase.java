import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*

	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘…  Camel Case  …'·.,¸¸,.·'´°•„¸¸„›“

    This is a very easy problem that can be found at: https://www.hackerrank.com/challenges/camelcase
    
    Simply, we count the number of upper case characters in the array and return that + 1, which is the number
    of words in the string.
    
*/
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int answer = 1;
        
        for( char c : s.toCharArray() )
            if(Character.isUpperCase(c) ) answer++;
            
        System.out.println(answer);
    }
}
