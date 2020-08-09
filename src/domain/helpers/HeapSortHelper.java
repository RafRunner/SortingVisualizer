package domain.helpers;

import domain.ArrayOperation;
import domain.CompareElements;
import domain.SwapElements;

import java.util.List;

public class HeapSortHelper {

    public static void heapify(final int[] array, final int n, final int i, final List<ArrayOperation> operations) {
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        operations.add(new CompareElements(l, largest));
        if (l < n && array[l] > array[largest])
            largest = l;

        operations.add(new CompareElements(r, largest));
        if (r < n && array[r] > array[largest])
            largest = r;

        if (largest != i) {
            operations.add(new SwapElements(i, largest));

            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(array, n, largest, operations);
        }
    }
}
