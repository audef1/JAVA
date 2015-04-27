package Creator;

/**
 * Created by FirstROW on 07.05.14.
*/

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class LabyrinthModel {
		
	private int rows;
	private int columns;
	private Tile[][] mazeElements;
	private Tile[][] finalMaze;

	public LabyrinthModel(int r, int c) throws FileNotFoundException, UnsupportedEncodingException{
		
		this.rows = r;
		this.columns = c;
		
		mazeElements = new Tile[rows][columns];
		
		for (int i = 0; i<rows;i++){
			for (int j = 0; j<columns;j++){
				mazeElements[i][j] = new Tile(i, j, null);
			}
		}
		
		// select random point and open as start point
        Tile start = new Tile((int)(Math.random()*rows),(int)(Math.random()*columns),null);
        mazeElements[start.row][start.column].type = 1; //start point could also be marked as S or X
        
        // iterate through direct neighbors of node
        ArrayList<Tile> walls = new ArrayList<Tile>();
        for(int x=-1;x<=1;x++)
        	for(int y=-1;y<=1;y++){
        		if(x==0&&y==0||x!=0&&y!=0)
        			continue;
        		try{
        			if(mazeElements[start.row+x][start.column+y].type == 1) continue;
        		}catch(Exception e){ // ignore ArrayIndexOutOfBounds
        			continue;
        		}
        		// add eligible points to frontier
        		walls.add(new Tile(start.row+x,start.column+y,start));
        	}
        
        Tile last = null;
        while(!walls.isEmpty()){
 
        	// pick current node at random
        	Tile current = walls.remove((int)(Math.random()*walls.size()));
        	Tile oppositeTile = current.opposite();
        	
        	try{
        		// if both node and its opposite are walls
        		if(mazeElements[current.row][current.column].type == 0){
        			if(mazeElements[oppositeTile.row][oppositeTile.column].type == 0){
 
        				// open path between the nodes
        				mazeElements[current.row][current.column].type = 1;
        				mazeElements[oppositeTile.row][oppositeTile.column].type = 1;
 
        				// store last node in order to mark it later
        				last = oppositeTile;
 
        				// iterate through direct neighbors of node, same as earlier
        				for(int x=-1;x<=1;x++)
				        	for(int y=-1;y<=1;y++){
				        		if(x==0&&y==0||x!=0&&y!=0)
				        			continue;
				        		try{
				        			if(mazeElements[oppositeTile.row+x][oppositeTile.column+y].type == 1) continue;
				        		}catch(Exception e){
				        			continue;
				        		}
				        		walls.add(new Tile(oppositeTile.row+x,oppositeTile.column+y,oppositeTile));
				        	}
        			}
        		}
        	}catch(Exception e){ // ignore NullPointer and ArrayIndexOutOfBounds
        	}
 
        	// if algorithm has resolved, mark end point
        	if(walls.isEmpty())
        		mazeElements[last.row][last.column].type = 1;  //end point could also be marked separately as X or E
        }
    
        
      //prepare maze with walls and exit
      //initialize final array
      		finalMaze = new Tile[(rows+2)][(columns+2)];
      		for (Integer i = 0; i < (rows+2); i++){
      			for (Integer j = 0; j < (columns+2); j++){
      				finalMaze[i][j] = new Tile(i,j,null);
      				finalMaze[i][j].type = 0;
      			}
      		}
      		
      		//shift array one row down and a column to the right
      		for (Integer i = (rows); i > 0 ; i--){
      			for (Integer j = (columns); j > 0; j--){
      				finalMaze[i][j] = mazeElements[i-1][j-1];
      			}
      		}
      		
      		//open gate
      		for (Integer i = 0; i < (columns+2); i++){
      			if (finalMaze[1][i].type == 1){
      				finalMaze[0][i].type = 4;
      				break;
      			}
      			if (finalMaze[rows][i].type == 1){
      				finalMaze[rows+1][i].type = 4;
      				break;
      			}
      		}
      		
      		//print final maze to console
      		for (Integer i = 0; i < (rows+2); i++){
      			for (Integer j = 0; j < (columns+2); j++){
      				System.out.print(finalMaze[i][j].type);
      			}
      			System.out.println();
      		}

      	rows = rows+2;
      	columns = columns+2;
      	
        // write maze into txt-file
		PrintWriter writer = new PrintWriter("maze.txt", "UTF-8");
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++)
				writer.print(finalMaze[i][j].type);
			writer.println();
		}
		writer.close();
	}
	
	public Tile[][] getFinalMaze() {
		return finalMaze;
	}
	
}
