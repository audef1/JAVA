package Model;

public abstract class Associate {

	//Variabeln
	private final int ID;
	private String name;
	private String firstname;
	private gender gender;
	private enum gender{ male, female, unknown };
	
	
	//Constructors
	public Associate(){
		this.ID = 4;
	}
	
	public Associate(String name, String firstname, int ID){
		this.ID = ID;
		this.name = name;
		this.firstname = firstname;
		this.gender = gender.unknown;
	}
	
	//Methoden
	public String getFullName(){
		return name + " " + firstname;
	}
	
	public void fuerallegleichemethode1(){
		System.out.println("blaa");
	}
	
	public void fuerallegleichemethode2(){
		
	}
	
	public void fuerallegleichemethode3(){
		
	}
	
	public int getID(){
		return ID;
	}
	
	//Abstrakte Methoden
	abstract public void display();
	abstract public void inform(String message);
	
}