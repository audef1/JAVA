package Marshaller;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD) //nimmt Variabeln statt getter und setternamen 
@XmlRootElement(namespace="http://ch.fbi.xml.marshaller",name="time_unit")
public class TimeUnit {

	@XmlElement(name = "unit_label") //legt name für nachfolgende Variable fest
	private String UNIT_LABEL;
	
	@XmlTransient
	private int secretNumber;
	
	
	public TimeUnit(String unitLabel){
		this.UNIT_LABEL = unitLabel;
	}
	
	@SuppressWarnings("unused")
	private TimeUnit(){
		//only for JAXB -> ohne argumente
	}
	
	public String getUnitLabel(){
		return UNIT_LABEL;
	}
	
	public String toString(){
		return "TimeUnit [UNIT_LABEL=" + UNIT_LABEL + "]";
	}
}
