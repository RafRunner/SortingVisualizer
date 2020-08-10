package domain.helpers;

import domain.ArrayOperation;
import domain.CompareElements;
import domain.OverrideValue;

import java.util.List;

public class MergeSortHelper {

    public static void mergeSort(final int[] array, final int start, final int end, final List<ArrayOperation> operations) {
        if (end - start < 1) {
            return;
        }

        int middle = (end + start) / 2;

        mergeSort(array, start, middle, operations);
        mergeSort(array, middle + 1, end, operations);
        merge(array, start, middle, end, operations);
    }

    private static void merge(final int[] array, final int start, final int middle, final int end, final List<ArrayOperation> operations) {
        int range = end - start + 1;
        int[] merged = new int[range];
        int leftIndex = start;
        int rightIndex = middle + 1;

        for (int mergedIndex = 0; mergedIndex < range; mergedIndex++) {

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

        for (int i = 0; i < range; i++) {
            operations.add(new OverrideValue(i + start, merged[i]));
            array[i + start] = merged[i];
        }
    }
}
