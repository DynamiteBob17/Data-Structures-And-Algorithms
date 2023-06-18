package hr.mlinx.ii;

import hr.mlinx.util.Sort;

public class CountingSort implements Sort {

    @Override
    public int[] mutableIntSort(int[] arr) {
        // assume last element in 'arr' is upper bound of range, not important for sorting
        // (to accommodate the Runner class since right now it only allows for one method argument)
        int k = arr[arr.length - 1];
        int len = arr.length - 1;

        int[] b = new int[len];
        int[] c = new int[k + 1];

        for (int i = 0; i < len; ++i)
            ++c[arr[i]];

        for (int i = 1; i <= k; ++i)
            c[i] += c[i - 1];

        for (int i = len - 1; i >= 0; --i) {
            b[c[arr[i]] - 1] = arr[i];
            --c[arr[i]];
        }

        System.arraycopy(b, 0, arr, 0, len);

        return arr;
    }

    public void digitBased(int[] arr, int exp) {
        // do not assume what is assumed in the mutableIntSort method
        int k = 10; // decimal numbers

        int[] b = new int[arr.length];
        int[] c = new int[k];

        for (int value : arr)
            ++c[(value / exp) % 10];

        for (int i = 1; i < k; ++i)
            c[i] += c[i - 1];

        for (int i = arr.length - 1; i >= 0; --i) {
            b[c[(arr[i] / exp) % 10] - 1] = arr[i];
            --c[(arr[i] / exp % 10)];
        }

        System.arraycopy(b, 0, arr, 0, arr.length);
    }

}
