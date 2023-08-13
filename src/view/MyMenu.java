package view;

import enuns.ESortingAlgorithm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MyMenu extends JPanel {

    private final JButton bSort;
    private final JComboBox<String> cNumberOfBars;
    private final JComboBox<Integer> cIntervals;
    private final JComboBox<String> cAlgorithms;

    private static final int DEFAULT_INTERVAL = 10;
    private static final String DEFAULT_BAR_WIDTH = "Moderate";
    private static final ESortingAlgorithm DEFAULT_ALGORITHM = ESortingAlgorithm.SelectionSort;

    MyMenu(final MainWindow window) {
        final JLabel[] spaces = new JLabel[6];

        for (int i = 0; i < spaces.length; i++) {
            spaces[i] = new JLabel("");
        }

        bSort = new JButton("Sort");
        bSort.addActionListener(window);

        final String[] numberOfBars = new String[]{
                "Very Low",
                "Low",
                "Moderate",
                "High",
                "Very High"
        };

        cNumberOfBars = new JComboBox<>(numberOfBars);
        cNumberOfBars.setBorder(BorderFactory.createBevelBorder(1));
        cNumberOfBars.setSelectedItem(DEFAULT_BAR_WIDTH);

        cIntervals = new JComboBox<>();
        cIntervals.setBorder(BorderFactory.createBevelBorder(1));

        final int[] intervals = new int[]{
                8,
                10,
                15,
                20,
                30,
                50,
                100
        };

        for (final int i : intervals) {
            cIntervals.addItem(i);
        }
        cIntervals.setSelectedItem(DEFAULT_INTERVAL);

        cAlgorithms = new JComboBox<>();
        cAlgorithms.setBorder(BorderFactory.createBevelBorder(1));

        for (final ESortingAlgorithm algorithm : ESortingAlgorithm.values()) {
            cAlgorithms.addItem(algorithm.name);
        }

        final JLabel lNumberOfBars = new JLabel("Number of values:");
        final JLabel lIntervals = new JLabel("Interval (in milliseconds):");
        final JLabel lAlgorithms = new JLabel("Algorithm:");

        this.setBorder(BorderFactory.createTitledBorder("Menu"));
        this.setLayout(new GridBagLayout());

        final GridBagConstraints gb = new GridBagConstraints();

        gb.anchor = GridBagConstraints.CENTER;
        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.weightx = 1;
        gb.weighty = 1;

        int i = 0, j = 0;

        gb.gridx = i++;
        gb.gridy = j++;
        this.add(spaces[0], gb);

        gb.weightx = 0.3;
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

    JButton getbSort() {
        return bSort;
    }

    int getBarWidth() {
        final Object selectedDensity = cNumberOfBars.getSelectedItem();
        switch (selectedDensity == null ? DEFAULT_BAR_WIDTH : selectedDensity.toString()) {
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
        }

        // Should never reach this return
        return 10;
    }

    int getInterval() {
        return (int) cIntervals.getSelectedItem();
    }

    ESortingAlgorithm getSelectedAlgorithm() {
        final Object algorithmName = cAlgorithms.getSelectedItem();
        if (algorithmName == null) {
            return DEFAULT_ALGORITHM;
        }

        return Arrays.stream(ESortingAlgorithm.values())
                .filter(sortingAlgorithm -> sortingAlgorithm.name.equals(algorithmName))
                .findFirst()
                .orElse(DEFAULT_ALGORITHM);
    }
}
