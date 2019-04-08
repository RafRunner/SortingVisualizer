package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyMenu extends JPanel {

	private JButton bSort;
	private JComboBox<String> cNumberOfBars;
	private JComboBox<Integer>cIntervals;
	private JComboBox<String> cAlgorithms;
	private JLabel lNumberOfBars;
	private JLabel lIntervals;
	private JLabel lAlgorithms;
	
	private String[] numberOfBars = new String[] {
			"Very Low",
			"Low", 
			"Moderate",
			"High",
			"Very High" };
	
	private int[] intervals = new int[] {
			8,
			10,
			15,
			20,
			30,
			50,
			100 };
	
	private String[] algorithms = new String[]{ 
				"Selection Sort", 
				"Bubble Sort", 
				"Insertion Sort", 
				"Merge Sort", 
				"Quick Sort", 
				"Heap Sort",
				"Radix Sort",
				"Shell Sort" };
	
	public MyMenu(MainWindow window) 
	{
		JLabel[] spaces = new JLabel[6];
		
		for(int i = 0; i < spaces.length; i++) {
			spaces[i] = new JLabel("");
		}

		bSort = new JButton("Sort");
		bSort.addActionListener(window);
		
		cNumberOfBars = new JComboBox<>(numberOfBars);
		cNumberOfBars.setBorder(BorderFactory.createBevelBorder(1));
		cNumberOfBars.setSelectedItem("High");
		lNumberOfBars = new JLabel("Number of values:");
		
		cIntervals = new JComboBox<>();
		cIntervals.setBorder(BorderFactory.createBevelBorder(1));
		for(int i : intervals) {
			cIntervals.addItem(i);
		}
		cIntervals.setSelectedItem(10);
		lIntervals = new JLabel("Interval (in milliseconds):");
		
		cAlgorithms = new JComboBox<>(algorithms);
		cAlgorithms.setBorder(BorderFactory.createBevelBorder(1));
		lAlgorithms = new JLabel("Algorithm:");
		
		this.setBorder(BorderFactory.createTitledBorder("Menu"));
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gb = new GridBagConstraints();
		
		gb.anchor = GridBagConstraints.CENTER;
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.weightx = 1;
		gb.weighty = 1;
		
		int i = 0, j = 0;
		
		gb.gridx = i++;
		gb.gridy = j++;
		this.add(spaces[0], gb);
		
		gb.weightx = 0.3;
		gb.weighty = 0.3;
		
		gb.weighty = 0.1;
		
		gb.gridx = i;
		gb.gridy = j++;
		this.add(lNumberOfBars, gb);
		
		gb.gridx = i;
		gb.gridy = j++;
		this.add(cNumberOfBars, gb);
		
		gb.gridx = i;
		gb.gridy = j++;
		this.add(lIntervals, gb);
		
		gb.gridx = i;
		gb.gridy = j++;
		this.add(cIntervals, gb);
		
		gb.gridx = i;
		gb.gridy = j++;
		this.add(lAlgorithms, gb);
		
		gb.gridx = i;
		gb.gridy = j++;
		this.add(cAlgorithms, gb);
		
		gb.weighty = 0.3;
		
		gb.gridx = i++;
		gb.gridy = j++;
		this.add(bSort, gb);
		
		gb.weightx = 1;
		gb.weighty = 1;
		
		gb.gridx = i;
		gb.gridy = j;
		this.add(spaces[1], gb);
	}

	public JButton getbSort() 
	{
		return bSort;
	}
	
	public int getBarWidth()
	{
		switch(cNumberOfBars.getSelectedItem().toString()) {
		
		case "Very Low":
			return 20;
			
		case "Low":
			return 15;
			
		case "Moderate":
			return 10;
			
		case "High":
			return 5;
			
		case "Very High":
			return 3;
			
		default:
			return 5;
		}
	}
	
	public int getInterval()
	{
		return (int) cIntervals.getSelectedItem();
	}

	public String getSelectedAlgorithm() 
	{
		return cAlgorithms.getSelectedItem().toString();
	}

	public String[] getAlgorithms() 
	{
		return algorithms;
	}
}
