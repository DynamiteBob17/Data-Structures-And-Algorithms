package hr.mlinx.ii;

import hr.mlinx.util.Runner;
import hr.mlinx.util.Sort;

import java.util.Arrays;
import java.util.Comparator;

import static hr.mlinx.util.Util.*;

public class MainII {

    public static void main(String[] args) {
        Heap<Integer> minHeap = new Heap<>(Integer.class);
        testIntegerHeap(minHeap);
        Heap<Integer> maxHeap = new Heap<>(Comparator.reverseOrder(), Integer.class);
        testIntegerHeap(maxHeap);

        int[] ints = randInts(10000, 0, 200);
        int[] counts = { 10, 100 };
        String nEquals = " (n=" + ints.length + ")";
        Sort quickSort = new QuickSort();
        Runner.run("Quick Sort" + nEquals, quickSort::immutableIntSort, ints, counts);
        int[] smallerInts = randInts(20, 0, 200);
        printInts(smallerInts);
        printInts(quickSort.immutableIntSort(smallerInts));

        // put limit 'k' at last position in array to accommodate limitations of the Runner class
        int[] newInts = new int[ints.length + 1];
        int[] newSmallerInts = new int[smallerInts.length + 1];
        System.arraycopy(ints, 0, newInts, 0, ints.length);
        System.arraycopy(smallerInts, 0, newSmallerInts, 0, smallerInts.length);
        newInts[newInts.length - 1] = newSmallerInts[newSmallerInts.length - 1] = 199; // "ints" method's upper bound is exclusive
        Sort countingSort = new CountingSort();
        Runner.run("Counting Sort" + nEquals, countingSort::immutableIntSort, newInts, counts);
        printInts(newSmallerInts);
        printInts(countingSort.immutableIntSort(newSmallerInts));

        Sort radixSort = new RadixSort();
        Runner.run("Radix sort:" + nEquals, radixSort::immutableIntSort, ints, counts);
        printInts(smallerInts);
        printInts(radixSort.immutableIntSort(smallerInts));

        BucketSort bucketSort = new BucketSort();
        double[] doubles = randDoubles(10000, 0, 1);
        double[] smallerDoubles = randDoubles(20, 0, 1);
        Runner.run("Bucket sort" + nEquals, bucketSort::immutableDoubleSort, doubles, counts);
        printDoubles(smallerDoubles);
        System.out.println(bucketSort.immutableDoubleSort(smallerDoubles));

        ints = new int[] {41, 123, 44, 26, 137, 63, 108, 146, 43, 75, 87, 112, 121, 64, 31, 73, 98, 8, 53, 133};
        printInts(ints);
        printInts(quickSort.immutableIntSort(ints));
        System.out.println(OrderStatistics.randomizedSelect(ints, 0, ints.length - 1, 5));
    }

    private static void testIntegerHeap(Heap<Integer> heap) {
        heap.add(54, 2, 70, 19, 3, 21, 7, 1, 5);
        System.out.println("sorted: " + Arrays.toString(heap.getSortedArray()));
        System.out.println("added multiple before polling: " + heap);
        System.out.println("polled: " + heap.poll());
        System.out.println("after polling: " + heap);
        heap.clear();
        heap.add(54);
        heap.add(2);
        heap.add(70);
        heap.add(19);
        heap.add(3);
        heap.add(21);
        heap.add(7);
        heap.add(1);
        heap.add(5);
        System.out.println("added one by one before polling: " + heap);
        System.out.println("polled: " + heap.poll());
        System.out.println("after polling: " + heap);
        System.out.println();
    }

}
