package enuns;

import domain.*;
import domain.helpers.*;
import interfaces.SortingSolution;

public enum ESortingAlgorithm {

    SelectionSort("Selection Sort", (array, operations) -> {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                operations.add(new CompareElements(j, minIndex));
                if (array[j] < array[minIndex])
                    minIndex = j;
            }

            ArrayHelper.swap(array, operations, i, minIndex);
        }
    }),

    BubbleSort("Bubble Sort", (array, operations) -> {
        int i, j;
        boolean swapped;

        for (i = 0; i < array.length - 1; i++) {
            swapped = false;

            for (j = 0; j < array.length - i - 1; j++) {
                operations.add(new CompareElements(j, j + 1));
                if (array[j] > array[j + 1]) {
                    ArrayHelper.swap(array, operations, j, j + 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }),

    CocktailSort("Cocktail Sort", (array, operations) -> {
        int i, j;
        boolean swapped;

        for (i = 0; i < array.length / 2; i++) {
            swapped = false;

            for (j = i; j < array.length - i - 1; j++) {
                operations.add(new CompareElements(j, j + 1));
                if (array[j] > array[j + 1]) {
                    ArrayHelper.swap(array, operations, j, j + 1);
                    swapped = true;
                }
            }

            for (j = array.length - 1 - i; j > i; j--) {

                operations.add(new CompareElements(j, j - 1));
                if (array[j] < array[j - 1]) {
                    ArrayHelper.swap(array, operations, j, j - 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }),

    OddevenSort("Odd-Even Sort", (array, operations) -> {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;

            for (int i = 1; i < array.length - 1; i += 2) {

                operations.add(new CompareElements(i, i + 1));
                if (array[i] > array[i + 1]) {
                    ArrayHelper.swap(array, operations, i, i + 1);
                    sorted = false;
                }
            }

            for (int i = 0; i < array.length - 1; i += 2) {

                operations.add(new CompareElements(i, i + 1));
                if (array[i] > array[i + 1]) {
                    ArrayHelper.swap(array, operations, i, i + 1);
                    sorted = false;
                }
            }
        }
    }),

    ComboSort("Combo Sort", ((array, operations) -> {
        int gap = array.length;
        boolean swapped = true;

        while (gap > 1 || swapped) {
            if (gap > 1) {
                gap = (int) (gap / 1.247330950103979);
            }
            int i = 0;
            swapped = false;
            while (i + gap < array.length) {

                operations.add(new CompareElements(i, i + gap));
                if (array[i] > (array[i + gap])) {
                    ArrayHelper.swap(array, operations, i, i + gap);
                    swapped = true;
                }
                i++;
            }
        }
    })),

    InsertionSort("Insertion Sort", (array, operations) -> {
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
    }),

    ShellSort("Shell Sort", (array, operations) -> {
        final int n = array.length;

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
    }),

    MergeSort("Merge Sort", (array, operations) -> MergeSortHelper.mergeSort(array, 0, array.length - 1, operations)),

    QuickSort("Quick Sort", (array, operations) -> QuickSortHelper.quickSort(array, 0, array.length - 1, operations)),

    DualPivotQuickSort("Dual Pivot Quick Sort", (array, operations) -> DualPivotQuickSortHelper.dualPivotQuickSort(array, 0, array.length - 1, operations)),

    HeapSort("Heap Sort", (array, operations) -> {
        final int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            HeapSortHelper.heapify(array, n, i, operations);
        }

        for (int i = n - 1; i >= 0; i--) {
            ArrayHelper.swap(array, operations, 0, i);
            HeapSortHelper.heapify(array, i, 0, operations);
        }
    }),

    RadixSort("Radix Sort", (array, operations) -> {
        int max = RadixSortHelper.getMax(array, operations);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            RadixSortHelper.countSort(array, exp, operations);
        }
    }),

    CycleSort("Cycle Sort", (CycleSortHelper::cycleSort));

    public String name;
    public SortingSolution sortingSolution;

    ESortingAlgorithm(String name, final SortingSolution sortingSolution) {
        this.name = name;
        this.sortingSolution = sortingSolution;
    }
}
