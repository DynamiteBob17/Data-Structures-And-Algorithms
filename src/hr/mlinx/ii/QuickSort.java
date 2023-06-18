package hr.mlinx.ii;

import hr.mlinx.util.Sort;
import hr.mlinx.util.Util;

public class QuickSort implements Sort {

    @Override
    public int[] mutableIntSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high);
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        // choose random element in the range and swap it with element at index 'high'
        swap(arr, Util.R.nextInt(low, high + 1), high);

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j <= high - 1; ++j) { // ignore last index as it is the partition
            if (arr[j] <= pivot) {
                swap(arr, ++i, j);
            }
        }

        swap(arr, ++i, high);

        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
