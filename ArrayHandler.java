package gui;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;

public class ArrayHandler {

	private static long interval = MainWindow.INTERVAL;

	public static void shuffle(int[] Values, ArrayDrawer panel) throws InterruptedException
	{
		Random rng = new Random();
		
		for(int i = 0; i < Values.length; i++) {
			int index = rng.nextInt(i + 1);
			int aux = Values[index];
			Values[index] = Values[i];
			Values[i] = aux;
			
			colorSwap(index, i, panel);
			panel.repaint();
			Thread.sleep(interval);
		}
		panel.repaint();
	}
	
	public static void colorSwap(int indexA, int indexB, ArrayDrawer panel) 
	{
		if(indexA < indexB) {
			panel.addRecolor(indexA, indexA+1, Color.RED);
			panel.addRecolor(indexB, indexB+1, Color.RED);
		}
		else {
			panel.addRecolor(indexA, indexA+1, Color.RED);
			panel.addRecolor(indexB, indexB+1, Color.RED);
		}
	}
	
	public static void finish(int numberOfValues, ArrayDrawer panel) throws InterruptedException
	{
		for(int i = 0; i < numberOfValues - 1; i++) {
			panel.addRecolor(0, i + 1, Color.GREEN);
			panel.repaint();
			Thread.sleep(interval);
		}
		panel.repaint();
	}
}
