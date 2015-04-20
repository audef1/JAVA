package Calculator;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Listeners.CalcMouseListener;

public class GUI extends JFrame{

	public JTextField value1;
	public JTextField value2;
	public JTextField result;
	public JButton plus;
	public JButton minus;
	public JButton times;
	public JButton divide;
	public JButton clear;
	
	public GUI(){
		//Frame
		this.setSize(300, 450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("SwingCalc");
		GridLayout lytFrame = new GridLayout(3,1);
		this.setLayout(lytFrame);
		
		//Panels
		GridLayout lytInput = new GridLayout(2, 1);
		GridLayout lytOperators = new GridLayout(1, 4);
		GridLayout lytOutput = new GridLayout(2, 1);
		JPanel pnlInput = new JPanel(lytInput);
		pnlInput.setSize(300,150);
		JPanel pnlOperators = new JPanel(lytOperators);
		pnlInput.setSize(300,100);
		JPanel pnlOutput = new JPanel(lytOutput);
		pnlInput.setSize(300,150);

		//Elements
		value1 = new JTextField(24);
		value2 = new JTextField(24);
		result = new JTextField(24);
		plus = new JButton("+");
		minus = new JButton("-");
		times = new JButton("*");
		divide = new JButton("/");
		clear = new JButton("Clear");
		
		value1.setFont(new Font("Arial",0,20));
		value1.setMargin(new Insets(0,20,0,0));
		value2.setFont(new Font("Arial",0,20));
		value2.setMargin(new Insets(0,20,0,0));
		result.setFont(new Font("Arial",0,20));
		result.setMargin(new Insets(0,20,0,0));
		plus.setFont(new Font("Arial",1,30));
		minus.setFont(new Font("Arial",1,30));
		times.setFont(new Font("Arial",1,30));
		times.setMargin(new Insets(20,10,20,10));
		divide.setFont(new Font("Arial",2,30));
		clear.setFont(new Font("Arial",1,20));
		
		//ad Elements to Panels
		pnlInput.add(value1);
		pnlInput.add(value2);
		pnlOperators.add(plus);
		pnlOperators.add(minus);
		pnlOperators.add(times);
		pnlOperators.add(divide);
		pnlOutput.add(result);
		pnlOutput.add(clear);
		
		this.add(pnlInput);
		this.add(pnlOperators);
		this.add(pnlOutput);
		
		//add ButtonLIsteners
		plus.addMouseListener(new CalcMouseListener(this, "+"));
		minus.addMouseListener(new CalcMouseListener(this, "-"));
		times.addMouseListener(new CalcMouseListener(this, "*"));
		divide.addMouseListener(new CalcMouseListener(this, "/"));
		clear.addMouseListener(new CalcMouseListener(this, "clear"));
		
		this.setVisible(true);
	}
	
}
