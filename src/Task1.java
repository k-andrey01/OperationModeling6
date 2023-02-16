public class Task1 {
    private static int lineCoord;
    private static int columnCoord;

    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 6, 5},
                {5, 6, 3, 9},
                {7, 3, 5, 4}};

//        int[][] matrix = {{-2, 0, 1},
//                          {3, 4, 5},
//                          {2, 7, -2}};

        int[] minsByLines = getMins(matrix);
        int[] maxsByColumns = getMaxs(matrix);

        int alpha = getMaxInArray(minsByLines);
        int beta = getMinInArray(maxsByColumns);

        System.out.println("Нижняя цена игры: " + alpha + "\nВерхняя цена игры:  " + beta + "\n");

        if (alpha == beta) {
            System.out.println("Координаты седловой точки: [" + lineCoord + ", " + columnCoord + "]");
        } else {
            System.out.println("Седловая точка отсутствует!");
        }
    }

    private static int[] getMins(int[][] matrix) {
        int[] result = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = getMinInArray(matrix[i]);
        }
        return result;
    }

    private static int[] getMaxs(int[][] matrix) {
        int[] result = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            result[i] = getMaxInArray(getColumn(matrix, i));
        }
        return result;
    }

    private static int[] getColumn(int[][] matrix, int index) {
        int[] result = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = matrix[i][index];
        }
        return result;
    }

    private static int getMaxInArray(int[] array) {
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > result) {
                result = array[i];
                lineCoord = i;
            }
        }
        return result;
    }

    private static int getMinInArray(int[] array) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < result) {
                result = array[i];
                columnCoord = i;
            }
        }
        return result;
    }

}
