package domain.helpers;


import domain.ArrayOperation;
import domain.CompareElements;
import domain.SwapElements;

import java.util.List;

public class QuickSortHelper {

    public static void quickSort(int[] array, int start, int end, final List<ArrayOperation> operations) {
        if (start < end) {
            int pivot = partition(array, start, end, operations);

            quickSort(array, start, pivot - 1, operations);
            quickSort(array, pivot + 1, end, operations);
        }
    }

    private static int partition(int[] array, int low, int high, final List<ArrayOperation> operations) {
        int pivot = array[high];

        int i = (low - 1);
        for (int j = low; j < high; j++) {

            operations.add(new CompareElements(j, high));
            if (array[j] <= pivot) {

                i++;

                operations.add(new SwapElements(i, j));
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        operations.add(new SwapElements(i + 1, high));
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}
