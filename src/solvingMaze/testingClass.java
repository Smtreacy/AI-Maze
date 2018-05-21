package solvingMaze;

import java.util.Scanner;

public class testingClass {
		
	 static int vertical_col = 0;	//class variable
	 static int horizontal_rows = 0;	//class variable
	 public static int numOfTraps; //this is causing (0,0) to be x at any value higher than what we have
	 public static int rowTrap;
	 public static int colTrap;
	 	 
	 public static int start_row;
	 public static int start_col;
	 


	 static int end_row;		//sets our goal row
	 static int end_col;		//sets our goal column
	 
	 public static String searchHolder;	//makes sure that the scanner only grabs the first char
	 public static String searchMethod;	//this is our parsed searchHolder 
	 
	 static Scanner in = new Scanner (System.in);
	 
	 
	public static void main (String [] args)
	{
		System.out.println("Running from TestingClass.java");
		setup_maze();
		print_maze();

	}
	public  static void setup_maze() 
	{
		

		System.out.println("Please enter number of horizontal rows");
		while(!in.hasNextInt()) {
			System.out.println("Please enter a valid Int");
		    in.next();
		}
		 horizontal_rows = in.nextInt();
		
		System.out.println("Please enter number of vertical columns");
		while(!in.hasNextInt()) {
			System.out.println("Please enter a valid Int");
		    in.next();
		}
		vertical_col = in.nextInt();	//affects the class variable

		// This will set up the traps
		System.out.println("Please enter the number of obstacles you would like to place ");
		numOfTraps = in.nextInt();
		
		
		
		System.out.println("Please enter starting row within 0 to " + (horizontal_rows - 1));
		while(!in.hasNextInt()) {
			System.out.println("Please enter a valid Int");
		    in.next();
		}
		start_row = in.nextInt();	//affects the class variable
		
		System.out.println("Please enter starting column within 0 to " + (vertical_col -1));
		while(!in.hasNextInt()) {
			System.out.println("Please enter a valid Int");
		    in.next();
		}
		start_col = in.nextInt();	//affects the class variable
		
		System.out.println("Please enter ending row within 0 to " + (horizontal_rows - 1));
		while(!in.hasNextInt()) {
			System.out.println("Please enter a valid Int");
		    in.next();
		}
		end_row = in.nextInt();	//affects the class variable
		
		System.out.println("Please enter ending column within 0 to" + (vertical_col - 1));
		while(!in.hasNextInt()) {
			System.out.println("Please enter a valid Int");
		    in.next();
		}
		end_col = in.nextInt();	//affects the class variable
		
	}
	
	static void print_maze() 
	 {
	 	//This sets the values of the maze			
	 	//Maze is set up by row then colum
	 		 int[] trap_Rows=new int [numOfTraps];//declaration and instantiation.
	 		 int[] trap_cols=new int [numOfTraps];//declaration and instantiation.
	 		 
	 		 int testTraps = numOfTraps;
	 			int rowTrap;
	 			int colTrap;
	 			for (int i = 0; i < testTraps; i++)
	 			{
	 				
	 				System.out.println("Please Enter Row of trap " + i);
	 				rowTrap = in.nextInt();
	 				trap_Rows[i] = rowTrap;
	 				System.out.println("Please Enter Col of trap " + i);
	 				colTrap = in.nextInt();
	 				trap_cols[i] = colTrap;
	 			}
	 		 
	 		Node [][] maze = new Node[horizontal_rows][vertical_col];
	 	    Node Start = new Node (start_row,start_col,"S",false); 
	 		Node Goal = new Node (end_row,end_col,"G",false);
	 		
	 		int zs = 0;
	 		
	 		for ( int i = 0; i < maze.length; i++) //this increments rows
	 		{ 
	 			for (  int j = 0; j < maze[i].length; j++)  //this increments columns
	 				{	
	 					Node Empty = new Node (i,j,"0",false);
	 					maze [i][j] = Empty;
	 					
	 					for ( int jk = 0; jk < trap_Rows.length; jk++)	//this will allow each loop to look at the whole array bypassing an issue where it couldnt "look behind" such as putting (0.3) and then (0,2) would result in (0,2) never being marked
	 					{
	 						if (maze[i][j] == maze[trap_Rows[jk]][trap_cols[jk]]) //TODO this seems to work further debugging should be done
	 						{
	 						Node trap = new Node (i,j,"X",true);
	 						maze[i][j] = trap;
	 						
	 						}
	 					}
	 				}	
	 		}
	 		
	 		maze[start_row][start_col] = Start;
	 		maze[end_row][end_col] = Goal;
	 	
	 		for (Node[] z : maze) //This prints the maze
	 		{ 	
	 			for (Node x : z) 
	 				{ 
	 					System.out.print(x + "\t"); 
	 				} 
	 			System.out.println("\n"); 
	 		} 
	 	


	 System.out.println("Enter 1 for Greedy Search, 2 For Breadth First Search, 3 for Depth Search or 4 for A* Search");

	 while (!in.hasNext("[1-4]+")) {
	     System.out.println("Press Press 1,2,3 or 4");
	     in.next();
	 }
	 searchHolder= in.next();
	 searchMethod = searchHolder.substring(0,1);

	 //TODO make have a repository for the original "maze" then I can refresh the maze once the searches are done with them.

	 if(searchMethod.equals("1"))
	 {
	 	Greedy_Search.Greedy_Search(Start,Goal,maze,horizontal_rows,vertical_col);	//passes in start, goal, and initalized maze
	 }

	 else if(searchMethod.equals("2"))
	 {
	 	Breadth_First_Search.BFS_Search(Start,Goal,maze,horizontal_rows,vertical_col);	//passes in start, goal, and initalized maze
	 }
	 else if(searchMethod.equals("3"))
	 {
	 	DepthSearch.depth_Searching(Start,Goal,maze,horizontal_rows,vertical_col);	//passes in start, goal, and initalized maze
	 }
	 else if(searchMethod.equals("4"))
	 {
	 	A_Star_Search.Star_Search(Start,Goal,maze,horizontal_rows,vertical_col);	//passes in start, goal, and initalized maze
	 }
	 }


}
