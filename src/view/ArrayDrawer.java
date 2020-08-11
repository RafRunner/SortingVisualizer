package view;

import domain.ArrayOperation;
import domain.CompareElements;
import domain.OverrideValue;
import domain.RecolorList;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

public class ArrayDrawer extends JPanel {

    private int[] array;
    private int numberOfBars;
    private int barWidth;
    private int width;
    private int height;
    private int slope;

    private RecolorList recolorList;

    private int margin;

    private Color barColor;

    ArrayDrawer(final int width, final int height, final int margin, final int barWidth, final Color barColor) {
        this.width = width;
        this.height = height;
        this.barColor = barColor;
        this.margin = margin;

        recolorList = new RecolorList();

        setBackground(Color.BLACK);
        setVisible(true);
        setSize(width, height);
        setBarWidth(barWidth);
    }

    int[] getArrayCopy() {
        return array.clone();
    }

    int getArraySize() {
        return array.length;
    }

    void setBarWidth(final int barWidth) {
        this.barWidth = barWidth;
        numberOfBars = width / barWidth;
        slope = (int) (barWidth / 2.5);

        array = new int[numberOfBars];
        for (int i = 0; i < numberOfBars; i++) {
            array[i] = i + 1;
        }
    }

    private void addRecolor(final int indexB, final int indexE, final Color color) {
        recolorList.addColor(indexB, indexE, color);
    }

    private void clearColors() {
        recolorList = new RecolorList();
    }

    private void colorSwap(final int indexA, final int indexB, final Color color) {
        addRecolor(indexA, indexA, color);
        addRecolor(indexB, indexB, color);
    }

    void performOperations(final List<ArrayOperation> operations, final int interval) throws InterruptedException {
        for (final ArrayOperation operation : operations) {

            final int indexA = operation.getIndex();
            final int indexB = operation.getSecondaryValue();

            if (operation instanceof CompareElements)  {
                // colorSwap(indexA, indexA, Color.YELLOW, panel);
                continue;
            }
            else if (operation instanceof OverrideValue) {
                colorSwap(indexA, indexA, Color.RED);
                array[indexA] = indexB;
            }
            else {
                colorSwap(indexA, indexB, Color.RED);
                final int temp = array[indexA];
                array[indexA] = array[indexB];
                array[indexB] = temp;
            }

            repaint();
            Thread.sleep(interval);
        }
        clearColors();
    }

    void finish() throws InterruptedException {
        for(int i = 0; i < array.length - 1; i++) {
            addRecolor(0, i + 1, Color.GREEN);
            repaint();
            Thread.sleep(8);
        }
        clearColors();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(barColor);

        for (int i = 0; i < numberOfBars; i++) {
            final int startingX = i * barWidth;
            final int startingY = height - array[i] * slope - margin;
            final int barHeight = array[i] * slope + 30;

            if (!recolorList.isEmpty() && i == recolorList.getIndexB()) {
                g2D.setColor(recolorList.getColor());
            }

            g2D.fillRect(startingX, startingY, barWidth, barHeight);

            if (!recolorList.isEmpty() && i >= recolorList.getIndexE()) {
                g2D.setColor(barColor);
                recolorList.removeColor();
            }
        }
    }
}
