package domain.helpers;

import domain.ArrayOperation;
import domain.CompareElements;
import domain.OverrideValue;

import java.util.List;

public class CycleSortHelper {

    public static void cycleSort(final int[] array, final List<ArrayOperation> operations) {
        // Loop through the array to find cycles to rotate.
        for (int cycleStart = 0; cycleStart < array.length - 1; cycleStart++) {
            int item = array[cycleStart];

            // Find where to put the item.
            int pos = cycleStart;
            for (int i = cycleStart + 1; i < array.length; i++) {
                if (array[i] < item)
                    pos++;
            }

            // If the item is already there, this is not a cycle.
            if (pos == cycleStart)
                continue;

            // Otherwise, put the item there or right after any duplicates.
            operations.add(new CompareElements(cycleStart, pos));
            while (item == array[pos])
                pos++;

            operations.add(new OverrideValue(pos, item));
            int temp = array[pos];
            array[pos] = item;
            item = temp;

            // Rotate the rest of the cycle.
            while (pos != cycleStart) {

                // Find where to put the item.
                pos = cycleStart;

                for (int i = cycleStart + 1; i < array.length; i++) {
                    if (array[i] < item)
                        pos++;
                }

                // Put the item there or right after any duplicates.
                operations.add(new CompareElements(pos, cycleStart));
                while (item == array[pos])
                    pos++;

                operations.add(new OverrideValue(pos, item));
                temp = array[pos];
                array[pos] = item;
                item = temp;
            }
        }
    }
}
