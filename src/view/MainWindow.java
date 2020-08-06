package view;

import domain.ArrayOperation;
import domain.CompareElements;
import domain.OverrideValue;
import domain.helpers.ShuffleHelper;
import enuns.ESortingAlgorithm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;

public class MainWindow implements ActionListener {

    private static final int WIDTH = 1650;
    private static final int HEIGHT = 830;
    private static final Color BAR_COLOR = Color.WHITE;
    private static final int MARGIN = 50;

    private JFrame window;
    private ArrayDrawer panel;
    private MyMenu menu;

    public MainWindow() {
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setTitle("Sorting Visualiser");
        window.setLocation(200, 100);
        window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.setResizable(false);

        menu = new MyMenu(this);
        menu.setBounds((int) (0.33 * WIDTH), (int) (0.2 * HEIGHT), (int) (0.33 * WIDTH), (int) (0.6 * HEIGHT));
        menu.setVisible(false);

        panel = new ArrayDrawer(WIDTH, HEIGHT, MARGIN, menu.getBarWidth(), BAR_COLOR);
        panel.setLayout(null);
        panel.add(menu, BorderLayout.CENTER);

        window.add(panel, BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
    }

    public void showMenu() {
        menu.setVisible(true);
        window.pack();
        panel.repaint();
    }

    private void hideMenu() {
        menu.setVisible(false);
        window.pack();
        panel.repaint();
    }

    private static void colorSwap(final int indexA, final int indexB, final Color color, final ArrayDrawer panel) {
        panel.addRecolor(indexA, indexA, color);
        panel.addRecolor(indexB, indexB, color);
    }

    private static void performOperations(final List<ArrayOperation> operations, final int[] array, final int interval, final ArrayDrawer panel) throws InterruptedException {
        for (final ArrayOperation operation : operations) {

            final int indexA = operation.getIndex();
            final int indexB = operation.getSecondaryValue();

            if (operation instanceof CompareElements)  {
                // colorSwap(indexA, indexA, Color.YELLOW, panel);
                continue;
            }
            else if (operation instanceof OverrideValue) {
                colorSwap(indexA, indexA, Color.RED, panel);
                array[indexA] = indexB;
            }
            else {
                colorSwap(indexA, indexB, Color.RED, panel);
                final int temp = array[indexA];
                array[indexA] = array[indexB];
                array[indexB] = temp;
            }

            panel.repaint();
            Thread.sleep(interval);
        }
        panel.clearColors();
    }

    private static void finish(final int numberOfValues, final ArrayDrawer panel) throws InterruptedException
    {
        for(int i = 0; i < numberOfValues - 1; i++) {
            panel.addRecolor(0, i + 1, Color.GREEN);
            panel.repaint();
            Thread.sleep(8);
        }
        panel.clearColors();
        panel.repaint();
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == menu.getbSort()) {

            new Thread(() -> {
                try {
                    panel.setBarWidth(menu.getBarWidth());
                    hideMenu();

                    final ESortingAlgorithm selectedAlgotiyhm = menu.getSelectedAlgorithm();
                    final List<ArrayOperation> shuffleOperations = ShuffleHelper.shuffle(panel.getValues().clone());
                    performOperations(shuffleOperations, panel.getValues(), 8, panel);

                    Thread.sleep(500);

                    final List<ArrayOperation> operations = selectedAlgotiyhm.sortingSolution.getSortingSteps(panel.getValues().clone());
                    performOperations(operations, panel.getValues(), menu.getInterval(), panel);
                    finish(panel.getValues().length, panel);

                    showMenu();
                } catch (InterruptedException e1) {
                    System.out.println(e1.getMessage());
                }
            }).start();
        }
    }
}