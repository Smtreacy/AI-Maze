package solvingMaze;
import java.util.Scanner;
public class Node implements Comparable<Node>{
	//Node locations
	public int x, y;
	
	//our euclidean distance
	public  double distance;
	
	//our costs for each Node
	public double northCost = 3;
	public double westCost = 4;
	public double southCost = 1;
	public double eastCost = 2;
	public double accumulated_Cost;
	
	//This is to fill in the Maps
	public String s;
	
	/* wether it is a forbidden cell */
	public boolean is_forbid; 
	
	/* wether it is a forbidden cell */
	public boolean visited; 
	
	/* wether it has been in fringe */
	public boolean in_fringe;
	
	//This will be used to grab the parent node to print out our final path
	public Node parent;
	static Scanner in = new Scanner (System.in);

/*the previous node during search so that search path can be recorded by back tracking*/

	
	public Node(int x, int y, String s, boolean f){
		this.x = x;
		this.y = y;
		this.is_forbid = f;
		this.in_fringe = false;
		this.visited = false;
		this.s =s;
		northCost = 3;
		westCost = 4;
		southCost = 1;
		eastCost = 2;
		}
/*******Not used	
//	public Node(int x, int y, String s, boolean f, float distance) //This Constructor will be used for Greedy Search
//	{
//		this.x = x;
//		this.y = y;
//		this.is_forbid = f;
//		this.in_fringe = false;
//		this.visited = false;
//		this.s = s;
//		this.distance = distance;
//*****/	//}
	
	public Node(String s, boolean f){
		this.s = s;
		this.is_forbid = f;
	}
	
	public Node(Node queueAdder, double heurstics) {
		
		this.distance = heurstics;
	}

	public String toString () //we need this otherwise we get memory locations
	{
	//	return "(" + this.x + "," + this.y +")" + "(" + s + ")" + " Is forbid? " + this.is_forbid + " is in fringe? " + this.in_fringe + " was visited? " + this.visited;
		return "(" + this.x + "," + this.y +")" + "(" + s + ")";
	//	return "(" + this.x + "," + this.y +")" + "(" + s + ") Distance is " + distance; //Tests our Greedy Path


	}
	
	public Node getParent(){
		return parent;
		
	}
	
	//This method will take a node value that has already been created
	//and allow us to put in a distance value
	public static Node addDistance(Node changedNode, double heuristic)
	{
		changedNode.distance = heuristic;
		return changedNode;
		
	}
	
	public static Node addTotalCost(Node changedNode, Node oldNode, double cost)
	{

		changedNode.accumulated_Cost = oldNode.accumulated_Cost + cost;
		return changedNode;
		
	}
	
	//This method is for our "First search" method for A* it doesn't require an older node 
	public static Node addTotalCost(Node changedNode, double cost)
	{

		changedNode.accumulated_Cost =  cost;
		return changedNode;
		
	}
	

	
	public int compareTo(Node n) 
	{
        if(this.distance < n.distance)
        {
            return -1;
        }
        else if(this.distance > n.distance)
        {
            return 1;
        }
        else
            return 0;
    }
}
	

