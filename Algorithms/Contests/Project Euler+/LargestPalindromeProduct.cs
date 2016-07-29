using System;
using System.Collections.Generic;
using System.IO;

/*
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘… Project Euler #4  …'·.,¸¸,.·'´°•„¸¸„›“

    This challenge is from the Project Euler contest found here: https://www.hackerrank.com/contests/projecteuler/challenges/euler004
    
    We must find a palindrome number which is less than the input that can be created by
    multiplying two 3 digit numbers.  I do this by subtracting one from the input and if it is
    a palindrome number, I will check if the number can be created by multiplying two three digit
    numbers.  Many people I have seen have done this by having two loops from 100-999 and multiplying
    every possible value.  This is inefficient, in my function I only divide the number by 100-999
    and checking if the value is a whole number, thus providing the information that we need that
    the number is a palindrome and can be constructed by multiplying two 3 digit integers

*/
	class Solution
	{
		static void Main( string[] args )
		{
			int numberOfTestCases = Convert.ToInt32( Console.ReadLine() );

			for(int i = 0; i < numberOfTestCases; i++)
			{
				int inputNumber = Convert.ToInt32( Console.ReadLine() );

				for( int j = inputNumber - 1; j > 101100; j-- )
				{
					if( isPalindrome( j ) && isDivisibleByTwoThreeDigitNumbers( j ) )
					{
						Console.WriteLine( j );
						break;
					}
				}
			}
		}

        //Creates a string from the number, reverses the string and checks if they are equal
		static bool isPalindrome( int number )
		{
			string numberString = Convert.ToString( number );
			char[] reversedString = numberString.ToCharArray();
			Array.Reverse( reversedString );
			return numberString.Equals( new string( reversedString ) );
		}

        //Loops from 999-100 and checks if it divides evenly into the number, if it does it returns true
		static bool isDivisibleByTwoThreeDigitNumbers(int number)
		{
			for( double i = 999; i >= 100; i-- )
			{
				double quotient = number / i;
				if( ( quotient % 1 == 0 ) && quotient >= 100 && quotient <= 999 )
					return true;
			}

			return false;
		}
	}  