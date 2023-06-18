package hr.mlinx.i;

import hr.mlinx.util.Sort;

public class InsertionSort implements Sort {

    @Override
    public int[] mutableIntSort(int[] arr) {
        for (int i = 1, j, key; i < arr.length; ++i) {
            key = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = key;
        }

        return arr;
    }

}
