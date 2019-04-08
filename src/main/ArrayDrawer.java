package main;

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
		this.barColor = barColor;
		this.margin = margin;
		
		setBarWidth(barWidth);
		
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
	
	public void setBarWidth(int barWidth)
	{
		this.barWidth = barWidth;
		numberOfBars = width / barWidth;
		slope = (int) (barWidth/2.5);
		
		Values = new int[numberOfBars];
		for(int i = 0; i < numberOfBars; i++)
			Values[i] = i + 1;
	}
	
	public void addRecolor(int indexB, int indexE, Color color)
	{
		recolorList.addColor(indexB, indexE, color);
	}
	
	public void clearColors()
	{
		recolorList = new RecolorList();
	}

	@Override
	public void paintComponent(Graphics g) 
	{	
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(barColor);
		
		for(int i = 0; i < numberOfBars; i++) {
			int startingX = i*barWidth;
			int startingY = height - Values[i]*slope - margin;
			int barHeight = Values[i]*slope + 30;
			
			if(!recolorList.isEmpty() && i == recolorList.getIndexB()) {
				g2D.setColor(recolorList.getColor());
			}

			g2D.fillRect(startingX, startingY, barWidth, barHeight);
			
			if(!recolorList.isEmpty() && i >= recolorList.getIndexE()) {
				g2D.setColor(barColor);
				recolorList.removeColor();
			}
		}
	}
}
