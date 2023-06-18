package hr.mlinx.ii;

import static hr.mlinx.ii.QuickSort.partition;

public class OrderStatistics {

    public static int randomizedSelect(int[] arr, int low, int high, int orderStatistic) {
        if (low == high)
            return arr[low];

        int partitionIndex = partition(arr, low, high);
        int k = partitionIndex - low + 1;

        if (orderStatistic == k)
            return arr[partitionIndex];
        else if (orderStatistic < k)
            return randomizedSelect(arr, low, partitionIndex - 1, orderStatistic);
        else
            return randomizedSelect(arr, partitionIndex + 1, high, orderStatistic - k);
    }

}
