package hr.mlinx.i;

public class GoldenRatio {

    public static double calculate(int precision) {
        int fib1 = 0, fib2 = 1;

        for (int i = 0, temp; i < precision; ++i) {
            temp = fib2;
            fib2 = fib1 + fib2;
            fib1 = temp;
            System.out.println(fib1 + ", " + fib2);
        }

        return 1d * fib2 / fib1;
    }

    public static int fibonnaci(int n) {
        if (n < 2)
            return n;

        return fibonnaci(n - 1) + fibonnaci(n - 2);
    }

}
