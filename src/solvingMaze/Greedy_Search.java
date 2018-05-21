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

public class Greedy_Search{


static void Greedy_Search(Node Start, Node Goal, Node maze[][], int horizontal_rows, int vertical_cols)
{
	System.out.println("Starting a Greedy Search");
	System.out.println();
	int numOfSteps = 0;
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
	int horizontal_rows = horizontal; //grab this from the passed in information
	int vertical_col = vertical;	  //grab this from the passed information
	int xG = goal.x;
	int xY = goal.y;
	float heuristic_start = (x - xG)- (y - xY); //The idea is that the smaller the number is closer
	double heuristic_North = 0;
	double heuristic_West = 0;
	double heuristic_South = 0;
	double heuristic_East = 0;
	
	if(x - 1 >= 0) //Since 0 is the absolute "max" we can reach we can assume if it's less then zero we hit the "max"
	{
 	 heuristic_North = Math.pow( ((x-1) - xG),2) + Math.pow(( y - xY),2); 	//North Heuristic, store the value then Sqrt later 
	}
	else if (x - 1 < 0) //if it's below zero we want to make sure it can't be used in a heuristic
	{
		heuristic_North =  Double.NaN;
	}

	if(y - 1 >= 0)
	{
 	 heuristic_West = Math.pow((x - xG),2) + Math.pow(((y-1) - xY),2); 	//West Heuristic, store the value then Sqrt it later

	}
	else if (y - 1 < 0)
	{
		heuristic_West = Double.NaN;
	}
	
	if (x + 1 <= horizontal_rows)	//if user picks 10, it means we have 0-9 columns. Having it be less than means we're in the maze
	{
 	 heuristic_South = Math.pow(((x+1) - xG),2) - Math.pow(( y - xY),2);	//South Heuristic
	}
	else  //if we reach or exceed start col we're out of bounds
	{
		heuristic_South = Double.NaN;
	}
	
	if (y + 1 < vertical_col) //if user picks 10, it means we have 0-9 Rows. Having it be less than means we're in the maze
	{
 	 heuristic_East = Math.pow((x - xG),2) - Math.pow(((y+1) - xY),2); 	//East Heuristic
	}
	else if (y + 1 >= vertical_col)
	{
		heuristic_East = Double.NaN;
	}
	
 	System.out.println("Starting Heuristic is " + heuristic_start);
 	System.out.println("North Heuristic is " + Math.sqrt(Math.abs(heuristic_North)));
 	System.out.println("West heuristic is " + Math.sqrt(Math.abs(heuristic_West)));
 	System.out.println("South heuristic is " + Math.sqrt(Math.abs(heuristic_South)));
 	System.out.println("East Heuristic is " + Math.sqrt(Math.abs(heuristic_East))); //TODO this acted up
 	System.out.println();
 	
	start.visited = true;
	queue.add(start);		//should add Start to the queue (which contains X Y and String)
	pq.add(start); 			//this adds our start node to the priority queue
	
	
	if (start.x - 1 >= 0) //test north
	{		
		Node test = maze[start.x-1][y];
		Node.addDistance(test, Math.sqrt(Math.abs(heuristic_North))); //TODO test this, seems to work
		System.out.println("Test Value is " + test);
		
		
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
	
	if (start.y - 1 >= 0)//This tests the west
	{		
		Node test = maze[x][start.y-1];
		Node.addDistance(test, Math.sqrt(Math.abs(heuristic_West))); //TODO test this, seems to work
		System.out.println("Test Value is " + test);


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
	
	if (start.x + 1 < horizontal_rows)//Test south
	{	
		Node test = maze[start.x+1][y];
		Node.addDistance(test, Math.sqrt(Math.abs(heuristic_South))); //TODO test this, seems to work
		System.out.println("Test Value is " + test);
		
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
	

if (start.y + 1 < vertical_col) //test east
{	
	Node test = maze[x][start.y+1];
	Node.addDistance(test, Math.sqrt(Math.abs(heuristic_East))); //TODO test this, seems to work
	System.out.println("Test Value is " + test);
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
//TODO this seems to place the nodes in the correct way for a prioirty queue (tested two values and east and south got popped for the first one)
}



private static void GreedyMazeStartSecond(Node Start, Node Goal, Node[][] maze, Queue queue, int numOfSteps, Queue PathtoGoal, boolean goalFound,Queue visted, int horizonal, int vertical, PriorityQueue<Node> pq) {
	int horizontal_rows = horizonal;
	int vertical_col = vertical;
	double heurstic_North = 1; //This isnt affecting second run distances
	double heuristic_West = 0;
	double heuristic_South = 0;
	double heuristic_East = 0;
	
	Node first = (Node) pq.remove();	//Gets rid of start we dont need it anymore
	numOfSteps++;
	
	PathtoGoal.add(first); //TODO change this to PQ since it might act up
	

	while (!pq.isEmpty())//while the Queue is NOT empty
	{	
		if (first.x == Goal.x && first.y == Goal.y)
		{
			System.out.println("Goal was on top of start: Finished");
			goalFound = true;
			break;
		}
		
		Node Crawler = (Node) pq.remove();//We pop the Queue into a new Node value, since it came from pq it should have distance (not that it matters)
		PathtoGoal.add(Crawler);		//TODO might need to change this since this is a normal Queue
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
		

		System.out.println("Popped Value is: " + Crawler);	//Testing to make sure it gets the right node Value
		System.out.println();
		
			if (Crawler.x - 1 >= 0)//this to test North, this will not proceed if it reaches of map (CONFIRMED)
			{		
					Node QueueAdder = maze[Crawler.x - 1][Crawler.y]; 	//make a new node that gets the values of whatever node is North of "Crawler"
					heurstic_North = Math.pow((QueueAdder.x - Goal.x),2) + Math.pow(( QueueAdder.y - Goal.y),2); //This should get the QueueAdder (which already checked North)
					heurstic_North = Math.abs(Math.sqrt(heurstic_North));	//ABS just in case																						  //So now all we need \to do is sqrt it
					Node.addDistance(QueueAdder, heurstic_North); //TODO This is where I left off for lunch

			if(QueueAdder.visited == true || QueueAdder.is_forbid == true)
			{
				System.out.println("This North Node has been visisted or is forbidden, will not revisit ");
			}

			else if (QueueAdder.is_forbid == false || QueueAdder.visited == false)
			{
				System.out.println("Testing North Version 2: Node is now " + QueueAdder);
				QueueAdder.visited = true;
				QueueAdder.in_fringe = true;
				QueueAdder.parent = Crawler;
				pq.add(QueueAdder);
			}
			}

			if (Crawler.y - 1 >= 0)//test west, this will not proceed if it reaches end of map
			{		
				Node QueueAdder = maze[Crawler.x][Crawler.y-1];
				heuristic_West = Math.pow((QueueAdder.x - Goal.x),2) + Math.pow(( QueueAdder.y - Goal.y),2); //This should get the QueueAdder (which already checked North)
				heuristic_West = Math.abs(Math.sqrt(heuristic_West));	//ABS just in case																						  //So now all we need \to do is sqrt it
				Node.addDistance(QueueAdder, heuristic_West); 

				if(QueueAdder.visited == true || QueueAdder.is_forbid == true)
				{
					System.out.println("This West node has been visisted, will not revisit ");
				}
				else if (QueueAdder.is_forbid == false || QueueAdder.visited == false)
				{
					System.out.println("Testing West Version 2: Node is now " + QueueAdder);
					QueueAdder.visited = true;
					QueueAdder.in_fringe = true;
					QueueAdder.parent = Crawler;
					pq.add(QueueAdder);
				}
			}	
			//TODO when Start is set at (0,0) it will visit 01 and 10. it should only visit one of these
			if (Crawler.x + 1 < horizontal_rows) //test south, this test should skip south(WORKS)
			{
				Node QueueAdder = maze[Crawler.x+1][Crawler.y];
				heuristic_South = Math.pow((QueueAdder.x - Goal.x),2) + Math.pow(( QueueAdder.y - Goal.y),2); //This should get the QueueAdder (which already checked North)
				heuristic_South = Math.abs(Math.sqrt(heuristic_South));	//ABS just in case																						  //So now all we need \to do is sqrt it
				Node.addDistance(QueueAdder, heuristic_South); //TODO This is where I left off for lunch

			if(QueueAdder.visited == true || QueueAdder.is_forbid == true )
			{
			System.out.println("This South Node has been visisted, will not revisit ");
			}
			else if (QueueAdder.is_forbid == false || QueueAdder.visited == false)
			{
				System.out.println("Testing South Version 2 : Node is now " + QueueAdder);
				QueueAdder.visited = true;
				QueueAdder.in_fringe = true;
				QueueAdder.parent = Crawler;
				pq.add(QueueAdder);
			}
			}


	if (Crawler.y + 1 < (vertical_col))//test east, confirm it will hit end of wall (TESTING)
	{	
		Node QueueAdder = maze[Crawler.x][Crawler.y+1];
		heuristic_East = Math.pow((QueueAdder.x - Goal.x),2) + Math.pow(( QueueAdder.y - Goal.y),2); //This should get the QueueAdder (which already checked North)
		heuristic_East = Math.abs(Math.sqrt(heuristic_East));	//ABS just in case																						  //So now all we need \to do is sqrt it
		Node.addDistance(QueueAdder, heuristic_East); //TODO This is where I left off for lunch

		if(QueueAdder.visited == true || QueueAdder.is_forbid == true)
		{
			System.out.println("This East node has been visisted, will not revisit ");
			System.out.println("");
		}
		else if (QueueAdder.is_forbid == false || QueueAdder.visited == false)
		{
			System.out.println("Testing East Version 2: Node is now " + QueueAdder);
			System.out.println("");
			QueueAdder.visited = true;
			QueueAdder.in_fringe = true;
			QueueAdder.parent = Crawler;
			pq.add(QueueAdder);

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
	 System.out.println("Our Greedy Search path is ");
	 System.out.print(PathtoGoal + "");
	 System.out.println();
	 System.out.println();
	 System.out.println("Our Final path is ");
	 System.out.print(visted + "");
	 System.out.println();
	 System.out.println();

	 
	 for (Node[] z : maze) 	//This prints the maze
	 	{
		for (Node B : z) 
			{ 
			System.out.print(B + "\t"); 
			} 
		
		System.out.println("\n"); 
	 	} 
	}
}

