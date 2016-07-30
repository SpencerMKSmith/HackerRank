using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

/*
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘… String Construction  …'·.,¸¸,.·'´°•„¸¸„›“

    This string manipulation challenge can be found here: https://www.hackerrank.com/challenges/string-construction
    
    The solution can be broken down into simply discovering how many unique characters are in the string.  This is because
    a single character of the string can also be seen as a substring of that string, meaning that it doesn't matter
    how many substrings of length 2, 3+, the only thing that matters is the amount of unique characters that we encounter
    in the string.
    
    So, to discover this I have a boolean array of length 26, when a new char is encountered, I set the index corresponding
    to that char to true and increment the dollar counter.  The index is discovered by using the ASCII value of the char.
    
*/

class Solution {

    static void Main(String[] args) 
    {
        int n = Convert.ToInt32(Console.ReadLine());
        
        for(int i = 0; i < n; i++)
        {
            int dollarsSpent = 0;
            bool[] seenChars = new bool[26];
            char[] charArray = Console.ReadLine().ToCharArray();
            for(int j = 0; j < charArray.Length; j++)
            {
                if( !seenChars[ charArray[j] - 97 ] )       //If we havent seen this char before
                {
                    seenChars[ charArray[j] - 97 ]  = true; //Set it's value to true
                    dollarsSpent++;                         //And increment the dollar counter
                }
            }
            Console.WriteLine(dollarsSpent);
        }
    }
}