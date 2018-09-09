package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class ArrayDrawer extends JPanel {

	private int[] Values;
	private int numberOfBars;
	private int barWidth;
	private int width;
    private int height;
    private int slope;
    
    private RecolorList recolorList;
    
    private int margin;
  
    private Color barColor;

	public ArrayDrawer(int width, int height, int margin, int barWidth, Color barColor) 
	{	
		this.width = width;
		this.height = height;
		this.barWidth = barWidth;
		slope = (int) (barWidth/2.5);
		this.numberOfBars = width / barWidth - (int) (barWidth/10);
		this.barColor = barColor;
		this.margin = margin;
		
		recolorList = new RecolorList();

		this.setBackground(Color.BLACK);
		this.setVisible(true);
		this.setSize(width, height);

		Values = new int[numberOfBars];
		for(int i = 0; i < numberOfBars; i++)
			Values[i] = i + 1;
	}
	
	public int[] getValues()
	{
		return Values;
	}
	
	public void redraw()
	{
		this.repaint();
	}
	
	public void addRecolor(int indexB, int indexE, Color color)
	{
			recolorList.addColor(indexB, indexE, color);
	}
	
	public void clearColors()
	{
		recolorList = new RecolorList();
	}

	public void paintComponent(Graphics g) 
	{	
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(barColor);
		
		for(int i = 0; i < numberOfBars; i++) {
			int startingX = i*barWidth;
			int startingY = height - Values[i]*slope - margin;
			int barHeight = Values[i]*slope + 30;
			
			if(!recolorList.isEmpty() && i == recolorList.getIndexB())
				g2D.setColor(recolorList.getColor());
		
			g2D.fillRect(startingX, startingY, barWidth, barHeight);
			
			if(!recolorList.isEmpty() && i >= recolorList.getIndexE() - 1) {
				g2D.setColor(barColor);
				recolorList.removeColor();
			}
		}
	}
}
