package domain.helpers;

import domain.ArrayOperation;
import domain.SwapElements;

import java.util.List;

public class ArrayHelper {

    public static void swap(final int[] array, final List<ArrayOperation> operations, final int indexA, final int indexB) {
        operations.add(new SwapElements(indexA, indexB));

        int temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }
}
