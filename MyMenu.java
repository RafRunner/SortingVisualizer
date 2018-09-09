package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyMenu extends JPanel {

	private JButton BShuffle;
	private JButton BSort;

	public MyMenu(int width, int heigth) 
	{
		JLabel[] spaces = new JLabel[6];
		
		for(int i = 0; i < spaces.length; i++)
			spaces[i] = new JLabel("");
		
		BShuffle = new JButton("Shuffle");
		BSort = new JButton("Sort");
		
		this.setVisible(true);
		
		this.setBorder(BorderFactory.createTitledBorder("Menu"));
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gb = new GridBagConstraints();
		
		gb.anchor = GridBagConstraints.CENTER;
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.weightx = 1;
		gb.weighty = 1;
		
		gb.gridx = 0;
		gb.gridy = 0;
		this.add(spaces[0], gb);
		
		gb.weightx = 0.3;
		gb.weighty = 0.3;
		
		gb.gridx = 1;
		gb.gridy = 1;
		this.add(BShuffle, gb);
		
		gb.gridx = 1;
		gb.gridy = 2;
		this.add(BSort, gb);
		
		gb.weightx = 1;
		gb.weighty = 1;
		
		gb.gridx = 2;
		gb.gridy = 3;
		this.add(spaces[1], gb);
	}

}
