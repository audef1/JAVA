package Dice;

import java.util.Random;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD) //nimmt Variabeln statt getter und setternamen 
@XmlRootElement(namespace="http://ch.fbi.xml.dice",name="statisticalDice")
@XmlType(name = "Dice", propOrder = {"minValue","maxValue","actValue","statistics"})
public class Dice {

	@XmlElement(name="statistics", nillable=false, required=true)
	private int[] statistics;
	@XmlElement(name="minValue", nillable=false, required=true)
	private int minValue;
	@XmlElement(name="maxValue", nillable=false, required=true)
	private int maxValue;
	@XmlElement(name="actValue", nillable=false, required=true)
	private int actValue;
	
	private transient Random random = new Random(); //transient um nicht zu serialisieren,andere möglichkeit mit @xml... nicht beide gleichzeitig.
	
	public Dice(int minValue, int maxValue) throws DiceException{
		
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.statistics = new int[maxValue-minValue+1];
		
	}
	
	@SuppressWarnings("unused")
	private Dice(){
		//leerer Cunstructor muss da sein für jaxb
	}
	
	public int getActValue() {
		return actValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public int getMinValue() {
		return minValue;
	}
	
	public void play(){
		this.actValue = random.nextInt(maxValue - minValue + 1) + minValue;
		//actValue = minValue + (int)(Math.random() * ((maxValue - minValue) + 1)); // alternativ
		this.statistics[actValue - minValue]++;
	}
	
	public String toString(){
		return "[Dice minValue=" + minValue + " maxValue=" + maxValue + " actValue=" + actValue + "]";
	}

	public int[] getStatistics() {
		return statistics.clone(); //gibt eine kopie des statistics-arrays aus.
	}
}
