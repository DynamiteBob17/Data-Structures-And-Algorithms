package hr.mlinx.ii;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * heap / priority queue
 * @param <T> implements Comparable
 */

public class Heap<T extends Comparable<T>> {

    private static final int INITIAL_LENGTH = 16;

    private final Comparator<T> comparator;
    private T[] heap;
    private int size;

    public Heap(Comparator<T> comparator, Class<T> clazz) {
        this.comparator = comparator;
        heap = instantiate(clazz, INITIAL_LENGTH);
        size = 0;
    }

    public Heap(Class<T> clazz) {
        this(Comparator.naturalOrder(), clazz);
    }

    private T[] instantiate(Class<T> clazz, int length) {
        return (T[]) Array.newInstance(clazz, length);
    }

    private void expand() {
        T[] expanded = instantiate((Class<T>) heap[0].getClass(), heap.length * 2);
        System.arraycopy(heap, 0, expanded, 0, heap.length);
        heap = expanded;
    }

    private void fitElements(int amount) {
        int newSize = size + amount;
        while (heap.length < newSize)
            expand();
    }

    private void heapify(int i) {
        int left = left(i);
        int right = right(i);
        int minOrMax;

        minOrMax = (left < size && comparator.compare(heap[left], heap[i]) < 0)
                ? left
                : i;

        if (right < size && comparator.compare(heap[right], heap[minOrMax]) < 0)
            minOrMax = right;

        if (minOrMax != i) {
            swap(i, minOrMax);
            heapify(minOrMax);
        }
    }

    private void buildHeap() {
        for (int i = (size / 2) - 1; i >= 0; --i)
            heapify(i);
    }

    private void heapifyBottomUp(int i) {
        int parent = parent(i);

        if (comparator.compare(heap[i], heap[parent]) < 0) {
            swap(i, parent);
            heapifyBottomUp(parent);
        }
    }

    public void add(T element) {
        fitElements(1);
        heap[++size - 1] = element;
        heapifyBottomUp(size - 1);
    }

    @SafeVarargs
    public final void add(T... elements) {
        fitElements(elements.length);

        for (T element : elements)
            heap[++size - 1] = element;

        buildHeap();
    }

    public T peek() {
        return heap[0];
    }

    public T poll() {
        T root = peek();

        heap[0] = heap[size - 1];
        heap[size-- - 1] = null;
        heapify(0);

        return root;
    }

    public void clear() {
        for (int i = 0; i < size; ++i) {
            heap[i] = null;
        }
        size = 0;
    }

    public T[] getSortedArray() {
        T[] heapBefore = Arrays.copyOf(heap, heap.length);
        int sizeBefore = size;

        T[] sortedArray = instantiate((Class<T>) heap[0].getClass(), sizeBefore);
        for (int i = 0; i < sizeBefore; ++i) {
            sortedArray[i] = poll();
        }

        heap = heapBefore;
        size = sizeBefore;

        return sortedArray;
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int parent(int i) {
        return i >> 1;
    }

    private int left(int i) {
        return i << 1;
    }

    private int right(int i) {
        return left(i) + 1;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(heap, 0, size));
    }

}
