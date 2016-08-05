using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

/*
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘…Jumping On the Clouds…'·.,¸¸,.·'´°•„¸¸„›“


    This is a solution to the easy challenge found here: https://www.hackerrank.com/challenges/jumping-on-the-clouds
    
    We are trying to figure out how many jumps it will take us to reach the end of the line of clouds (see site for
    a good picture representation).  We can either jump one space or two and we have to avoid the thunderclouds. This
    means that we will always try to jump two clouds, but if the second cloud is a thundercloud then we only jump one
    space.  The problem statement states that there will always be a solution to the problem, so in my solution there is
    no checking to see whether that is true or not.

*/

class Solution {

    static void Main(String[] args) {
        int n = Convert.ToInt32(Console.ReadLine());
        string[] c_temp = Console.ReadLine().Split(' ');
        int[] c = Array.ConvertAll(c_temp,Int32.Parse);
        
        int numberOfJumps = 0;
        for(int currentCloud = 0; currentCloud < n - 1; numberOfJumps++)
            currentCloud += c[(currentCloud + 2) % n] == 1 ? 1 : 2;     //If the cloud two spaces away is a thundercloud, then we jump to the cloud one space away instead
        
        Console.WriteLine(numberOfJumps);
    }
}
