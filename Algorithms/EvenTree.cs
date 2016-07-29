using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

/*
	………. ,*˜*, ……. ,•°*•, Spencer MK Smith ,•*°•, …… ,*˜*, .……… 
	„*˜……“• •“………'·,¸¸,‘•°. HackerRank .°•',¸¸,·'……..“• •“……˜*„
	“‹„¸¸„ •°`'·.,¸¸,.·‘… Even Tree Solution …'·.,¸¸,.·'´°•„¸¸„›“

	This is my C# solution to Even Tree which can be found here: https://www.hackerrank.com/challenges/even-tree
	
	The solution is much easily understood when we understand this simple principle:
			The number of edges we can remove = The number of even subtrees in the graph
	
	This is true because the tree that we are given as input will ALWAYS have an even number of nodes, therefore,
		taking off any even number of nodes from it will still maintain the even number of nodes in each sub tree.
		Now that we understand this principle, I now tell you that simply, the answer to this problem is the number
		of nodes that has an even number of descendants.  This is true because if you remove the edge above a node
		with an even number of descendants, it will be come an even subtree (because you include itself in the sub tree)

*/
	//Object used to each node in the tree
	class Node
	{
		public int index;
		public int parentNode = -1;
		public int numberOfDescendants = 0;
		public List<int> childNodes;

		public Node( int aIndex )
		{
			index = aIndex;
			childNodes = new List<int>();
		}
	}

	class Solution
	{
		public static Node[] NodeList;

		static void Main( string[] args )
		{
			int numberOfNodes, numberOfEdges;
			
			//Read the input
			string input = Console.ReadLine();
			numberOfNodes = Convert.ToInt32( input.Substring( 0, input.IndexOf( " " ) ) );
			numberOfEdges = Convert.ToInt32( input.Substring( input.IndexOf( " " ) + 1, ( input.Length - 1 ) - input.IndexOf( " " ) ) );

			//Create the array of nodes and instantiate them with their index
			NodeList = new Node[numberOfNodes];
			for( int i = 0; i < numberOfNodes; i++ )
				NodeList[i] = new Node( i );

			//Read the edge input, for each edge add
			for( int i = 0; i < numberOfEdges; i++ )
			{
				string edgeDefinition = Console.ReadLine();
				int spaceIndex = edgeDefinition.IndexOf( " " );
				int to = Convert.ToInt32( edgeDefinition.Substring( 0, spaceIndex ) );
				int from = Convert.ToInt32( edgeDefinition.Substring( spaceIndex + 1, edgeDefinition.Length - spaceIndex - 1 ) );

				//Convert the input to the index in the array
				NodeList[from - 1].childNodes.Add( to - 1 );
				NodeList[to - 1].parentNode = from - 1;
				
				//Traverse up the tree and add one to number of descendants of all of the ancestors of the new node
				for( int currentNodeIndex = (from-1); currentNodeIndex != -1; currentNodeIndex = NodeList[currentNodeIndex].parentNode )
					NodeList[currentNodeIndex].numberOfDescendants++;
			}

			//The number of edges that we can remove is equal to the number of sub trees with an even number of nodes.
			//This means that this number is equal to the number of nodes with an odd number of descendants because
			//	a node with odd descendants + itself is always even.  So count the number of nodes with odd descendants and
			//	return it
			int answer = 0;
			foreach( Node node in NodeList )
				if( node.numberOfDescendants % 2 == 1 ) answer++;

			Console.WriteLine( answer - 1 );

		}
	}