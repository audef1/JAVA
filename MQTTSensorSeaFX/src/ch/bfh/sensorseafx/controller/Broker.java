package ch.bfh.sensorseafx.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Observable;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Broker extends Observable {

	private String id = Double.toString(new Date().getTime());
	private String clientID = "bro" + id;
	
	private MqttClient client;
	private MqttConnectOptions options = new MqttConnectOptions();
	
	public Broker(){
		
	}
	
	public Broker(String clientID){
		this.clientID = clientID;
	}
	
	public void connect(String host, int port, String username, String password){
		try {
			if (client == null){
				client = new MqttClient("tcp://" + host + ":" + port + "", clientID);
				options.setConnectionTimeout(3);
				options.setCleanSession(false);
				options.setUserName(username);
				options.setPassword(password.toCharArray());
	            	System.out.println("Connecting to broker: "+ host);
				client.connect(options);
					System.out.println("Connected!");
					this.setChanged();
			    	this.notifyObservers();
			}
			else
			{
				System.out.println("Connection to " + client.getServerURI() + " already established.");
			}
		} catch (MqttException e) {
			connAlert(e);
		}
	}
	
	public void connect(String host, String username, String password){
		try {
			if (client == null){
				client = new MqttClient("tcp://" + host + ":1883", clientID);
				options.setConnectionTimeout(3);
				options.setCleanSession(false);
				options.setUserName(username);
				options.setPassword(password.toCharArray());
	            System.out.println("Connecting to broker: "+ host);
				client.connect(options);
				System.out.println("Connected!");
				this.setChanged();
		    	this.notifyObservers();
			}
			else
			{
				System.out.println("Connection to " + client.getServerURI() + " already established.");
			}
		} catch (MqttException e) {
			connAlert(e);
		}
	}
	
	public void connect(String host){
		try {
			if (client == null){
				client = new MqttClient("tcp://" + host + ":1883", clientID);
				options.setConnectionTimeout(3);
				System.out.println("Connecting to broker: "+ host);
				options.setCleanSession(false);
				client.connect(options);
				System.out.println("Connected!");
				this.setChanged();
		    	this.notifyObservers();
			}
			else
			{
				System.out.println("Connection to " + client.getServerURI() + " already established.");
			}
		} catch (MqttException e) {
			connAlert(e);
		}
	}
	
	public void connect(String host, int port){
		try {
			client = new MqttClient("tcp://" + host + ":" + port + "", clientID);
			options.setConnectionTimeout(3);
			System.out.println("Connecting to broker: "+ host);
			client.connect(options);
			System.out.println("Connected!");
			this.setChanged();
	    	this.notifyObservers();
		} catch (MqttException e) {
			connAlert(e);
		}
	}
	
	public void disconnect(){
		try {
			client.disconnect();
			System.out.println("Disconnected!");
			client = null;
			this.setChanged();
	    	this.notifyObservers();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public MqttClient getClient(){
		if (client != null)
			return client;
		return client;
	}
	
	public boolean isConnected(){
		if (client != null)
			return client.isConnected();
		return false;	
	}
	
	public void setClientID(String s){
		this.clientID = s;
	}
	
	public void connAlert(Exception e){
		client = null;
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Connection Failure");
		alert.setHeaderText("Oops an Error occured!");
		alert.setContentText("Connection could not be established.");
		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception stacktrace was:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    
		}
	}
}
