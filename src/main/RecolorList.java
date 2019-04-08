package main;

import java.awt.Color;
import java.util.ArrayList;

public class RecolorList {
	
	private ArrayList<Integer> indexesB;
	private ArrayList<Integer> indexesE;
	private ArrayList<Color> colors;

	public RecolorList() 
	{
		indexesB = new ArrayList<>();
		indexesE = new ArrayList<>();
		colors = new ArrayList<>();
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
		return indexesB.isEmpty();
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
