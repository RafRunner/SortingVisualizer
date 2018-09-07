package gui;

import java.awt.Color;

import javax.swing.JFrame;

public class MainWindow {
    
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 800;
    private static final int BAR_WIDTH = 5;
    private static final Color BAR_COLOR = Color.WHITE;
    private static final int MARGIN = 70;
    public static final long INTERVAL = 10;

    private JFrame window;
    private ArrayDrawer Panel;

    public MainWindow()
    {
    	window = new JFrame();
    	window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    	window.setTitle("Sorting Visualiser");
    	window.setLocation(150, 100);
    	window.setSize(WIDTH, HEIGHT);
    	window.setVisible(true);
    	//window.setResizable(false); 	

    	Panel = new ArrayDrawer(WIDTH, HEIGHT, MARGIN, BAR_WIDTH, BAR_COLOR);
    	window.add(Panel);
    }
    
    public ArrayDrawer getPanel()
    {
    	return Panel;
    }

    public static void main(String[] args) throws InterruptedException
    {
    	MainWindow window = new MainWindow();
    	ArrayDrawer panel = window.getPanel();
    	ArrayHandler.shuffle(panel.getValues(), panel);
    }
}