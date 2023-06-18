package hr.mlinx.util;

import java.util.function.Function;

public class Runner {


    public static <T> String run(Function<T, ?> method, T arg, int count) {
        if (count <= 0)
            throw new IllegalArgumentException("Count must be a positive integer but got " + count + ".");

        long before = System.nanoTime();
        for (int i = 0; i < count; ++i)
            method.apply(arg);
        double durationMilli = (System.nanoTime() - before) / 1_000_000d;
        double averageMilli = durationMilli / count;

        return String.format(
                "%d call(s) -> Total time elapsed = %.6f ms, average per call = %.6f ms",
                count, durationMilli, averageMilli
        );
    }

    public static <T> void run(String methodName, Function<T, ?> method, T arg, int[] counts) {
        StringBuilder sb = new StringBuilder(methodName).append(":\n");

        for (int count : counts)
            sb.append("\t").append(run(method, arg, count)).append("\n");

        System.out.print(sb);
    }

}
