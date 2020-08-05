package domain.helpers;

import domain.ArrayOperation;
import domain.CompareElements;
import domain.SwapElements;

import java.util.List;

public class MergeSortHelper {

    public static void mergeSort(int[] array, int start, int end, final List<ArrayOperation> operations) {
        if (end - start < 1)
            return;

        int middle = (end + start) / 2;

        mergeSort(array, start, middle, operations);
        mergeSort(array, middle + 1, end, operations);
        merge(array, start, middle, end, operations);
    }

    private static void merge(int[] array, int start, int middle, int end, final List<ArrayOperation> operations) {
        int range = end - start + 1;
        int[] merged = new int[range];
        int mergedIndex = 0;
        int leftIndex = start;
        int rightIndex = middle + 1;

        int i;
        for (i = 0; i < range; i++, mergedIndex++) {

            operations.add(new CompareElements(leftIndex, middle));
            if (leftIndex > middle) {
                merged[mergedIndex] = array[rightIndex];
                rightIndex++;
            } else {
                operations.add(new CompareElements(rightIndex, end));
                if (rightIndex > end) {
                    merged[mergedIndex] = array[leftIndex];
                    leftIndex++;
                } else {
                    operations.add(new CompareElements(leftIndex, rightIndex));
                    if (array[leftIndex] < array[rightIndex]) {
                        merged[mergedIndex] = array[leftIndex];
                        leftIndex++;
                    } else {
                        merged[mergedIndex] = array[rightIndex];
                        rightIndex++;
                    }
                }
            }
        }

        for (i = 0; i < range; i++) {
            operations.add(new SwapElements(i + start, i));
            array[i + start] = merged[i];
        }
    }
}
