package solvingMaze;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
//Got number of steps: 15 for Assignment 1
//Final path (5,2) (5,1) (6,1) (7,1) (8,1) (9,1)(9,2) (9,3) (8,3) (8,4)
// Greedy Path was: [(8,4)(S), (8,5)(V), (7,4)(V), (7,3)(V), (8,3)(V), (9,4)(V), (7,5)(V), (9,3)(V), (9,2)(V), (9,1)(V), (8,1)(V), (7,1)(V), (6,1)(V), (5,1)(V), (5,2)(G)]

public class A_Star_Search{


static void Star_Search(Node Start, Node Goal, Node maze[][], int horizontal_rows, int vertical_cols)
{
	System.out.println("Starting a A* Search");
	System.out.println();
	int numOfSteps = 0;
	//This will be used to hold the changing heuristic values
	float heuristic = 0;
	
	boolean goalFound = false;
		
	Queue queue = new LinkedList();
	Queue PathtoGoal = new LinkedList();
	Queue visted = new LinkedList();
	PriorityQueue<Node> pq = new PriorityQueue<Node>(); //this is our priority queue, using our Node class

	
	
	GreedyMazeStartUp(Start,Goal,maze,queue,numOfSteps,PathtoGoal,goalFound,visted,horizontal_rows,vertical_cols,pq);

}	


@SuppressWarnings("unchecked")
private static void GreedyMazeStartUp(Node start,Node goal, Node maze[][], Queue queue, int numOfSteps, Queue PathtoGoal, boolean goalFound, Queue visted,int horizontal, int vertical, PriorityQueue<Node> pq) 
{
	int x = start.x;
	int y = start.y;
	int horizontal_rows = horizontal;
	int vertical_col = vertical;	  
	int xG = goal.x;
	int yG = goal.y;
	
	double heuristic_North = 0;
	double heuristic_West = 0;
	double heuristic_South = 0;
	double heuristic_East = 0;
	double complete_Cost = 0;
	
	//Since 0 is the absolute "max" we can reach we can assume if it's less then zero we hit the "max"
	if(x - 1 >= 0)
	{
 	 heuristic_North = Math.pow( ((x-1) - xG),2) + Math.pow(( y - yG),2); 	//North Heuristic, store the value then Sqrt later 
	}
	else if (x - 1 < 0) //if it's below zero we want to make sure it can't be used in a heuristic
	{
		heuristic_North =  Double.NaN;
	}

	//Since 0 is the minimum value we want to stop at 0
	if(y - 1 >= 0)
	{
 	 heuristic_West = Math.pow((x - xG),2) + Math.pow(((y-1) - yG),2); 	//West Heuristic, store the value then Sqrt it later

	}
	else if (y - 1 < 0)
	{
		heuristic_West = Double.NaN;
	}
	
	//if user picks 10, it means we have 0-9 columns. Having it be less than means we're in the maze
	if (x + 1 <= horizontal_rows)
	{
 	 heuristic_South = Math.pow(((x+1) - xG),2) - Math.pow(( y - yG),2);	//South Heuristic
	}
	else  //if we reach or exceed start col we're out of bounds
	{
		heuristic_South = Double.NaN;
	}
	
	//if user picks 10, it means we have 0-9 Rows. Having it be less than means we're in the maze
	if (y + 1 < vertical_col) 
	{
 	 heuristic_East = Math.pow((x - xG),2) - Math.pow(((y+1) - yG),2); 	//East Heuristic
	}
	else if (y + 1 >= vertical_col)
	{
		heuristic_East = Double.NaN;
	}
	
// 	System.out.println("North Cost is " + start.northCost);
// 	System.out.println("North Heuristic is " + Math.sqrt(Math.abs(heuristic_North)));
// 	System.out.println("North Total cost is " + (Math.sqrt(Math.abs(heuristic_North)) + start.northCost));
// 	System.out.println();
// 	
// 	System.out.println("West Cost is " + start.westCost);
// 	System.out.println("West heuristic is " + Math.sqrt(Math.abs(heuristic_West)));
// 	System.out.println("West total cost is " + (Math.sqrt(Math.abs(heuristic_West))+ start.westCost));
// 	System.out.println();
//
// 	System.out.println("South Cost is " + start.southCost);
// 	System.out.println("South heuristic is " + Math.sqrt(Math.abs(heuristic_South)));
// 	System.out.println("South total cost is " + (Math.sqrt(Math.abs(heuristic_South)) + start.southCost));
// 	System.out.println();
// 	
// 	System.out.println("East Cost is " + start.eastCost);
// 	System.out.println("East Heuristic is " + Math.sqrt(Math.abs(heuristic_East))); 
// 	System.out.println("East total cost is " + (Math.sqrt(Math.abs(heuristic_East)) + start.eastCost)); 
//
// 	System.out.println();
 	
 	
	start.visited = true;
	queue.add(start);		
	pq.add(start); 			
	
	
	if (start.x - 1 >= 0) //test north
	{		
		Node test = maze[start.x-1][y];
		//Still need to make sure this is square rooted 
		complete_Cost = Math.sqrt(Math.abs(heuristic_North)) + start.northCost;
		Node.addDistance(test, complete_Cost); 
		//this version of the method just lets us add in a new cost value for the node
		Node.addTotalCost(test, start.northCost);
		
			if(test.is_forbid == true)
				{
					System.out.println("This Northern Path is forbidden");
				}
			else 
				{
					System.out.println("Testing North: Node is now " + test);
					
					test.visited = true;
					test.parent = null;
					pq.add(test);		
				}
	}
	
	//This tests the west
	if (start.y - 1 >= 0)
	{		
		Node test = maze[x][start.y-1];
		complete_Cost = Math.sqrt(Math.abs(heuristic_West)) + start.westCost;
		Node.addDistance(test, complete_Cost);
		Node.addTotalCost(test, start.westCost);

			if(test.is_forbid == true)
			{
				System.out.println("This Western Path is forbidden");
			}
			else if (test.is_forbid == false)
			{
				System.out.println("Testing West: Node is now " + test);
				test.visited = true;
				test.parent = null;

				pq.add(test);
			}
	}
	
	//Test south
	if (start.x + 1 < horizontal_rows)
	{	
		Node test = maze[start.x+1][y];
		complete_Cost = Math.sqrt(Math.abs(heuristic_South)) + start.southCost;
		Node.addDistance(test, complete_Cost); //TODO test this, seems to work
		Node.addTotalCost(test, start.southCost);

			if(test.is_forbid == true)
			{
				System.out.println("This Southern Path is forbidden");
			}
		else if (test.is_forbid == false)
			{
				System.out.println("Testing South: Node is now " + test);
				test.visited = true;
				test.parent = null;

				pq.add(test);
			}
	}
	
	 //test east
if (start.y + 1 < vertical_col)
{	
	Node test = maze[x][start.y+1];
	complete_Cost = Math.sqrt(Math.abs(heuristic_East)) + start.eastCost;
	Node.addDistance(test, complete_Cost); //TODO test this, seems to work
	Node.addTotalCost(test, start.eastCost);



	if(test.is_forbid == true)
		{
			System.out.println("This Eastern Path is forbidden");
			System.out.println();
		}
		else if (test.is_forbid == false)
		{
			System.out.println("Testing East: Node is now " + test);
			System.out.println("");
			test.visited = true;
			test.parent = null;

			pq.add(test);
		}
		
		
}

GreedyMazeStartSecond(start,goal,maze,queue,numOfSteps,PathtoGoal,goalFound,visted,horizontal_rows,vertical_col,pq); //this calls our second method
}


//TODO make sure older costs are calculated as well
private static void GreedyMazeStartSecond(Node Start, Node Goal, Node[][] maze, Queue queue, int numOfSteps, Queue PathtoGoal, boolean goalFound,Queue visted, int horizonal, int vertical, PriorityQueue<Node> pq) {
	int horizontal_rows = horizonal;
	int vertical_col = vertical;
	
	double heurstic_North =  0; 
	double heuristic_West =  0;
	double heuristic_South = 0;
	double heuristic_East =  0;
	double complete_Cost = 	 0;
	
	//We can get rid of start ASAP to see if Goal = Start 
	Node first = (Node) pq.remove();
	
	numOfSteps++;
	
	PathtoGoal.add(first);
	
	//Loop through priority Queue
	while (!pq.isEmpty())
	{	
		if (first.x == Goal.x && first.y == Goal.y)
		{
			System.out.println("Goal was on top of start: Finished");
			goalFound = true;
			break;
		}
		
		//We pop the Queue and get the Node. since it came from pq it should have distance (not that it matters)
		//Crawler is the main node that will be used to "Crawl" through the maze
		//QueueAdder only exists to add nodes into pq so that they can become a "Crawler"
		Node Crawler = (Node) pq.remove();
		//Seperate Queue to follow our path
		PathtoGoal.add(Crawler);		
		numOfSteps++;
		
		if (Crawler == Goal)
		{
			System.out.println("WE FOUND THE GOAL " + Goal);
			goalFound = true;
			visted.add(Goal);
			
			while (Crawler.parent != null)
			{
			Crawler = Crawler.getParent();
			visted.add(Crawler);
			}
			visted.add(Start);

			break;
		}
		
		Crawler.s = "V";
		
		//This will be the nodes at the front of the PQ
		System.out.println("Popped Value is: " + Crawler);
		System.out.println();
		
		//make sure the crawler isn't going out of bounds
			if (Crawler.x - 1 >= 0)
			{		
					//QueueAdder is a node that exists only to put values into PQ so they can become the "Crawler"
					//This will grab whatever node is north of Node Crawler and assign as QueueAdder
					Node QueueAdder = maze[Crawler.x - 1][Crawler.y]; 	
					heurstic_North = Math.pow((QueueAdder.x - Goal.x),2) + Math.pow(( QueueAdder.y - Goal.y),2); 
					heurstic_North = Math.abs(Math.sqrt(heurstic_North));																						
					complete_Cost = heurstic_North + QueueAdder.northCost + Crawler.accumulated_Cost;
					
					//Since QueueAdder is the current node we take the old crawler values cost
					//Then we add the northenCost and give that new value to Queue Adder
					Node.addTotalCost(QueueAdder,Crawler, QueueAdder.northCost);
					Node.addDistance(QueueAdder, complete_Cost); //TODO This is where I left off for lunch

			if(QueueAdder.visited == true || QueueAdder.is_forbid == true)
			{
				System.out.println("This North Node has been visisted or is forbidden, will not visit ");
				System.out.println();
			}

			else if (QueueAdder.is_forbid == false || QueueAdder.visited == false)
			{
				System.out.println("Testing North Version 2: Node is now " + QueueAdder);
//					System.out.println("The North Heuristic is " + heurstic_North);
//					System.out.println("The accumulated cost of this node is " + Crawler.accumulated_Cost);
//					System.out.println("The Total traveled cost of this node is " + complete_Cost);

				QueueAdder.visited = true;
				QueueAdder.in_fringe = true;
				QueueAdder.parent = Crawler;
				pq.add(QueueAdder);
				System.out.println();
			}
			}
			
			//Test WEST
			if (Crawler.y - 1 >= 0)
			{		
				Node QueueAdder = maze[Crawler.x][Crawler.y-1];
				heuristic_West = Math.pow((QueueAdder.x - Goal.x),2) + Math.pow(( QueueAdder.y - Goal.y),2); 
				heuristic_West = Math.abs(Math.sqrt(heuristic_West));																							 
				complete_Cost = heuristic_West + QueueAdder.westCost + Crawler.accumulated_Cost;
				
				Node.addTotalCost(QueueAdder, Crawler,QueueAdder.westCost);
				Node.addDistance(QueueAdder, complete_Cost); 

				if(QueueAdder.visited == true || QueueAdder.is_forbid == true)
				{
					System.out.println("This West node has been visisted, will not revisit ");
					System.out.println();

				}
				else if (QueueAdder.is_forbid == false || QueueAdder.visited == false)
				{
					System.out.println("Testing West Version 2: Node is now " + QueueAdder);
//					System.out.println("The west Heuristic is " + heuristic_West);
//					System.out.println("The accumulated cost of this node is " + QueueAdder.accumulated_Cost);
//					System.out.println("The Total cost this node has traveled is " + complete_Cost);
					QueueAdder.visited = true;
					QueueAdder.in_fringe = true;
					QueueAdder.parent = Crawler;
					pq.add(QueueAdder);
					System.out.println();
				}
			}	
			//TODO when Start is set at (0,0) it will visit 01 and 10. it should only visit one of these
			//Test the southern Node
			if (Crawler.x + 1 < horizontal_rows) 
			{
				Node QueueAdder = maze[Crawler.x+1][Crawler.y];
				heuristic_South = Math.pow((QueueAdder.x - Goal.x),2) + Math.pow(( QueueAdder.y - Goal.y),2); 
				heuristic_South = Math.abs(Math.sqrt(heuristic_South));																						 
				complete_Cost = heuristic_South + QueueAdder.southCost + Crawler.accumulated_Cost;
				
				Node.addTotalCost(QueueAdder,Crawler, QueueAdder.southCost);
				Node.addDistance(QueueAdder, complete_Cost);

			if(QueueAdder.visited == true || QueueAdder.is_forbid == true )
			{
			System.out.println("This South Node has been visisted, will not revisit ");
			System.out.println();

			}
			else if (QueueAdder.is_forbid == false || QueueAdder.visited == false)
			{
				System.out.println("Testing South Version 2 : Node is now " + QueueAdder);
//				System.out.println("Southern Heuristic is " + heuristic_South);
//				System.out.println("The accumulated cost of this node is " + QueueAdder.accumulated_Cost);
//				System.out.println("The Total cost of this node is " + complete_Cost);
				QueueAdder.visited = true;
				QueueAdder.in_fringe = true;
				QueueAdder.parent = Crawler;
				pq.add(QueueAdder);
				System.out.println();
			}
			}

			//Test east
			if (Crawler.y + 1 < (vertical_col))
				{	
		Node QueueAdder = maze[Crawler.x][Crawler.y+1];
		heuristic_East = Math.pow((QueueAdder.x - Goal.x),2) + Math.pow(( QueueAdder.y - Goal.y),2); //This should get the QueueAdder (which already checked North)
		heuristic_East = Math.abs(Math.sqrt(heuristic_East));	//ABS just in case																						  //So now all we need \to do is sqrt it
		complete_Cost = heuristic_East + QueueAdder.eastCost + Crawler.accumulated_Cost;
		Node.addTotalCost(QueueAdder,Crawler, QueueAdder.eastCost);
		Node.addDistance(QueueAdder, complete_Cost);

				if(QueueAdder.visited == true || QueueAdder.is_forbid == true)
					{
						System.out.println("This East node has been visisted, will not revisit ");
						System.out.println("");
					}
				else if (QueueAdder.is_forbid == false || QueueAdder.visited == false)
					{
			System.out.println("Testing East Version 2: Node is now " + QueueAdder);
//			System.out.println("Eastern Heuristic is " + heuristic_East);
//			System.out.println("The accumulated cost of this node is " + QueueAdder.accumulated_Cost);
//			System.out.println("The Total cost is of this node is" + complete_Cost);
			System.out.println("");
			QueueAdder.visited = true;
			QueueAdder.in_fringe = true;
			QueueAdder.parent = Crawler;
			pq.add(QueueAdder);
			System.out.println();

	if (Crawler.y + 1 >= (horizontal_rows-1))
	{
		System.out.println();
	}


		}
	}
		}

	getResults(queue,PathtoGoal,visted,goalFound,numOfSteps,maze);

}
private static void getResults(Queue queue, Queue PathtoGoal, Queue visted, boolean goalFound, int numOfSteps, Node maze[][]) {
	if(queue.isEmpty() && goalFound == false)
	{
		System.out.println("Could not find or access the Goal");
	}
	 
	 System.out.println("Number of Steps taken " + numOfSteps);
	 System.out.println("Our A* path is ");
	 System.out.print(PathtoGoal + "");
	 System.out.println();
	 System.out.println();
	 System.out.println("Our Final path is ");
	 System.out.print(visted + "");
	 System.out.println();
	 System.out.println();

	//This prints the maze
	 for (Node[] z : maze) 	
	 	{
		for (Node B : z) 
			{ 
			System.out.print(B + "\t"); 
			} 
		
		System.out.println("\n"); 
	 	} 
	}
}

