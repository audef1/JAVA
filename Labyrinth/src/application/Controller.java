package application;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;

import Creator.LabyrinthModel;
import Creator.Tile;
import Solver.Solver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txfX;

    @FXML
    private MenuItem menuGenerate;

    @FXML
    private TextField txfY;

    @FXML
    private Label lblMessage;

    @FXML
    private Button btnSolve;

    @FXML
    private Canvas cvsPath;
    
    @FXML
    private Canvas cvsMaze;

    @FXML
    private VBox root;

    @FXML
    private MenuItem menuClose;

    @FXML
    private MenuItem menuLoad;

    @FXML
    void mazeGenerate(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
		LabyrinthModel l = new LabyrinthModel(28,59);
		
		GraphicsContext mazecanvas = cvsMaze.getGraphicsContext2D();
		
		for (int i = 0; i < l.getFinalMaze().length; i++){
			for (int j = 0; j < l.getFinalMaze()[i].length; j++){
				if (l.getFinalMaze()[i][j].type == 0){
					mazecanvas.setFill(Color.BLACK);
					mazecanvas.fillRect((j*10), (i*10), 10, 10);
				}
				else
				{
					mazecanvas.setFill(Color.WHITE);
					mazecanvas.fillRect((j*10), (i*10), 10, 10);
				}
			}
		}
		
		GraphicsContext pathcanvas = cvsPath.getGraphicsContext2D();
    	pathcanvas.clearRect(0, 0, 610, 300);
    	lblMessage.setText("");
    }

    @FXML
    void mazeLoad(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
    	
    	Tile[][] maze = Main.loadMaze();
		
		GraphicsContext mazecanvas = cvsMaze.getGraphicsContext2D();
		
		try {
			for (int i = 0; i < maze.length; i++){
				for (int j = 0; j < maze[i].length; j++){
					if (maze[i][j].type == 0){
						mazecanvas.setFill(Color.BLACK);
						mazecanvas.fillRect((j*10), (i*10), 10, 10);
					}
					else
					{
						mazecanvas.setFill(Color.WHITE);
						mazecanvas.fillRect((j*10), (i*10), 10, 10);
					}
				}
			}
		} catch (NullPointerException e) {
		    // ignore nullpointer exception
		}
		
		GraphicsContext pathcanvas = cvsPath.getGraphicsContext2D();
    	pathcanvas.clearRect(0, 0, 610, 300);
    	lblMessage.setText("");
    }

    @FXML
    void mazeClose(ActionEvent event) {
    	System.exit(1);
    }
    
    @FXML
    void mazeSolve(ActionEvent event) throws FileNotFoundException, InterruptedException {
    	
    	int x = Integer.parseInt(txfX.getText());
    	int y = Integer.parseInt(txfY.getText());
    	
    	Solver solver = new Solver(x,y); // define Startpoint here
    	
    	if (solver.getMaze()[x][y].type == 1){
	    	
	    	boolean path[][] = solver.getMazepath();
	    	
	    	GraphicsContext pathcanvas = cvsPath.getGraphicsContext2D();
	    	pathcanvas.clearRect(0, 0, 610, 300);
	    	
			for (int i = 0; i < path.length; i++){
				for (int j = 0; j < path[i].length; j++){
					if (path[i][j]){
						pathcanvas.setFill(Color.RED);
						pathcanvas.fillOval((j*10)+2, (i*10)+2, 6, 6);
					}
				}
			}
			lblMessage.setTextFill(Color.GREEN);
			lblMessage.setText("You have escaped!");
		}
    	else
    	{
    		GraphicsContext pathcanvas = cvsPath.getGraphicsContext2D();
        	pathcanvas.clearRect(0, 0, 610, 300);    		
    		lblMessage.setTextFill(Color.RED);
    		lblMessage.setText("You hit a wall, choose another tile.");
    	}	
    }

    @FXML
    void initialize() {
        assert txfX != null : "fx:id=\"txfX\" was not injected: check your FXML file 'Main.fxml'.";
        assert menuGenerate != null : "fx:id=\"menuGenerate\" was not injected: check your FXML file 'Main.fxml'.";
        assert txfY != null : "fx:id=\"txfY\" was not injected: check your FXML file 'Main.fxml'.";
        assert lblMessage != null : "fx:id=\"lblMessage\" was not injected: check your FXML file 'Main.fxml'.";
        assert btnSolve != null : "fx:id=\"btnSolve\" was not injected: check your FXML file 'Main.fxml'.";
        assert cvsMaze != null : "fx:id=\"cvsCanvas\" was not injected: check your FXML file 'Main.fxml'.";
        assert cvsPath != null : "fx:id=\"cvsPath\" was not injected: check your FXML file 'Main.fxml'.";
        assert root != null : "fx:id=\"root\" was not injected: check your FXML file 'Main.fxml'.";
        assert menuClose != null : "fx:id=\"menuClose\" was not injected: check your FXML file 'Main.fxml'.";
        assert menuLoad != null : "fx:id=\"menuLoad\" was not injected: check your FXML file 'Main.fxml'.";
    }
}