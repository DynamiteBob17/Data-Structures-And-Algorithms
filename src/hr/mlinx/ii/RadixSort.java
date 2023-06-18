package hr.mlinx.ii;

import hr.mlinx.util.Sort;

import java.util.Arrays;
import java.util.OptionalInt;

public class RadixSort implements Sort {

    @Override
    public int[] mutableIntSort(int[] arr) {
        OptionalInt maxOptional = Arrays.stream(arr).max();
        CountingSort countingSort = new CountingSort();

        if (maxOptional.isPresent()) {
            int max = maxOptional.getAsInt();
            for (int exp = 1; max / exp > 0; exp *= 10)
                countingSort.digitBased(arr, exp);
        } else {
            throw new IllegalArgumentException("Array is empty.");
        }

        return arr;
    }

}
