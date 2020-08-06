package main;

import domain.ArrayOperation;
import enuns.ESortingAlgorithm;
import view.MainWindow;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<ArrayOperation> teste = ESortingAlgorithm.MergeSort.sortingSolution.getSortingSteps(new int[]{4,9,7,1});
        new MainWindow().showMenu();
    }
}
