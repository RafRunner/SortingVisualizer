package domain.helpers;

import domain.ArrayOperation;
import domain.CompareElements;
import domain.SwapElements;

import java.util.Arrays;
import java.util.List;

public class RadixSortHelper {

    public static int getMax(final int[] array, final List<ArrayOperation> operations) {
        int mx = array[0];
        int mxIndex = 0;
        for (int i = 1; i < array.length; i++) {

            operations.add(new CompareElements(i, mxIndex));
            if (array[i] > mx) {
                mx = array[i];
                mxIndex = i;
            }

        }

        return mx;
    }

    public static void countSort(final int[] array, final int exp, final List<ArrayOperation> operations) {
        int length = array.length;
        int i;

        int[] output = new int[length]; // output array
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < length; i++)
            count[(array[i] / exp) % 10]++;

        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = length - 1; i >= 0; i--) {
            int outputIndex = count[(array[i] / exp) % 10] - 1;
            operations.add(new SwapElements(outputIndex, i));

            output[outputIndex] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        for (i = 0; i < length; i++) {
            array[i] = output[i];
        }
    }
}
