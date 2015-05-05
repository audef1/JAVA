package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Stopwatch extends BorderPane {

	private Scene scene = new Scene(this,350,180);
	
	private HBox hbcenter = new HBox();
	private VBox vbbottom = new VBox();
	private HBox hbbottom = new HBox();
	
	private Button btnStart = new Button("Start");
	private Button btnStop = new Button("Stop");
	private Button btnReset = new Button("Reset");
	
	private Label lblTime = new Label("Time: 00:00:00");
	private Label lblStatus = new Label("Status");
	
	private Timer timer;
	private long time = 0;
	
	public Stopwatch(){
		this.setCenter(hbcenter);
		this.setBottom(vbbottom);
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
		hbbottom.setMargin(btnReset,new Insets(0, 10, 0, 0));
		
		this.setMargin(hbcenter, new Insets(10, 10, 10, 10));
		this.setMargin(vbbottom, new Insets(10, 10, 10, 10));
		this.setMargin(hbbottom, new Insets(0, 0, 20, 10));
		vbbottom.setMargin(lblStatus, new Insets(15, 0, 0, 0));
		hbcenter.setStyle("-fx-background-color: #eee; -fx-border-color:black;");

		hbbottom.setMinHeight(20);
		hbbottom.setPrefHeight(20);
		hbbottom.setMaxHeight(20);

		hbcenter.setAlignment(Pos.CENTER);
		hbbottom.setAlignment(Pos.CENTER);
		vbbottom.setAlignment(Pos.BOTTOM_LEFT);
		
		btnReset.setDisable(true);
		btnStop.setDisable(true);
		
		//adding handlers
		btnStart.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					btnStart.setDisable(true);
					btnStop.setDisable(false);
					btnReset.setDisable(false);
					timer.start();
				}
			});
		btnStop.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					timer.stop();
					btnStart.setDisable(false);
				}
			});
		btnReset.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					timer.reset();
				}
			});

	}
	
	public void update(){		
		lblTime.setText("Time: " + timer.getTimeString());
	}

	public void attach(Timer t) {
		this.timer = t;
	}

}