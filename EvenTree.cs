using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;


//	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
//	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
//	“‹„¸¸„ •°`'·.,¸¸,.·‘… Even Tree Solution …'·.,¸¸,.·'´°•„¸¸„›“
//
// Problem can be found here: https://www.hackerrank.com/challenges/even-tree
//
//	Given a simply connected graph (a tree), we are asked to figure out the maximum
//	number of edges that can be removed from the tree such that each resulting tree
//	contains an even number of verticies.
//
//  Solution in a nut shell:  Given a tree of an even number of verticies, we know
//	that we can remove an edge of the tree that connects to a subtree that contains 
//	an even number of verticies as this will produce two even trees.  Keep in mind
//	that the solution does not ask for which verticies will specifically will be
//	removed, just for the number that can be removed.  This number can be found by
//	determining how many subtrees exist with an even number of verticies.  This is
//	done below by recursively traversing the tree and finding the number of descendants
//	that each node has, the number of verticies that have an odd number of descendants
//	is the answer to the problem.

namespace HREvenTree
{
	class Node
	{
		public int index;
		public int parentNode;
		public int numberOfDescendants;
		public List<int> childNodes;

		public Node( int aIndex )
		{
			index = aIndex;
			parentNode = -1;
			childNodes = new List<int>();
		}
	}

	class Solution
	{
		public static Node[] NodeList;

		static void Main( string[] args )
		{
			int numberOfNodes, numberOfEdges, rootIndex = -1;
			string input = Console.ReadLine();

			numberOfNodes = Convert.ToInt32( input.Substring( 0, input.IndexOf( " " ) ) );
			numberOfEdges = Convert.ToInt32( input.Substring( input.IndexOf( " " ) + 1, ( input.Length - 1 ) - input.IndexOf( " " ) ) );

			//Instantiate the array of nodes
			NodeList = new Node[numberOfNodes];
			for( int i = 0; i < numberOfNodes; i++ )
				NodeList[i] = new Node( i - 1 );

			//Read the input which defines the edges of the tree and store them in the NodeList structure
			for( int i = 0; i < numberOfEdges; i++ )
			{
				string edgeDefinition = Console.ReadLine();
				int spaceIndex = edgeDefinition.IndexOf( " " );
				int to = Convert.ToInt32( edgeDefinition.Substring( 0, spaceIndex ) );
				int from = Convert.ToInt32( edgeDefinition.Substring( spaceIndex + 1, edgeDefinition.Length - spaceIndex - 1 ) );

				NodeList[from - 1].childNodes.Add( to - 1 );
				NodeList[to - 1].parentNode = from - 1;
			}

			//I do not assume that the first node is the root, I look for the root node (the one that has no parent)
			foreach(Node node in NodeList)
				if( node.parentNode == -1 ) rootIndex = node.index;

			getNumberOfDescendants( rootIndex );

			//The answer to this problem is equal to the number of nodes that has an odd number of descendants
			int answer = 0;
			foreach( Node node in NodeList )
				if( node.numberOfDescendants % 2 == 1 ) answer++;

			//Print the answer (subtracted by 1 because the variable will count the root)
			Console.WriteLine( answer - 1 );

		}

		//This recursive function will traverse the tree using depth first search and determine how many descendants each
		//		node has.
		public static int getNumberOfDescendants( int indexOfNode )
		{
			int descendants = 0;
			for( int i = 0; i < NodeList[indexOfNode].childNodes.Count; i++ )
				descendants += getNumberOfDescendants( NodeList[indexOfNode].childNodes[i] );

			descendants += NodeList[indexOfNode].childNodes.Count;

			NodeList[indexOfNode].numberOfDescendants = descendants;
			return descendants;
		}
	}
}
