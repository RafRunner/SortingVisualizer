package tests;

import domain.ArrayOperation;
import domain.helpers.ShuffleHelper;
import enuns.ESortingAlgorithm;

import java.util.List;
import java.util.Vector;

public class TestSortingAlgorithms {

    private interface ListFactory {
        List<ArrayOperation> getList();
    }

    private static int[] getRandomArray(final int size) {
        final int[] array = new int[size];
        ShuffleHelper.shuffle(array);
        return array;
    }

    private static boolean assertSortedArray(final int[] array) {
        for (int i = 0; i < array.length - 1; i++) {

            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static void run(final ListFactory listFactory) {
        final int[] arraySizesToTest = {2, 10, 50, 100, 1000, 2000};

        for (final int size : arraySizesToTest) {

            for (final ESortingAlgorithm sortingAlgorithm : ESortingAlgorithm.values()) {

                final List<ArrayOperation> operations = listFactory.getList();
                final int[] randomArray = getRandomArray(size);

                final long time = Benchmark.benchmark((args) -> sortingAlgorithm.sortingSolution.sort((int[]) args[0], (List<ArrayOperation>) args[1]), new Object[]{randomArray, operations});

                if (!assertSortedArray(randomArray)) {
                    System.out.println("Algorithm " + sortingAlgorithm.name + " failed to sort properly array of size " + size);
                }
                System.out.println("Algorithm " + sortingAlgorithm.name + " took " + time + " ns sorting a array of size " + size);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("\nRunning tests using Vector:\n");
        run(Vector::new);
    }
}
