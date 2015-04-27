package Solver;

/**
 * Created by FirstROW on 07.05.14.
*/

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;

import Creator.Tile;

public class Solver
{
	private Tile[][] maze; // The maze

	private boolean[][] visited;
	private boolean[][] mazepath; // The solution to the maze

	private int rows, cols;
	private int startX, startY; // Starting X and Y values of maze
	
	public Solver(int x, int y) throws FileNotFoundException{
		startX = x;
		startY = y;
		solveMaze();
	}
	
	private void solveMaze() throws FileNotFoundException {
		
		//this leaves a booleanarray with the correct path
		
		maze = loadMaze();
		
		//set booleanarrays to default values	
		visited = new boolean[rows][cols];
		mazepath = new boolean[rows][cols];
		
		for (int r = 0; r < rows; r++)
	        for (int c = 0; c < cols; c++){
	            visited[r][c] = false;
	            mazepath[r][c] = false;
	        }
	    @SuppressWarnings("unused")
		boolean b = recursiveSolve(startX, startY); //start recursive solving
	    
	    //print out treasuremap
		for (int i = 0; i < mazepath.length;i++){
			for (int j = 0; j < mazepath[i].length; j++){
				if (mazepath[i][j])
					System.out.print("x");
				else
					System.out.print("#");
			}
			System.out.println();
		}
	}
	
	public Tile[][] loadMaze() throws FileNotFoundException{
		
		//get width and height of maze
        File f = new File("maze.txt");
        @SuppressWarnings("resource")
		Scanner s = new Scanner(f);
        
        while(s.hasNextLine())  {
            rows++;
            String line = s.nextLine();
            cols = line.length();
        }
        
        maze = new Tile[rows][cols];
        @SuppressWarnings("resource")
		Scanner m = new Scanner(f);
        
        for (Integer i = 0; i < rows; i++){
        	for (Integer j = 0; j < cols; j++){
        			
        		//get type from character at position in txt
        		for (int x = 0; m.hasNext(); x++) {
                String line = m.nextLine();
                	for (int y = 0; y < line.length(); y++)
                	{
                		String c = "" + line.charAt(y);
                		maze[x][y] = new Tile(i,j,null);
                      	maze[x][y].type = Integer.parseInt(c);
                	}
        		}
        	}
        }
        
        return maze;
	}
	
	private boolean recursiveSolve(int x, int y) {
		//if escaped from maze
		if (maze[x][y].type == 4){
	    	mazepath[x][y] = true;
	    	return true;
	    }
		//if wall or already visited
		if (maze[x][y].type == 0 || visited[x][y])
	    	return false;
	    
	    //set visited true
	    visited[x][y] = true;
		
	    //check left field
	    if (x != 0) //not left edge
	        if (recursiveSolve(x-1, y)) { //recall method one left
	            mazepath[x][y] = true; // Sets that path value to true;
	            return true;
	        }
	    //check right field
	    if (x != rows-1) //not right edge
	        if (recursiveSolve(x+1, y)) { //recall method one right
	            mazepath[x][y] = true;
	            return true;
	        }
	    //check top field
	    if (y != 0)  //not top edge
	        if (recursiveSolve(x, y-1)) { //recall method one up
	            mazepath[x][y] = true;
	            return true;
	        }
	    //check bottom field
	    if (y != cols-1) //not bottom edge
	        if (recursiveSolve(x, y+1)) { //recall method one down
	            mazepath[x][y] = true;
	            return true;
	        }
	    
	    return false;
	}

	public boolean[][] getMazepath() {
		return mazepath;
	}
	
	public Tile[][] getMaze() {
		return maze;
	}
}
