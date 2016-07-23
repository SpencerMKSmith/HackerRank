#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

/* Forward Declarations */
struct Square;
void createBoard();
bool checkIfNoSolution();
int discoverMostOptimalPath();
int traverseTheChute( Square* aCurrentLocation, std::vector<Square*> aChutesTraversed, int aRollsMade, int aRollsOfBestPathSoFar );


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

	for ( int i = 0; i < numberOfCases; i++ )
	{
		createBoard();

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

		if ( checkIfNoSolution() )
			cout << -1 << "\n";
		else
			cout << discoverMostOptimalPath() << "\n";
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
//		all in a row, this means that it is impossible to cross the chutes with any roll and would render
//		the player in an infinite loop.
bool checkIfNoSolution()
{
	int numberOfConsecutiveChutes = 0;
	for ( Square* currentSquare = lookup[1]; currentSquare != NULL; currentSquare = currentSquare->nextSquare )
	{
		if ( currentSquare->squareType == CHUTE_HEAD )
			numberOfConsecutiveChutes++;
		else
			numberOfConsecutiveChutes = 0;

		if ( numberOfConsecutiveChutes == 6 )
			return true;
	}
	return false;
}

int discoverMostOptimalPath()
{
	lookup[100]->movesToWin = 0;
	for ( int i = 99; i > 0; i-- )
	{
		if ( i >= 94 && lookup[i]->squareType != CHUTE_HEAD ) lookup[i]->movesToWin = 1;
		else if ( lookup[i]->squareType == LADDER_HEAD ) lookup[i]->movesToWin = lookup[i]->ladderExit->movesToWin;
		else if( lookup[i]->squareType != CHUTE_HEAD ) lookup[i]->movesToWin = ( std::min( { lookup[i + 1]->movesToWin,lookup[i + 2]->movesToWin, lookup[i + 3]->movesToWin,
																					 lookup[i + 4]->movesToWin, lookup[i + 5]->movesToWin, lookup[i + 6]->movesToWin } ) + 1 );
		else
		{
			vector<Square*> chutesTraversed = vector<Square*>();
			chutesTraversed.push_back( lookup[i] );
			lookup[i]->movesToWin = traverseTheChute( lookup[i]->chuteExit, chutesTraversed, 0, 100 );
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
int traverseTheChute( Square* aCurrentLocation, std::vector<Square*> aChutesTraversed, int aRollsMade, int aRollsOfBestPathSoFar )
{
	int rollsOfBestPathFoundInThisBranch = 100;
	int stepsTaken = 0;
	while ( std::find( aChutesTraversed.begin(), aChutesTraversed.end(), aCurrentLocation) == aChutesTraversed.end() )
	{
		if ( aCurrentLocation->squareType == CHUTE_HEAD )
		{
			aChutesTraversed.push_back( aCurrentLocation );
			int diveDeeper = traverseTheChute( aCurrentLocation->chuteExit, aChutesTraversed, aRollsMade + 1, aRollsOfBestPathSoFar );
		}
		else if ( aCurrentLocation->squareType == LADDER_HEAD )
		{
			if ( aCurrentLocation->ladderExit->movesToWin < rollsOfBestPathFoundInThisBranch ) rollsOfBestPathFoundInThisBranch = aCurrentLocation->ladderExit->movesToWin + 1;
		}

		aCurrentLocation = aCurrentLocation->nextSquare;
		stepsTaken++;
	}

	return rollsOfBestPathFoundInThisBranch;
}
