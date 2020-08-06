package enuns;

import domain.*;
import domain.helpers.HeapSortHelper;
import domain.helpers.MergeSortHelper;
import domain.helpers.QuickSortHelper;
import domain.helpers.RadixSortHelper;
import interfaces.SortingSolution;

import java.util.ArrayList;
import java.util.List;

public enum ESortingAlgorithm {

    SelectionSort("Selection Sort", array -> {
        final List<ArrayOperation> operations = new ArrayList<>();

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                operations.add(new CompareElements(j, minIndex));

                if (array[j] < array[minIndex])
                    minIndex = j;
            }

            operations.add(new SwapElements(i, minIndex));

            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }

        return operations;
    }),

    BubbleSort("Bubble Sort", array -> {
        final List<ArrayOperation> operations = new ArrayList<>();

        int i, j, temp;
        boolean swapped;

        for (i = 0; i < array.length - 1; i++) {
            swapped = false;

            for (j = 0; j < array.length - i - 1; j++) {
                operations.add(new CompareElements(j, j + 1));

                if (array[j] > array[j + 1]) {

                    operations.add(new SwapElements(j, j + 1));
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return operations;
    }),

    InsertionSort("Insertion Sort", array -> {
        final List<ArrayOperation> operations = new ArrayList<>();

        int n = array.length;
        for (int i = 1; i < n; ++i) {

            int key = array[i];
            int j = i - 1;

            operations.add(new CompareElements(j, i));
            while (j >= 0 && array[j] > key) {
                operations.add(new SwapElements(j, j + 1));
                array[j + 1] = array[j];
                j = j - 1;

            }
            array[j + 1] = key;
        }

        return operations;
    }),

    MergeSort("Merge Sort", array -> {
        final List<ArrayOperation> operations = new ArrayList<>();

        MergeSortHelper.mergeSort(array, 0, array.length - 1, operations);

        return operations;
    }),

    QuickSort("Quick Sort", array -> {
        final List<ArrayOperation> operations = new ArrayList<>();

        QuickSortHelper.quickSort(array, 0, array.length - 1, operations);

        return operations;
    }),

    HeapSort("Heap Sort", array -> {
        final List<ArrayOperation> operations = new ArrayList<>();

        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            HeapSortHelper.heapify(array, n, i, operations);

        for (int i = n - 1; i >= 0; i--) {
            operations.add(new SwapElements(0, i));

            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;


            HeapSortHelper.heapify(array, i, 0, operations);
        }

        return operations;
    }),

    RadixSort("Radix Sort", array -> {
        final List<ArrayOperation> operations = new ArrayList<>();

        int max = RadixSortHelper.getMax(array, operations);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            RadixSortHelper.countSort(array, exp, operations);
        }

        return operations;
    }),

    ShellSort("Shell Sort", array -> {
        final List<ArrayOperation> operations = new ArrayList<>();

        int n = array.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {

            for (int i = gap; i < n; i += 1) {
                int temp = array[i];

                int j;
                for (j = i; j >= gap; j -= gap) {
                    operations.add(new CompareElements(i, j - gap));
                    if (array[j - gap] < temp)
                        break;

                    operations.add(new SwapElements(j, j - gap));
                    array[j] = array[j - gap];
                }

                array[j] = temp;
            }
        }

        return operations;
    });

    public String name;
    public SortingSolution sortingSolution;

    ESortingAlgorithm(String name, final SortingSolution sortingSolution) {
        this.name = name;
        this.sortingSolution = sortingSolution;
    }
}
