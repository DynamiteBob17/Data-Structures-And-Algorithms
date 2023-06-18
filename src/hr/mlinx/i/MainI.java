package hr.mlinx.i;

import hr.mlinx.util.Runner;
import hr.mlinx.util.Sort;

import static hr.mlinx.util.Util.*;

public class MainI {

    public static void main(String[] args) {
        int[] ints = randInts(10000, -100, 100);
        int[] counts = { 10, 100 };
        String nEquals = " (n=" + ints.length + ")";
        Sort insertion = new InsertionSort();
        Sort merge = new MergeSort();
        Sort bubble = new BubbleSort();
        Runner.run("Insertion sort" + nEquals, insertion::immutableIntSort, ints, counts);
        Runner.run("Merge Sort" + nEquals, merge::immutableIntSort, ints, counts);
        Runner.run("Bubble sort" + nEquals, bubble::immutableIntSort, ints, counts);
        int[] smallerInts = randInts(20, 1, 100);
        printInts(smallerInts);
        printInts(insertion.immutableIntSort(smallerInts));
        printInts(merge.immutableIntSort(smallerInts));
        printInts(bubble.immutableIntSort(smallerInts));

        // Horner's rule for polynomials
        int magnitude = 3;
        int[] coefficients = new int[] { -7, 3, 5, -2}; // coefficients for x^0, x^1, x^2, ...
        int x = 6;
        double result = 0;
        for (int i = magnitude; i >= 0; --i) {
            result = coefficients[i] + x * result;
        }
        System.out.println(result);

        System.out.println(GoldenRatio.calculate(33));

        int[][] a = {{1,2,3},{4,5,6},{7,8,9}},
                b = {{10,11,12},{13,14,15},{16,17,18}},
                resultMatrix = new int[3][3];
        print2DInts(Matrix.multiplyIteratively(a, b, resultMatrix));

        System.out.println(LiterallyRandomStuff.birthdayParadox(10, 10000));
        System.out.println(LiterallyRandomStuff.birthdayParadox(23, 10000));
        System.out.println(LiterallyRandomStuff.birthdayParadox(50, 10000));
        System.out.println(LiterallyRandomStuff.birthdayParadox(100, 10000));

        System.out.println(LiterallyRandomStuff.ballsInBin(100000, 10));
        System.out.println(LiterallyRandomStuff.tossesUntilBinHasBall(100000));
        System.out.println(LiterallyRandomStuff.tossesUntilEveryBinHasOneOrMore(10));

        System.out.println(LiterallyRandomStuff.longestStreakOfHeads(1000));

        System.out.println(LiterallyRandomStuff.onlineHiringProblem(10, 10000));
    }

}
