package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MainWindow implements ActionListener {
    
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 800;
    private static final int BAR_WIDTH = 5;
    private static final Color BAR_COLOR = Color.WHITE;
    private static final int MARGIN = 50;
    public static final long INTERVAL = 10;

    private JFrame window;
    private ArrayDrawer panel;
    private MyMenu menu;

    public MainWindow()
    {
    	window = new JFrame();
    	window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    	window.setTitle("Sorting Visualiser");
    	window.setLocation(150, 100);
    	window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    	window.setLayout(null);
    	window.setResizable(false);
    	
    	menu = new MyMenu(this);
    	menu.setBounds((int) (0.33*WIDTH), (int) (0.2*HEIGHT), (int) (0.33*WIDTH), (int) (0.6*HEIGHT));
    	menu.setVisible(false);

    	panel = new ArrayDrawer(WIDTH, HEIGHT, MARGIN, BAR_WIDTH, BAR_COLOR);
    	panel.setLayout(null);
    	panel.add(menu, BorderLayout.CENTER);
    	
    	window.add(panel, BorderLayout.CENTER);
    	window.pack();
    	window.setVisible(true);
    }
    
    public ArrayDrawer getPanel()
    {
    	return panel;
    }
    
    public JFrame getWindow()
    {
    	return window;
    }
    
    public void showMenu()
    { 	
       	menu.setVisible(true);
    	window.pack();
    	panel.repaint();
    }
    
    public void hideMenu()
    {
    	menu.setVisible(false);
    	window.pack();
    	panel.repaint();
    }
    
    @Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == menu.getBShuffle()) {
			new Thread() {
				public void run() 
				{
					try {
						hideMenu();
						Thread.sleep(100);
						ArrayHandler.shuffle(panel.getValues(), panel);
						showMenu();
					
					} catch (InterruptedException e1) {
					e1.printStackTrace();
					}
				}
			}.start();
		}
		
		if(e.getSource() == menu.getBSort()) {
			System.out.println("Algorithm choosen: " + menu.getAlgorithms()[menu.getLAlgorithms().getSelectedIndex()]);
		}
	}
    
    public static void main(String[] args) throws InterruptedException
    {
    	MainWindow window = new MainWindow();
    	ArrayDrawer panel = window.getPanel();
    	ArrayHandler.shuffle(panel.getValues(), panel);
    	window.showMenu();
    }
}