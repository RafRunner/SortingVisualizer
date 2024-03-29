package view;

import domain.ArrayOperation;
import domain.helpers.ShuffleHelper;
import enuns.ESortingAlgorithm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.List;

import javax.swing.*;

public class MainWindow implements ActionListener {

    private static final int WIDTH = 1650;
    private static final int HEIGHT = 830;
    private static final Color BAR_COLOR = Color.WHITE;
    private static final int MARGIN = 50;

    private final JFrame window;
    private final ArrayDrawer arrayDrawerPanel;
    private final MyMenu menu;

    public MainWindow() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Sorting Visualiser");
        window.setLocation(200, 100);
        window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.setResizable(false);

        menu = new MyMenu(this);
        menu.setBounds((int) (0.33 * WIDTH), (int) (0.2 * HEIGHT), (int) (0.33 * WIDTH), (int) (0.6 * HEIGHT));
        menu.setVisible(false);

        arrayDrawerPanel = new ArrayDrawer(WIDTH, HEIGHT, MARGIN, menu.getBarWidth(), BAR_COLOR);
        arrayDrawerPanel.setLayout(null);
        arrayDrawerPanel.add(menu, BorderLayout.CENTER);

        window.add(arrayDrawerPanel, BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
    }

    public void showMenu() {
        menu.setVisible(true);
        window.pack();
        arrayDrawerPanel.repaint();
    }

    private void hideMenu() {
        menu.setVisible(false);
        window.pack();
        arrayDrawerPanel.repaint();
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (event.getSource() == menu.getbSort()) {

            new Thread(() -> {
                try {
                    arrayDrawerPanel.setBarWidth(menu.getBarWidth());
                    hideMenu();

                    final ESortingAlgorithm selectedAlgorithm = menu.getSelectedAlgorithm();
                    final List<ArrayOperation> shuffleOperations = ShuffleHelper.shuffle(arrayDrawerPanel.getArrayCopy());
                    arrayDrawerPanel.performOperations(shuffleOperations, 8);

                    Thread.sleep(500);

                    final List<ArrayOperation> solvingOperations = new Vector<>(arrayDrawerPanel.getArraySize() * 10);
                    selectedAlgorithm.sortingSolution.sort(arrayDrawerPanel.getArrayCopy(), solvingOperations);
                    arrayDrawerPanel.performOperations(solvingOperations, menu.getInterval());
                    arrayDrawerPanel.finish();

                } catch (final Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();

                } finally {
                    showMenu();
                }
            }).start();
        }
    }
}