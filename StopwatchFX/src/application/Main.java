package application;
	
import java.awt.Color;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,350,150);
			
			HBox hbtop = new HBox();
			root.setTop(hbtop);
			HBox hbcenter = new HBox();
			root.setCenter(hbcenter);
			HBox hbbottom = new HBox();
			root.setBottom(hbbottom);
			
			Button btnStart = new Button("Start");
			Button btnStop = new Button("Stop");
			Button btnReset = new Button("Reset");
			
			Label lblTime = new Label("Time: 00:00:00");
			Label lblStatus = new Label("Status");
			
			hbtop.getChildren().add(lblTime);
			hbbottom.getChildren().add(lblStatus);
			hbcenter.getChildren().addAll(btnStart, btnStop, btnReset);
			
			btnStart.setMinWidth(100);
			btnStart.setPadding(new Insets(10, 10, 10, 10));
			btnStop.setMinWidth(100);
			btnReset.setMinWidth(100);
			
			hbcenter.setMargin(btnStart,new Insets(0, 10, 0, 0));
			hbcenter.setMargin(btnStop,new Insets(0, 10, 0, 0));
			hbcenter.setMargin(btnReset,new Insets(0, 10, 0, 0));
			
			root.setMargin(hbtop, new Insets(10, 10, 10, 10));
			root.setMargin(hbbottom, new Insets(0, 0, 10, 10));
			
			hbtop.setStyle("-fx-background-color: #eee; -fx-border-color:black;");
			
			
			hbtop.setMinHeight(80);
			hbcenter.setMinHeight(50);
			hbbottom.setMinHeight(20);
			
			hbtop.setPrefHeight(80);
			hbcenter.setPrefHeight(50);
			hbbottom.setPrefHeight(20);
			
			hbtop.setMaxHeight(80);
			hbcenter.setMaxHeight(50);
			hbbottom.setMaxHeight(20);
			
			hbtop.setAlignment(Pos.CENTER);
			hbcenter.setAlignment(Pos.CENTER);
			hbbottom.setAlignment(Pos.BOTTOM_LEFT);
			
			primaryStage.setTitle("StopwatchFX");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
