package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

public class MyMenu extends JPanel {

	private JButton BShuffle;
	private JButton BSort;
	private JList<String> LAlgorithms;
	
	private String[] algorithms = new String[]{ 
				"Selection Sort", 
				"Bubble Sort", 
				"Insertion Sort", 
				"Merge Sort", 
				"Quick Sort", 
				"Heap Sort" };
	
	public MyMenu(MainWindow window) 
	{
		JLabel[] spaces = new JLabel[6];
		
		for(int i = 0; i < spaces.length; i++)
			spaces[i] = new JLabel("");
		
		BShuffle = new JButton("Shuffle");
		BSort = new JButton("Sort");
		
		BShuffle.addActionListener(window);
		BSort.addActionListener(window);
		
		LAlgorithms = new JList<String>(algorithms);
		
		LAlgorithms.setSelectedIndex(ListSelectionModel.SINGLE_SELECTION);
		LAlgorithms.setBorder(BorderFactory.createBevelBorder(1));
		
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
		
		gb.gridx = i;
		gb.gridy = j++;
		this.add(BShuffle, gb);
		
		gb.gridx = i;
		gb.gridy = j++;
		this.add(LAlgorithms, gb);
		
		gb.gridx = i++;
		gb.gridy = j++;
		this.add(BSort, gb);
		
		gb.weightx = 1;
		gb.weighty = 1;
		
		gb.gridx = i;
		gb.gridy = j;
		this.add(spaces[1], gb);
	}

	public JButton getBShuffle() 
	{
		return BShuffle;
	}

	public JButton getBSort() 
	{
		return BSort;
	}

	public JList<String> getLAlgorithms() 
	{
		return LAlgorithms;
	}

	public String[] getAlgorithms() 
	{
		return algorithms;
	}
}
