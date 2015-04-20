package Calculator;

public class Logic {

	GUI g; 
	
	public Logic(GUI g){
		this.g = g;
	}
	
	public void calculate(double i, double j, String op){
		
		double res = 0;
		
		if (op == "+")
			res = i + j;
		else if (op == "-")
			res = i - j;
		else if (op == "*")
			res = i * j;
		else if (op == "/")
			res = i / j;
		
		g.result.setText(Double.toString(res));
		
	}
	

	
}
