package ch.bfh.sensorseafx.controller;

import java.util.Observable;
import java.util.Observer;

public class FXMLBrokerController implements Observer  {

	private Broker broker;

	public FXMLBrokerController(Broker broker){
		this.broker = broker;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public Broker getBroker() {
		return broker;
	}

}
