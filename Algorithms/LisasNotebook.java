import java.io.*;
import java.util.*;

/*
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘… Lisa's Workbook …'·.,¸¸,.·'´°•„¸¸„›“
	
	This is my solution to Lisa's Workbook which is found here: https://www.hackerrank.com/challenges/lisa-workbook
	
	My solution may not be the least lines (after the fact I saw other solutions in Python with 2 lines), but it is easily
		understandable.  My basis is that for each chapter, we want to look at each page and see if it has the same problem
		number as the page number.  So, for each chapter we figure out how many pages it needs, then loop through each page 
		and determine the index of the first and last problem and see if the page number is between those two values.

*/

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfChapters = in.nextInt();
        int maxProblemsPerPage = in.nextInt();
        int numberOfSpecialProblems = 0; 					//This will be the value that is output, the number of "special" problems
        int pageCount = 0;
        
        for(int i = 0; i < numberOfChapters; i++)
        {
            int chapterProblems = in.nextInt();
            int pagesNeeded = (chapterProblems + maxProblemsPerPage - 1) / maxProblemsPerPage; //How many pages will this chapter need. This is the equivalent of ceiling(chapterProblems / maxProblemsPerPage)

            for(int j = 0; j < pagesNeeded; j++)
            {
                int currentPage = pageCount + j + 1; 											//What page are we dealing with?
                int firstProblemOnPage = j * maxProblemsPerPage + 1; 							//The question at the top of the page
                int numberOfProblemsLeftToPrint = chapterProblems - j * maxProblemsPerPage; 	// Number of problems left to print from the chapter
                int numberOfProblemsOnPage = (numberOfProblemsLeftToPrint > maxProblemsPerPage) ? maxProblemsPerPage : numberOfProblemsLeftToPrint; //Number of problems that will be on the page
                
				//A problem is a "special" problem if the problem index (within the chapter) is found on it's same page number.
				//Thusly, if the page number is between the first problem number on the page and the last one, we know there is a "special" problem on the page and we increment the counter
                if( currentPage >= firstProblemOnPage && currentPage <= firstProblemOnPage + numberOfProblemsOnPage - 1)
                    numberOfSpecialProblems++;
                
            }
            pageCount += pagesNeeded; //After we analyze all of the pages in a given chapter, add those pages to the book and we look at the next chapter
        }
        
        System.out.println(numberOfSpecialProblems);
        
    }
}