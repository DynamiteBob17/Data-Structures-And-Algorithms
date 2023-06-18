package hr.mlinx.i;

import java.util.Arrays;

public class Matrix {

    // if c initialized to 0 then returns a * b
    public static int[][] multiplyIteratively(int[][] a, int[][] b, int[][] resultMatrix) {
        if (areValidInputs(a, b, resultMatrix)) {
            int[][] c = Arrays.stream(resultMatrix).map(int[]::clone).toArray(int[][]::new);

            for (int i = 0, n = a.length; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    for (int k = 0; k < n; ++k) {
                        c[i][j] = c[i][j] + a[i][k] * b[k][j];
                    }
                }
            }

            return c;
        } else {
            throw new IllegalArgumentException("All matrices have to be of equal n x n dimensions.");
        }
    }

    public static boolean isSquareMatrix1(int[][] matrix) {
        return matrix.length == Arrays.stream(matrix).filter(sub -> sub.length == matrix.length).count();
    }

    public static boolean isSquareMatrix2(int[][] matrix) {
        double sqrt = Math.sqrt(Arrays.stream(matrix).flatMapToInt(Arrays::stream).count());
        return Double.compare(sqrt, Math.round(sqrt)) == 0;
    }

    public static boolean areValidInputs(int[][] a, int[][] b, int[][] resultMatrix) {
        return isSquareMatrix1(a) && isSquareMatrix1(b) && isSquareMatrix1(resultMatrix)
                && a.length == b.length && b.length == resultMatrix.length;
    }

}
