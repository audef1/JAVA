package Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Calculator.GUI;
import Calculator.Logic;

public class CalcMouseListener implements MouseListener{

	GUI g;
	String op;
	Logic log;
	
	public CalcMouseListener(GUI g, String op) {
		super();
		this.g = g;
		this.log = new Logic(g);
		this.op = op;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		try{
		if (!g.value1.getText().isEmpty() && !g.value2.getText().isEmpty() )
			log.calculate(Double.parseDouble(g.value1.getText()), Double.parseDouble(g.value2.getText()), op);
		}
		catch (NumberFormatException ex){
			g.result.setText("Only Numbers allowed");
		}
		
		if (op == "clear"){
			g.value1.setText("");
			g.value2.setText("");
			g.result.setText("");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//throw new UnsupportedOperationException("Methode ist nicht implementiert");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//throw new UnsupportedOperationException("Methode ist nicht implementiert");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//throw new UnsupportedOperationException("Methode ist nicht implementiert");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//throw new UnsupportedOperationException("Methode ist nicht implementiert");
	}

}
