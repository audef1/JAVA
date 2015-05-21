package application;

import java.util.Observable;
import java.util.Observer;

import Controller.TimeController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StopwatchGUI extends Stage implements Observer {
	
	private BorderPane bp = new BorderPane();
	
	private HBox hbcenter = new HBox();
	private VBox vbbottom = new VBox();
	private HBox hbbottom = new HBox();
	
	private Button btnStart = new Button("Start");
	private Button btnStop = new Button("Stop");
	private Button btnReset = new Button("Reset");
	
	private Label lblTime = new Label("Time: 00:00:00");
	private Label lblStatus = new Label("");
	
	private TimeController tc;
	private Timer t;

	public StopwatchGUI(TimeController tc, Timer t){
		
		this.tc = tc;
		this.t = t;

		bp.setCenter(hbcenter);
		bp.setBottom(vbbottom);
		vbbottom.getChildren().add(hbbottom);
		
		hbcenter.getChildren().add(lblTime);
		vbbottom.getChildren().add(lblStatus);
		hbbottom.getChildren().addAll(btnStart, btnStop, btnReset);
		
		btnStart.setMinWidth(100);
		btnStart.setPadding(new Insets(10, 10, 10, 10));
		btnStop.setMinWidth(100);
		btnStop.setPadding(new Insets(10, 10, 10, 10));
		btnReset.setMinWidth(100);
		btnReset.setPadding(new Insets(10, 10, 10, 10));
		
		hbbottom.setMargin(btnStart,new Insets(0, 10, 0, 0));
		hbbottom.setMargin(btnStop,new Insets(0, 10, 0, 0));
		
		bp.setMargin(hbcenter, new Insets(10, 10, 10, 10));
		bp.setMargin(vbbottom, new Insets(10, 10, 10, 10));
		bp.setMargin(hbbottom, new Insets(0, 0, 20, 10));
		vbbottom.setMargin(lblStatus, new Insets(15, 0, 0, 0));
		hbcenter.setStyle("-fx-background-color: #eee; -fx-border-color:black;");

		hbbottom.setMinHeight(20);
		hbbottom.setPrefHeight(20);
		hbbottom.setMaxHeight(20);

		hbcenter.setAlignment(Pos.CENTER);
		hbbottom.setAlignment(Pos.CENTER);
		vbbottom.setAlignment(Pos.BOTTOM_LEFT);
		
		btnStop.setDisable(true);
		
		t.addObserver(this);
		
		//adding handlers
		btnStart.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tc.start();
			}
		});

		btnStop.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					tc.stop();
				}
			});
		btnReset.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					tc.reset();
				}
			});
		
		this.setTitle("StopwatchFX");
		//this.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		this.setScene(new Scene(bp,350,150));
		this.show();
	}

	@Override
	public void update(Observable o, Object arg) {
		Platform.runLater(() -> {
			
			btnStart.setDisable(t.isRunning());
			btnStop.setDisable(!t.isRunning());
			
			//fast korrekt
			if (btnStop.isDisable() && !t.isRunning()){
				btnReset.setDisable(t.isReset());
			}
				
			if (t.isRunning()){
				lblStatus.setText("Running");
				lblStatus.setStyle("-fx-text-fill: green;");
			}
			else if (!t.isRunning()){
				lblStatus.setText("Stopped");
				lblStatus.setStyle("-fx-text-fill: red;");
				if (t.isReset()){
					lblStatus.setText("Reset");
					lblStatus.setStyle("-fx-text-fill: black;");
				}
			}
			
			lblTime.setText("Time: " + t.getTimeString());
		});
	}

}