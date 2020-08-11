package interfaces;

import domain.ArrayOperation;

import java.util.List;

public interface SortingSolution {

    // The list passed as a parameter should by empty and will be filled with the steps to order the array
    void sort(final int[] array, final List<ArrayOperation> operations);
}
