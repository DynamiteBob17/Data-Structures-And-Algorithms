package hr.mlinx.i;

import hr.mlinx.util.Sort;

public class BubbleSort implements Sort {

    @Override
    public int[] mutableIntSort(int[] arr) {
        for (int i = 0, temp; i < arr.length - 1; ++i) {
            for (int j = arr.length - 1; j >= i + 1; --j) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }

        return arr;
    }

}
