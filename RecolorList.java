package gui;

import java.awt.Color;
import java.util.ArrayList;

public class RecolorList {
	
	private ArrayList<Integer> indexesB;
	private ArrayList<Integer> indexesE;
	private ArrayList<Color> colors;

	public RecolorList() 
	{
		indexesB = new ArrayList<Integer>();
		indexesE = new ArrayList<Integer>();
		colors = new ArrayList<Color>();
	}
	
	public void addColor(int indexB, int indexE, Color color)
	{
		indexesB.add(indexB);
		indexesE.add(indexE);
		colors.add(color);
	}
	
	public void removeColor()
	{
		if(!indexesB.isEmpty()) {
			indexesB.remove(0);
			indexesE.remove(0);
			colors.remove(0);
		}
	}
	
	public boolean isEmpty()
	{
		if(indexesB.isEmpty())
			return true;
		else
			return false;
	}

	public int getIndexB() 
	{
		return indexesB.get(0);
	}

	public int getIndexE() 
	{
		return indexesE.get(0);
	}

	public Color getColor() 
	{
		return colors.get(0);
	}
}
