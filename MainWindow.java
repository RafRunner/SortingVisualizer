package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Menu;

import javax.swing.JFrame;

public class MainWindow {
    
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 800;
    private static final int BAR_WIDTH = 15;
    private static final Color BAR_COLOR = Color.WHITE;
    private static final int MARGIN = 70;
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
    	window.setSize(WIDTH, HEIGHT);
    	window.setVisible(true);
    	//window.setResizable(false);

    	panel = new ArrayDrawer(WIDTH, HEIGHT, MARGIN, BAR_WIDTH, BAR_COLOR);
    	panel.setLayout(null);
    	window.add(panel);
    	
    	menu = new MyMenu(WIDTH, HEIGHT);
    	menu.setBounds((int) (0.33*WIDTH), (int) (0.2*HEIGHT), (int) (0.33*WIDTH), (int) (0.6*HEIGHT));
    	menu.setVisible(false);
    	panel.add(menu, BorderLayout.CENTER);
    }
    
    public ArrayDrawer getPanel()
    {
    	return panel;
    }
    
    public void showMenu()
    {
    	menu.setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException
    {
    	MainWindow window = new MainWindow();
    	ArrayDrawer panel = window.getPanel();
    	ArrayHandler.shuffle(panel.getValues(), panel);
    	Thread.sleep(100);
    	window.showMenu();
    }
}