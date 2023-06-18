package hr.mlinx.i;

import hr.mlinx.util.Sort;

import java.util.Arrays;

public class MergeSort implements Sort {

    @Override
    public int[] mutableIntSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left >= right)
            return;

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        // "to" parameter of the copyOfRange method is exclusive
        int[] leftAux = Arrays.copyOfRange(arr, left, mid + 1),
                rightAux = Arrays.copyOfRange(arr, mid + 1, right + 1);
        int i = 0, j = 0, k = left;

        while (i < leftAux.length && j < rightAux.length)
            arr[k++] = leftAux[i] <= rightAux[j]
                    ? leftAux[i++]
                    : rightAux[j++];

        // at this point one of the indices will have reached its end,
        // so only one of the loops below will iterate

        while (i < leftAux.length)
            arr[k++] = leftAux[i++];

        while (j < rightAux.length)
            arr[k++] = rightAux[j++];
    }

}
