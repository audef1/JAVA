package application;

/**
 * Created by FirstROW on 07.05.14.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Creator.Tile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = (VBox)FXMLLoader.load( getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,640,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		launch(args);
	}
	
public static Tile[][] loadMaze() throws FileNotFoundException{
		
	int rows = 28;
	int columns = 59;
	Tile[][] finalMaze;
	
		//get width and height of maze
        File f = new File("maze.txt");
        @SuppressWarnings("resource")
		Scanner s = new Scanner(f);
        
        while(s.hasNextLine())  {
            rows++;
            String line = s.nextLine();
            columns = line.length();
        }
        
        finalMaze = new Tile[rows][columns];
        @SuppressWarnings("resource")
		Scanner m = new Scanner(f);
        
        for (Integer i = 0; i < rows; i++){
        	for (Integer j = 0; j < columns; j++){
        			
        		//get type from character at position in txt
        		for (int x = 0; m.hasNext(); x++) {
                String line = m.nextLine();
                	for (int y = 0; y < line.length(); y++)
                	{
                		String c = "" + line.charAt(y);
                		finalMaze[x][y] = new Tile(i,j,null);
                		finalMaze[x][y].type = Integer.parseInt(c);
                	}
        		}
        	}
        }
        
        return finalMaze;
	}
}
