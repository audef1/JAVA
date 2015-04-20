package Philosophers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame{
	
	private JPanel panel = new JPanel();
	private Ellipse2D.Double table = new Ellipse2D.Double();
	private Ellipse2D.Double p1 = new Ellipse2D.Double();
	private Ellipse2D.Double p2 = new Ellipse2D.Double();
	private Ellipse2D.Double p3 = new Ellipse2D.Double();
	private Ellipse2D.Double p4 = new Ellipse2D.Double();
	private Ellipse2D.Double p5 = new Ellipse2D.Double();
	private Color c1 = Color.RED;
	private Color c2 = Color.RED;
	private Color c3 = Color.RED;
	private Color c4 = Color.RED;
	private Color c5 = Color.RED;
	private Graphics2D g2;
	
	public GUI(){
		//Frame
		this.setSize(400, 400);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Dining Table");
		
		//add panel
		panel.setSize(400,400);
		this.add(panel);
		
		//circle-size
		int cs = 70;
		
		//elements
		table.width = 350;
	    table.height = 350;
	    table.x = 25;
	    table.y = 35;
	   
		p1.width = cs;
	    p1.height = cs;
	    p1.x = 160;
	    p1.y = 55;
	    
	    p2.width = cs;
	    p2.height = cs;
	    p2.x = 280;
	    p2.y = 140;
	    
	    p3.width = cs;
	    p3.height = cs;
	    p3.x = 250;
	    p3.y = 270;
	    
	    p4.width = cs;
	    p4.height = cs;
	    p4.x = 80;
	    p4.y = 270;
	    
	    p5.width = cs;
	    p5.height = cs;
	    p5.x = 50;
	    p5.y = 140;
	    
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		g2 = (Graphics2D)g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    
	    g2.draw(table);
	    g2.draw(p1);
	    g2.draw(p2);
	    g2.draw(p3);
	    g2.draw(p4);
	    g2.draw(p5);

	    g2.setPaint(c1);
	    g2.fill(p1);
	    g2.setPaint(c2);
	    g2.fill(p2);
	    g2.setPaint(c3);
	    g2.fill(p3);
	    g2.setPaint(c4);
	    g2.fill(p4);
	    g2.setPaint(c5);
	    g2.fill(p5);
	}
	
	public void setGreen(int n){
		Color c = Color.GREEN;
		if (n == 0)
			c1 = c;
		if (n == 1)
			c2 = c;
		if (n == 2)
			c3 = c;
		if (n == 3)
			c4 = c;
		if (n == 4)
			c5 = c;
		repaint();
	}
	
	public void setRed(int n){
		Color c = Color.RED;
		if (n == 0)
			c1 = c;
		if (n == 1)
			c2 = c;
		if (n == 2)
			c3 = c;
		if (n == 3)
			c4 = c;
		if (n == 4)
			c5 = c;
		repaint();
	}
}
