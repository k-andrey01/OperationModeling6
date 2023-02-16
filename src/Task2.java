import java.util.Arrays;

public class Task2 {
    private static double[] p;
    private static double[] q;

    public static void main(String[] args) {
        int[][] matrix = {{2, 6, 4, 2},
                {7, 2, 3, 1},
                {5, 3, 7, 5}};
        System.out.println("Исходная матрица:");
        printMatrix(matrix);

        p = new double[matrix.length];
        q = new double[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            p[i] = -1;
        }
        for (int i = 0; i < matrix[0].length; i++) {
            q[i] = -1;
        }

        matrix = clearMatrix(matrix);
        System.out.println("Матрица, сокращенная до 2х2:");
        printMatrix(matrix);

        boolean flag = false;
        for (int i = 0; i < p.length; i++) {
            if (p[i] == -1 && flag == false) {
                p[i] = (double) (matrix[1][1] - matrix[1][0]) / (matrix[0][0] + matrix[1][1] - matrix[1][0] - matrix[0][1]);
                flag = true;
            } else if (p[i] == -1 && flag == true)
                p[i] = (double) (matrix[0][0] - matrix[0][1]) / (matrix[0][0] + matrix[1][1] - matrix[1][0] - matrix[0][1]);
        }

        flag = false;
        for (int i = 0; i < q.length; i++) {
            if (q[i] == -1 && flag == false) {
                q[i] = (double) (matrix[1][1] - matrix[0][1]) / (matrix[0][0] + matrix[1][1] - matrix[1][0] - matrix[0][1]);
                flag = true;
            } else if (q[i] == -1 && flag == true)
                q[i] = (double) (matrix[0][0] - matrix[1][0]) / (matrix[0][0] + matrix[1][1] - matrix[1][0] - matrix[0][1]);
        }

        System.out.println("Оптимальная смешанная стратегия 1-го игрока: " + Arrays.toString(p));
        System.out.println("Оптимальная смешанная стратегия 2-го игрока: " + Arrays.toString(q));

        double y = (double) (matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1]) / (matrix[0][0] + matrix[1][1] - matrix[1][0] - matrix[0][1]);
        System.out.println("Цена игры: " + y);
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int[][] clearMatrix(int[][] matrix) {
        matrix = deleteColumns(matrix);
        matrix = deleteLines(matrix);
        return matrix;
    }

    private static int[][] deleteColumns(int[][] matrix) {
        int coef = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i != j) {
                    int[] firstColumn = getColumn(matrix, i - coef);
                    int[] secondColumn = getColumn(matrix, j);
                    if (isFirstLessThenSecond(firstColumn, secondColumn)) {
                        matrix = delColFromMatrix(matrix, j);
                        System.out.println("Сокращенная матрица:");
                        printMatrix(matrix);
                        q[j + coef] = 0;
                        coef += 1;
                    }
                }
            }
        }
        return matrix;
    }

    private static int[][] delColFromMatrix(int[][] matrix, int index) {
        int[][] newMatrix = new int[matrix.length][matrix[0].length - 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j < index)
                    newMatrix[i][j] = matrix[i][j];
                else if (j > index)
                    newMatrix[i][j - 1] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    private static int[][] delLineFromMatrix(int[][] matrix, int index) {
        int[][] newMatrix = new int[matrix.length - 1][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i < index)
                    newMatrix[i][j] = matrix[i][j];
                else if (i > index)
                    newMatrix[i - 1][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    private static boolean isFirstLessThenSecond(int[] firstArr, int[] secondArr) {
        for (int i = 0; i < firstArr.length; i++) {
            if (firstArr[i] > secondArr[i])
                return false;
        }
        return true;
    }

    private static boolean isFirstMoreThenSecond(int[] firstArr, int[] secondArr) {
        for (int i = 0; i < firstArr.length; i++) {
            if (firstArr[i] < secondArr[i])
                return false;
        }
        return true;
    }

    private static int[][] deleteLines(int[][] matrix) {
        int coef = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != j) {
                    int[] firstLine = matrix[i - coef];
                    int[] secondLine = matrix[j];
                    if (isFirstMoreThenSecond(firstLine, secondLine)) {
                        if (matrix.length > 2) {
                            matrix = delLineFromMatrix(matrix, j);
                            System.out.println("Сокращенная матрица:");
                            printMatrix(matrix);
                            p[j] = 0;
                        }
                    }
                }
            }
        }
        return matrix;
    }

    private static int[] getColumn(int[][] matrix, int index) {
        int[] result = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = matrix[i][index];
        }
        return result;
    }
}
