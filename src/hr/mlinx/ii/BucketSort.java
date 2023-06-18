package hr.mlinx.ii;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class BucketSort {

    public List<Double> immutableDoubleSort(double[] arr) {
        List<List<Double>> buckets = new ArrayList<>();

        for (int i = 0; i < arr.length; ++i)
            buckets.add(new ArrayList<>());

        for (double value : arr)
            buckets.get((int) Math.floor(arr.length * value))
                    .add(value);

        // "sort each bucket with insertion sort", or whatever sort the method uses :)
        buckets.forEach(bucket -> bucket.sort(Comparator.naturalOrder()));

        return buckets.stream()
                .flatMap(Collection::stream)
                .toList();
    }

}
