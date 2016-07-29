#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

/*
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘… Snakes and Ladders …'·.,¸¸,.·'´°•„¸¸„›“

	This c++ file is a solution to the Snakes and Ladders problem found here: https://www.hackerrank.com/challenges/the-quickest-way-up

	While the the category of the problem implies that it should be solved using graph theory and some kind of
	depth first search algorithm, I decided to use a dynamic programming approach to the problem as it made
	more sense to me, and it seems more efficient.  I will list below the steps that I take to solve it.

	1. Read the input, loop through the number of test cases
	2. Create a blank board with no ladders that will be reused through the test cases
	3. Read the ladder and chutes input and create the pointers on the board and set the square types
	4. Once all of that is done, we start from the last square (100) and go backwards and for each square determine how many
	   moves it takes from that square to win.  This means that the value of movesToWin of square 100 is 0.  Squared 94-99 are assigned
	   values of 1, because with a single roll we can reach square 100 (given that none of the squares are a chute entrance).
	5. If the square is normal, we take the minimum value of the 6 squares in front of it, add 1, and that's how many moves it takes
	   to win from that square.
	6. If the square is a ladder head, it is assigned the same value as it's exit
	7. If the square is a chute head, it gets very tricky.  Despite initial thought process, it sometimes may be a good idea to trascend
	   a chute, think of the situation where a chute leads to a ladder that brings you to to winning space, this would be a chute to go down.
	   to decide whether going down a chute is a good idea, we recursively descend the chutes and ladders to find if there is a ladder that
	   brings us higher than the first chute that we descended.
	8. Once we are done setting all of the values for the 100 spaces, we print the value of movesToWin from space #1, which is the optimal
	   number of moves needed to win the game.

*/

/* Forward Declarations */
struct Square;
void createBoard();
bool boardIsUnsolvable();
int discoverMostOptimalPath();
int traverseTheChute( Square* aCurrentLocation, std::vector<Square*> aChutesTraversed, int aRollsOfBestPathSoFar );
void clearBoardOfChutesAndLadders();


enum SquareType
{
	NORMAL,
	LADDER_HEAD,
	CHUTE_HEAD,
	CHUTE_EXIT
};
struct Square
{
	int squareNumber;
	SquareType squareType = NORMAL; 
	int movesToWin = 100;
	Square* nextSquare = NULL;
	union
	{
		Square* ladderExit;
		Square* chuteHead;
		Square* chuteExit;
	};

	Square( int number )
	{ 
		squareNumber = number;
	}
};

Square* lookup[101];

int main()
{
	int numberOfCases, numberOfLadders, numberOfChutes, fromSquare, toSquare;
	cin >> numberOfCases;

	createBoard();

	for ( int i = 0; i < numberOfCases; i++ )
	{

		cin >> numberOfLadders;
		for ( int j = 0; j < numberOfLadders; j++ )
		{
			cin >> fromSquare >> toSquare;
			lookup[fromSquare]->ladderExit = lookup[toSquare];
			lookup[fromSquare]->squareType = LADDER_HEAD;
		}

		cin >> numberOfChutes;
		for( int j = 0; j < numberOfChutes; j++ )
		{
			cin >> fromSquare >> toSquare;
			lookup[fromSquare]->chuteExit = lookup[toSquare];
			lookup[toSquare]->chuteHead = lookup[fromSquare];
			lookup[fromSquare]->squareType = CHUTE_HEAD;
			lookup[toSquare]->squareType = CHUTE_EXIT;
		}

		if ( boardIsUnsolvable() )
			cout << -1 << "\n";
		else
			cout << discoverMostOptimalPath() << "\n";

		clearBoardOfChutesAndLadders();
	}
	return 0;
}

void createBoard()
{
	lookup[1] = new Square( 1 );
	Square* currentSquare = lookup[1];
	for ( int i = 2; i <= 100; currentSquare = currentSquare->nextSquare )
	{
		lookup[i] = new Square( i );
		currentSquare->nextSquare = lookup[i++];
	}
}

//First, we want to see if there if no solution to the board.  This only occurs when 6 chute heads are
//		all in a row and there is no ladder that trancends them.  We first look if there are 6 chutes in a row
//		and if there are, we check if there is a ladder that bypasses them.
//
//		true : Board is unsolvable and -1 will be printed
//		false : Board is solvable
bool boardIsUnsolvable()
{
	int numberOfConsecutiveChutes = 0;
	for ( Square* currentSquare = lookup[1]; currentSquare != NULL; currentSquare = currentSquare->nextSquare )
	{
		if ( currentSquare->squareType == CHUTE_HEAD )
			numberOfConsecutiveChutes++;
		else
			numberOfConsecutiveChutes = 0;

		if ( numberOfConsecutiveChutes == 6 ) //If 6 slides in a row are found, check if there is a ladder that bypasses them
		{
			for ( int i = 1; i < currentSquare->squareNumber; i++ ) //If there is a ladder that bypasses them, return that the board is solvable
				if ( lookup[i]->squareType == LADDER_HEAD && lookup[i]->ladderExit->squareNumber > currentSquare->squareNumber ) return false;

			return true; //If no such ladder exists, return that the board is unsolvable
		}
	}
	return false; //Return that the board is solvable
}

int discoverMostOptimalPath()
{
	lookup[100]->movesToWin = 0; //If you have reached square 100, you have won, no need to make another move.
	for ( int i = 99; i > 0; i-- )
	{
		if ( i >= 94 && lookup[i]->squareType != CHUTE_HEAD ) lookup[i]->movesToWin = 1; //Base cases, the tiles that can reach 100 are set to 1 (from the space, they can win in 1 move)
		else if ( lookup[i]->squareType == LADDER_HEAD ) lookup[i]->movesToWin = lookup[i]->ladderExit->movesToWin; //The head of the ladder is assigned the same value as the top of the ladder
		else if( lookup[i]->squareType != CHUTE_HEAD ) lookup[i]->movesToWin = ( std::min( { lookup[i + 1]->movesToWin,lookup[i + 2]->movesToWin, lookup[i + 3]->movesToWin,
																					 lookup[i + 4]->movesToWin, lookup[i + 5]->movesToWin, lookup[i + 6]->movesToWin } ) + 1 );
		else
		{
			vector<Square*> chutesTraversed = vector<Square*>();
			chutesTraversed.push_back( lookup[i] );
			lookup[i]->movesToWin = traverseTheChute( lookup[i]->chuteExit, chutesTraversed, 500 );
		}

		if ( lookup[i]->squareType == CHUTE_EXIT ) lookup[i]->chuteHead->movesToWin = lookup[i]->movesToWin;
	}
		return lookup[1]->movesToWin;
}

//This is a recursive function which basically performs a DFS from the bottom of the snake to find a ladder.
//	aCurrentLocation : Where the game piece currently is
//	aChutesTraversed : This stores the pointers to the head of each snake that we have traversed, each loop we check if we currently
//						stand on a square in this vector, if we have we return with whatever value of the best path we've found
//	aRollsMade		 : 
int traverseTheChute( Square* aCurrentLocation, std::vector<Square*> aChutesTraversed, int aRollsOfBestPathSoFar )
{
	int rollsOfBestPathFoundInThisBranch = 500; //Arbitrary large number of moves
	int stepsTaken = 0;

	//Loop until we see the head of the chute that we went down and return the value of the best path we could find.
	while ( std::find( aChutesTraversed.begin(), aChutesTraversed.end(), aCurrentLocation) == aChutesTraversed.end() )
	{
		if ( aCurrentLocation->squareType == CHUTE_HEAD )
		{
			aChutesTraversed.push_back( aCurrentLocation );
			int diveDeeper = traverseTheChute( aCurrentLocation->chuteExit, aChutesTraversed, aRollsOfBestPathSoFar );
			rollsOfBestPathFoundInThisBranch = diveDeeper < rollsOfBestPathFoundInThisBranch ? (diveDeeper + 1 + stepsTaken / 6) : rollsOfBestPathFoundInThisBranch;
		}
		else if ( aCurrentLocation->squareType == LADDER_HEAD )
		{
			if ( aCurrentLocation->ladderExit->movesToWin < (rollsOfBestPathFoundInThisBranch + stepsTaken / 6) ) rollsOfBestPathFoundInThisBranch = aCurrentLocation->ladderExit->movesToWin + 1 + stepsTaken / 6;
		}

		aCurrentLocation = aCurrentLocation->nextSquare;
		stepsTaken++;
	}

	return rollsOfBestPathFoundInThisBranch;
}

//This clears the board so it is just a plain board with pointers to the next square
void clearBoardOfChutesAndLadders()
{
	for ( Square* currentSpace = lookup[1]; currentSpace != NULL; currentSpace = currentSpace->nextSquare )
	{
		currentSpace->chuteExit = NULL;
		currentSpace->chuteHead = NULL;
		currentSpace->ladderExit = NULL;
		currentSpace->squareType = NORMAL;
		currentSpace->movesToWin = 100;
	}
}
