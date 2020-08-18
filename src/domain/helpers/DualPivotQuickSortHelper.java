package domain.helpers;

import domain.ArrayOperation;
import domain.CompareElements;

import java.util.List;

public class DualPivotQuickSortHelper {

    public static void dualPivotQuickSort(final int[] arr, final int low, final int high, final List<ArrayOperation> operations) {
        if (low < high) {
            // piv[] stores left pivot and right pivot. 
            // piv[0] means left pivot and 
            // piv[1] means right pivot 
            int[] piv;
            piv = partition(arr, low, high, operations);

            dualPivotQuickSort(arr, low, piv[0] - 1, operations);
            dualPivotQuickSort(arr, piv[0] + 1, piv[1] - 1, operations);
            dualPivotQuickSort(arr, piv[1] + 1, high, operations);
        }
    }

    private static int[] partition(final int[] arr, final int low, final int high, final List<ArrayOperation> operations) {
        if (arr[low] > arr[high])
            ArrayHelper.swap(arr, operations, low, high);

        // p is the left pivot, and q  
        // is the right pivot. 
        int j = low + 1;
        int g = high - 1;
        int k = low + 1;
        int p = arr[low];
        int q = arr[high];

        while (k <= g) {

            // If elements are less than the left pivot
            operations.add(new CompareElements(k, low));
            if (arr[k] < p) {
                ArrayHelper.swap(arr, operations, k, j);
                j++;
            }

            // If elements are greater than or equal 
            // to the right pivot
            else {
                operations.add(new CompareElements(k, high));
                if (arr[k] >= q) {
                    operations.add(new CompareElements(g, high));
                    while (arr[g] > q && k < g)
                        g--;

                    ArrayHelper.swap(arr, operations, k, g);
                    g--;

                    if (arr[k] < p) {
                        ArrayHelper.swap(arr, operations, k, j);
                        j++;
                    }
                }
            }
            k++;
        }
        j--;
        g++;

        // Bring pivots to their appropriate positions. 
        ArrayHelper.swap(arr, operations, low, j);
        ArrayHelper.swap(arr, operations, high, g);

        // Returning the indices of the pivots
        // because we cannot return two elements
        // from a function, we do that using an array.
        return new int[]{j, g};
    }
}
