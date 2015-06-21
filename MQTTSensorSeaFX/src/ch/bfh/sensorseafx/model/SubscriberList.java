package ch.bfh.sensorseafx.model;

import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SubscriberList extends Observable{

	private ObservableList<String> topics = FXCollections.observableArrayList();	

	public void add(String topic){
		topics.add(topic);
		this.setChanged();
    	this.notifyObservers();
	}
	
	public void remove(String topic){
		topics.remove(topics.indexOf(topic));
		this.setChanged();
    	this.notifyObservers();
	}
	
	public void removeAll(){
		topics = FXCollections.observableArrayList();
		this.setChanged();
		this.notifyObservers();
	}
	
	public ObservableList<String> getTopics(){
		return topics;
	}
}
