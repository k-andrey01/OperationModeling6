import java.util.Arrays;

public class Task3 {
    public static void main(String[] args) {
        int[][] matrix = {{2, 6, 4, 2},
                {7, 2, 3, 1},
                {5, 3, 7, 5}};
        int iterations = 100;
        double[] p = {1, 0, 0};
        double[] q = {1, 0, 0, 0};
        System.out.println("Исходная матрица:");
        Task2.printMatrix(matrix);

        methodBraunRobinson(matrix, iterations, p, q);
    }

    private static void methodBraunRobinson(int[][] matrix, int iterations, double[] p, double[] q) {
        double alpha = 0;
        double beta = 0;
        for (int i = 1; i < iterations; i++) {
            double[] dataForNextI = getNextI(p, q, matrix);
            int nextI = (int) dataForNextI[0];
            alpha = dataForNextI[1];

            double[] dataForNextJ = getNextJ(p, q, matrix);
            int nextJ = (int) dataForNextJ[0];
            beta = dataForNextJ[1];

            for (int k = 0; k < p.length; k++) {
                if (k != nextI) {
                    p[k] = (i - 1) * p[k] / i;
                } else {
                    p[k] = ((i - 1) * p[k] + 1) / i;
                }
            }

            for (int k = 0; k < q.length; k++) {
                if (k != nextJ) {
                    q[k] = (i - 1) * q[k] / i;
                } else {
                    q[k] = ((i - 1) * q[k] + 1) / i;
                }
            }

            System.out.println("y[" + i + "] = " + ((alpha + beta) / 2));
        }
        System.out.println("\np: " + Arrays.toString(p));
        System.out.println("q: " + Arrays.toString(q));
        System.out.println("y: " + ((alpha + beta) / 2));
    }

    private static double[] getNextI(double[] p, double[] q, int[][] matrix) {
        int maxIndex = 0;
        double maxSum = Double.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            double sum = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                sum += matrix[i][j] * q[j];
            }
            if (sum > maxSum) {
                maxSum = sum;
                maxIndex = i;
            }
        }
        double[] result = {maxIndex, maxSum};
        return (result);
    }

    private static double[] getNextJ(double[] p, double[] q, int[][] matrix) {
        int minIndex = 0;
        double minSum = Double.MAX_VALUE;
        for (int i = 0; i < matrix[0].length; i++) {
            double sum = 0;
            for (int j = 0; j < matrix.length; j++) {
                sum += matrix[j][i] * p[j];
            }
            if (sum < minSum) {
                minSum = sum;
                minIndex = i;
            }
        }
        double[] result = {minIndex, minSum};
        return (result);
    }

}
