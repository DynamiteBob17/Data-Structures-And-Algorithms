package hr.mlinx.util;

import java.util.Arrays;
import java.util.Random;

public class Util {

    public static final Random R = new Random();
    public static final Random R_SEEDED = new Random(-13);

    public static void printInts(int[] ints) {
        if (ints.length <= 100)
            System.out.println(Arrays.toString(ints));
    }

    public static void printDoubles(double[] doubles) {
        if (doubles.length <= 100)
            System.out.println(Arrays.toString(doubles));
    }

    public static void print2DInts(int[][] ints) {
        System.out.println("2d array:");
        Arrays.stream(ints).forEach(subArray -> System.out.println("\t" + Arrays.toString(subArray)));
    }

    public static void print2DDoubles(int[][] doubles) {
        System.out.println("2d array:");
        Arrays.stream(doubles).forEach(subArray -> System.out.println("\t" + Arrays.toString(subArray)));
    }

    public static int[] randInts(int amount, int min, int max) {
        return Util.R.ints(amount, min, max).toArray();
    }

    public static double[] randDoubles(int amount, double min, double max) {
        return Util.R.doubles(amount, min, max).toArray();
    }

    public static void shuffleInts(int[] ints) {
        for (int i = 0, rand, temp; i < ints.length; ++i) {
            rand = Util.R.nextInt(0, ints.length);
            temp = ints[i];
            ints[i] = ints[rand];
            ints[rand] = temp;
        }
    }

    public static double log2(double n) {
        return Math.log(n) / Math.log(2);
    }

}
