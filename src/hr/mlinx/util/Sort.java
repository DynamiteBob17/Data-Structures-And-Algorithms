package hr.mlinx.util;

import java.util.Arrays;

public interface Sort {

    int[] mutableIntSort(int[] arr);

    default int[] immutableIntSort(int[] arr) {
        return mutableIntSort(arr.clone());
    }

}
