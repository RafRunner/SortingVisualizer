package domain.helpers;

import domain.ArrayOperation;
import domain.SwapElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShuffleHelper {

    public static List<ArrayOperation> shuffle(final int[] array) {
        final List<ArrayOperation> operations = new ArrayList<>();

        Random rng = new Random();

        for(int i = 0; i < array.length; i++) {
            int index = rng.nextInt(i + 1);

            operations.add(new SwapElements(i, index));

            int aux = array[index];
            array[index] = array[i];
            array[i] = aux;
        }
        
        return operations;
    }
}
