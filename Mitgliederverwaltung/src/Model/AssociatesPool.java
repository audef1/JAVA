package Model;

import java.util.ArrayList;

public class AssociatesPool {

	private ArrayList<Associate> pool = new ArrayList<Associate>();
	private int lastID = 0;
	
	public AssociatesPool(){
		
	}
	
	public void addAssociate(String type, String name, String vorname){
		
		int id = lastID + 1;
		
		if (type == "member")
			pool.add(new Member(name, vorname, id));
		else if (type == "backer")
			pool.add(new Backer(name, vorname, id));
		else if (type == "revisor")
			pool.add(new Revisor(name, vorname, id));
		lastID++;
	}
	
	public void removeAssociate(Associate a){
		pool.remove(a);
	}
		
	public void listPool(){
		for (Associate a : pool){
			System.out.println("| ID : " + a.getID() + " | Name: " + a.getFullName() + " | " + a.getClass().getSimpleName() + " |");
		}
	}

}
