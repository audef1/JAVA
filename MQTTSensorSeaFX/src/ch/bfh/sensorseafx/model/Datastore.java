package ch.bfh.sensorseafx.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ch.bfh.sensorseafx.helpers.XMLHelper;
import ch.bfh.sensorseafx.sensors.Sensor;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace="http://ch.audef1.mqttsensorsea",name="datastore")
public class Datastore extends Observable{
	
	@XmlElement(name ="timestamp")
	private long lastupdate = new Date().getTime();
	
	@XmlElement(name = "sensors")
	private ObservableList<Sensor> datastore = FXCollections.observableArrayList();	
	private ObservableList<XYChart.Series<String, Number>> tempChartData = FXCollections.observableArrayList();
	private Series<String, Number> tempSeries = new Series<String, Number>();
	
	private int multiplicator = 1;
	private int intervaltype = (1000*60*60*24); //day
	
	public Datastore(){
		tempSeries.setName("Messwerte");
		tempChartData.addAll(tempSeries);
	}
	
	public void add(Sensor s){
		
		int interval = (int) ((new Date().getTime() - lastupdate) / intervaltype);
	
		if (interval == multiplicator){
			try {
				export();
			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			lastupdate = new Date().getTime();
		}
		
		datastore.add(s);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");	
		tempSeries.getData().add(new XYChart.Data(dateFormat.format(s.getTimestamp()), (Number) s.getValues().get(0)));
		tempChartData.addAll(tempSeries);
		
		this.setChanged();
    	this.notifyObservers();
	}
	
	public void setInterval(int multiplicator, char type){
		this.multiplicator = multiplicator;
		
		if (type == 'd'){
			this.intervaltype = (1000*60*60*24);
		}
		else if (type == 'h'){
			this.intervaltype = ((1000*60*60) % 24);
		}
		else if (type == 'm'){
			this.intervaltype = ((1000*60) % 60);
		}
	}
	
	public void export() throws JAXBException, IOException{
		System.out.println("Exporting list....");
		
		SimpleDateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH:mm:ss");
		String date = df.format(lastupdate);
		
		OutputStream os = new FileOutputStream("export_" + date + ".xml");
		XMLHelper.saveInstance(os, this);
		os.close();
		
		datastore.clear();
	}

	public ObservableList<XYChart.Series<String, Number>> getDatastore() {
		return tempChartData;
	}
}
