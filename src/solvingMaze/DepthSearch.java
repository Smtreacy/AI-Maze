package solvingMaze;
//This seems to work for the Assignment 1
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthSearch {
	
	static void depth_Searching(Node start, Node Goal, Node[][] maze, int horizontal_rows, int vertical_col){
		System.out.println("Starting a DFS Search");
		System.out.println();
		
		int x = start.x;
		int y = start.y;
		int numOfSteps = 0;
		
		boolean goalFound = false;
		
		String s = start.s;
		
		Stack stack = new Stack();
		Queue PathtoGoal = new LinkedList();
		Queue visted = new LinkedList();
	
		start.visited = true;
		stack.push(start);		//should add Start to the queue (which contains X Y and String)
		PathtoGoal.add(start);
		
		if (start.y + 1 < vertical_col)//test east
			{		//test east
				Node test = maze[x][start.y + 1];
					if(test.is_forbid == true)
						{
							System.out.println("This Eastern Path is forbidden");
						}
					else if (test.is_forbid == false)
						{
							System.out.println("Testing East: Node is now " + test);
							test.visited = true;
							test.parent = null;
							stack.push(test);
						}
			}
		
		if (start.x + 1 < horizontal_rows)//test south
		{	
			Node test = maze[start.x + 1][y];
			if(test.is_forbid == true)
				{
					System.out.println("This Southern Path is forbidden");
				}
			else if (test.is_forbid == false)
				{
					System.out.println("Testing South: Node is now " + test);
					test.visited = true;
					test.parent = null;
					stack.push(test);
				}
		}
				
		if (start.y - 1 >= 0)//test west
		{		
			Node test = maze[x][start.y - 1];
			
			if(test.is_forbid == true)
				{
					System.out.println("This Western Path is forbidden");
				}
			else if (test.is_forbid == false)
			{
				System.out.println("Testing West: Node is now " + test);
				
				test.visited = true;
				test.parent = null;
				stack.push(test);
			}
		}	
	
			if (start.x - 1 >= 0)//this to test North
				{		
				Node test = maze[start.x - 1][y];
				if(test.is_forbid == true)
				{
					System.out.println("This Northern Path is forbidden");
					System.out.println();
				}
			else if (test.is_forbid == false)
			{
				System.out.println("Testing North: Node is now " + test);
				System.out.println();
				test.visited = true;
				test.parent = null;
				stack.push(test);
		
			}
	}
	
	//Start of second step to automatically go through the maze and search for Goal, if possible
	Node first = start;	//Gets rid of start we dont need it anymore
	numOfSteps++;
	
	while (!stack.isEmpty()) //while the Queue is NOT empty
	{
		if (first.x == Goal.x && first.y == Goal.y)//check to see if goal is on start
		{
			System.out.println("Goal was on top of start: Finished");
			goalFound = true;
			break;
		}
		
		Node Crawler = (Node) stack.pop();	//We pop the Queue into a new Node value
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
			visted.add(start);
	
			break;
		}
		Crawler.s = "V";
		
		
	
		System.out.println("Popped Value is: " + Crawler);	//Testing to make sure it gets the right node Value
		System.out.println();
	
		
		if (Crawler.y + 1 < (vertical_col))//test east, confirm it will hit end of wall (TESTING)
		{	
			Node QueueAdder = maze[Crawler.x][Crawler.y + 1];
			if(QueueAdder.visited == true || QueueAdder.is_forbid == true)
			{
				System.out.println("This East node has been visisted, will not revisit ");
				
			}
			else if (QueueAdder.is_forbid == false || QueueAdder.visited == false)
			{
				System.out.println("Testing East Version 2: Node is now " + QueueAdder);
				
				QueueAdder.visited = true;
				QueueAdder.in_fringe = true;
				QueueAdder.parent = Crawler;
	
				stack.push(QueueAdder);
			}
	
		}	
	
		if (Crawler.x + 1 < horizontal_rows)//test south, this test should skip south(WORKS)
		{
				Node QueueAdder = maze[Crawler.x + 1][Crawler.y];
				if(QueueAdder.visited == true || QueueAdder.is_forbid == true)
				{
					System.out.println("This South Node has been visisted, will not revisit ");
				}
				else if (QueueAdder.is_forbid == false || QueueAdder.visited == false)
				{
					System.out.println("Testing South VErsion 2 : Node is now " + QueueAdder);
					QueueAdder.visited = true;
					QueueAdder.in_fringe = true;
					QueueAdder.parent = Crawler;
	
					stack.add(QueueAdder);
				
		
				}
	}
	
		if (Crawler.y - 1 >= 0)//test west, this will not proceed if it reaches end of map (NEEDS TESTING)
		{
			Node QueueAdder = maze[Crawler.x][Crawler.y - 1];
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
	
			stack.add(QueueAdder);
		}
		}
	
		if (Crawler.x - 1 >= 0)//this to test North, this will not proceed if it reaches of map (CONFIRMED)
		{
			Node QueueAdder = maze[Crawler.x - 1][Crawler.y];
			if(QueueAdder.visited == true || QueueAdder.is_forbid == true)
				{
					System.out.println("This North Node has been visisted, will not revisit ");
					System.out.println();
				}
			else if (QueueAdder.is_forbid == false || QueueAdder.visited == false)
				{
			System.out.println("Testing North Version 2: Node is now " + QueueAdder);
			System.out.println();
			QueueAdder.visited = true;
			QueueAdder.in_fringe = true;
			QueueAdder.parent = Crawler;
	
			stack.push(QueueAdder);
				}
		}
		}
		
	
		if(stack.isEmpty() && goalFound == false)
		{
			System.out.println("Could not find or access the Goal");
		}
		 
		 System.out.println("Number of Steps taken " + numOfSteps);
		 System.out.println("Our DFS path is");
		 System.out.print(PathtoGoal + "");
		 System.out.println();
		 System.out.println("Our Final path is ");
		 System.out.print(visted + "");
		 System.out.println();
		 System.out.println();
	
		 for (Node[] z : maze) { 	//This prints the maze
			 for (Node B : z) 
			 	{	 
				 System.out.print(B + "\t"); 
			 	} 
			 System.out.println("\n"); 
		 } 
		}
		
	}

