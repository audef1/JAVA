package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Calculator.GUI;

public class CalcActionListener implements ActionListener{

	private GUI g;
	
	public CalcActionListener(GUI g){
		this.g = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		if (!g.value1.getText().isEmpty() && !g.value2.getText().isEmpty() ){
		
			try{
				
				Double value1 = Double.parseDouble(g.value1.getText());
				Double value2 = Double.parseDouble(g.value2.getText());
				
					if (e.getSource() == g.plus){
						g.result.setText(Double.toString(value1 + value2));
					}
					else if (e.getSource() == g.minus){
						g.result.setText(Double.toString(value1 - value2));
					}
					else if (e.getSource() == g.times){
						g.result.setText(Double.toString(value1 * value2));
					}
					else if (e.getSource() == g.divide){
						g.result.setText(Double.toString(value1 / value2));
					}
			}
			catch (NumberFormatException ex){
				g.result.setText("Only Numbers allowed");
			}
			
			if (e.getSource() == g.clear){
				g.value1.setText("");
				g.value2.setText("");
				g.result.setText("");
			}
		}
	}
}