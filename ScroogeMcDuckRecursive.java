import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Scanner;

public class ScroogeMcDuckRecursive {
    public static void main(String[] args) {
        System.setIn(new ByteArrayInputStream("""
                4 3
                3 2 4
                2 0 3
                1 1 5
                2 2 5
                """.getBytes()));
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];
        int startRow = 0;
        int startCol = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
                if (matrix[i][j] == 0) {
                    startRow = i;
                    startCol = j;
                }
            }
        }

        System.out.println(collectCoins(matrix, startRow, startCol));
    }

    public static int collectCoins(int[][] matrix, int currentRow, int currentCol) {
        int nextStapCoinsMax = 0;
        int nextRow = -1;
        int nextCol = -1;

        if (currentRow + 1 < matrix.length && matrix[currentRow + 1][currentCol] >= nextStapCoinsMax) {
            nextStapCoinsMax = matrix[currentRow + 1][currentCol];
            nextRow = currentRow + 1;
            nextCol = currentCol;
        }

        if (currentRow - 1 >= 0 && matrix[currentRow - 1][currentCol] >= nextStapCoinsMax) {
            nextStapCoinsMax = matrix[currentRow - 1][currentCol];
            nextRow = currentRow - 1;
            nextCol = currentCol;
        }

        if (currentCol + 1 < matrix[0].length && matrix[currentRow][currentCol + 1] >= nextStapCoinsMax) {
            nextStapCoinsMax = matrix[currentRow][currentCol + 1];
            nextRow = currentRow;
            nextCol = currentCol + 1;
        }

        if (currentCol - 1 >= 0 && matrix[currentRow][currentCol - 1] >= nextStapCoinsMax) {
            nextStapCoinsMax = matrix[currentRow][currentCol - 1];
            nextRow = currentRow;
            nextCol = currentCol - 1;
        }

        if (nextStapCoinsMax == 0) {
            return 0;
        }

        matrix[nextRow][nextCol]--;
        return 1 + collectCoins(matrix, nextRow, nextCol);
    }
}