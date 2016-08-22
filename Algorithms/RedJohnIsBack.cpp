#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

/*
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘…  Red John Is back    …'·.,¸¸,.·'´°•„¸¸„›“

	https://www.hackerrank.com/challenges/red-john-is-back
	
	This dynamic programming challenge is surprisingly easy to solve
	once the problem is broken down and understood.  Simply, the answer
	can be reached by using a modified fibonacci solution.  The number of
	possible ways to arrange the blocks given the width N and an array of
	previously calculated block arrangements, M[] is M[i-1] + M[i-4].
	
	Now that we have the number of block arrangements for each given N,
	the solution asks for the number of prime numbers less than the calculated
	value of arrangements.  If the number of arrangements is 10, then the answer is
	4 because 2, 3, 5, and 7 are all prime. So, for simplicity I created an array that
	will tell us whether a number if prime or not.  To complete this in an efficient
	manner is utilize the sieve of eratosthenes.
	
	After the number of arrangements given N and the prime numbers up to 1 million
	are discovered, we then create a lookup array that will tell us how many prime
	numbers are less than the index in the array.
	
	Using these lookups all we need to do to find the output is simply read the input, Now
	which is the width of the wall, figure out how many arrangements can be made with
	that width, and then output how many primes are less than that number.
	
	The use of the lookup arrays is to minimize calculating the same value multiple times
	and can be used because we know that there are upper constraints.

*/
long fibArray[42];
long isNotAPrime[1000001] = {false};
long primeSum[1000001] = {0};

void fillFibArray()
{
    fibArray[1] = 1;
    fibArray[2] = 1;
    fibArray[3] = 1;
    fibArray[4] = 2;
    for( int i = 5; i < 42; i ++ )
        fibArray[i] = fibArray[i - 1] + fibArray[i - 4];
}



void discoverPrimes()
{
    for( int i = 2; i < 10000; i++ )
    {
        if( !isNotAPrime[i] ) //If it is prime
        {
            for(int j  = i*i; j < 1000001; j = j + i)
                isNotAPrime[j] = true;
        }
    }

}

void fillPrimeSumArray()
{
    primeSum[0] = 0;
    primeSum[1] = 0;
    for(int i = 2; i < 1000001; i++)
    {
        if( isNotAPrime[i] )
            primeSum[i] = primeSum[i - 1];
        else
            primeSum[i] = primeSum[i - 1] + 1;
    }
}

int main() {
    int numberOfTestCases;
    cin >> numberOfTestCases;
    
    fillFibArray();
    discoverPrimes();
    fillPrimeSumArray();
    
    //cout << primeSum[10];
    for(int i = 0; i < numberOfTestCases; i++)
    {
        int n;
        cin >> n; //get the width of the wall
        cout << primeSum[ fibArray[ n ] ] << "\n";
    }
    
}
