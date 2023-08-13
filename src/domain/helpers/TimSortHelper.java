package domain.helpers;

import domain.ArrayOperation;
import domain.CompareElements;
import domain.OverrideValue;

import java.util.List;

public class TimSortHelper {

    private static final int RUN = 32;

    public static void timSort(int[] array, List<ArrayOperation> operations) {
        timSort(array, array.length, operations);
    }

    private static void timSort(int[] array, int n, List<ArrayOperation> operations) {
        for (int i = 0; i < n; i += RUN) {
            selectionSort(array, i, Math.min((i + RUN - 1), (n - 1)), operations);
        }

        for (int size = RUN; size < n; size = 2 * size) {
            for (int start = 0; start < n; start += 2 * size) {
                int mid = start + size - 1;
                int end = Math.min((start + 2 * size - 1), (n - 1));

                if (mid < end) {
                    merge(array, start, mid, end, operations);
                }
            }
        }
    }

    private static void selectionSort(int[] array, int start, int end, List<ArrayOperation> operations) {
        for (int i = start; i <= end; i++) {
            int smallest = array[i];
            int smallestIndex = i;

            for (int j = i; j <= end; j++) {
                operations.add(new CompareElements(j, smallestIndex));
                if (array[j] < smallest) {
                    smallestIndex = j;
                    smallest = array[j];
                }
            }

            if (smallestIndex != i) {
                ArrayHelper.swap(array, operations, i, smallestIndex);
            }
        }
    }

    private static void merge(int[] array, int start, int mid, int end, List<ArrayOperation> operations) {
        int n1 = mid - start + 1;
        int n2 = end - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(array, start, leftArray, 0, n1);
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }
        int i = 0;
        int j = 0;
        int k = start;

        while (i < n1 && j < n2) {
            operations.add(new CompareElements(i, j));
            if (leftArray[i] <= rightArray[j]) {
                operations.add(new OverrideValue(k, leftArray[i]));
                array[k] = leftArray[i];
                i++;
            } else {
                operations.add(new OverrideValue(k, rightArray[j]));
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            operations.add(new OverrideValue(k, leftArray[i]));
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            operations.add(new OverrideValue(k, rightArray[j]));
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

}
