package domain.helpers;


import domain.ArrayOperation;
import domain.CompareElements;

import java.util.List;

public class QuickSortHelper {

    public static void quickSort(final int[] array, final int start, final int end, final List<ArrayOperation> operations) {
        if (start < end) {
            int pivot = partition(array, start, end, operations);

            quickSort(array, start, pivot - 1, operations);
            quickSort(array, pivot + 1, end, operations);
        }
    }

    private static int partition(final int[] array, final int low, final int high, final List<ArrayOperation> operations) {
        int pivot = array[high];

        int i = (low - 1);
        for (int j = low; j < high; j++) {

            operations.add(new CompareElements(j, high));
            if (array[j] <= pivot) {
                i++;
                ArrayHelper.swap(array, operations, i, j);
            }
        }

        ArrayHelper.swap(array, operations, i + 1, high);

        return i + 1;
    }
}
