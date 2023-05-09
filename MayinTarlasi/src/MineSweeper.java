import java.util.Scanner;
import java.util.Random;

public class MineSweeper {
    private int[][] board;
    private boolean[][] mines;
    private int rows;
    private int cols;
    private int mineCount;
    private boolean gameOver;

    public MineSweeper(int rows, int cols, int mineCount) {
        this.rows = rows;
        this.cols = cols;
        this.mineCount = mineCount;

        // Matris oluştur
        board = new int[rows][cols];

        // Mayın matrisini oluştur
        mines = new boolean[rows][cols];

        // Mayınları rastgele yerleştir
        placeMines();

        // Noktaların değerlerini hesapla
        calculateValues();

        gameOver = false;
    }

    // Mayınları rastgele yerleştir
    private void placeMines() {
        int count = 0;
        Random rand = new Random();

        while (count < mineCount) {
            int row = rand.nextInt(rows);
            int col = rand.nextInt(cols);

            if (!mines[row][col]) {
                mines[row][col] = true;
                count++;
            }
        }
    }

    // Noktaların değerlerini hesapla
    private void calculateValues() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!mines[row][col]) {
                    int count = 0;

                    if (row > 0 && col > 0 && mines[row - 1][col - 1]) { // sol üst
                        count++;
                    }
                    if (row > 0 && mines[row - 1][col]) { // üst
                        count++;
                    }
                    if (row > 0 && col < cols - 1 && mines[row - 1][col + 1]) { // sağ üst
                        count++;
                    }
                    if (col > 0 && mines[row][col - 1]) { // sol
                        count++;
                    }
                    if (col < cols - 1 && mines[row][col + 1]) { // sağ
                        count++;
                    }
                    if (row < rows - 1 && col > 0 && mines[row + 1][col - 1]) { // sol alt
                        count++;
                    }
                    if (row < rows - 1 && mines[row + 1][col]) { // alt
                        count++;
                    }
                    if (row < rows - 1 && col < cols - 1 && mines[row + 1][col + 1]) { // sağ alt
                        count++;
                    }

                    board[row][col] = count;
                }
            }
        }
    }

    // Matrisi yazdır
    public void printBoard() {
        System.out.print("  ");
        for (int col = 0; col < cols; col++) {
            System.out.print(col + " ");
        }
        System.out.println();

        for (int row = 0; row < rows; row++) {
            System.out.print(row + " ");
            for (int col = 0; col < cols; col++) {
                if (gameOver && mines[row][col]) {
                    System.out.print("* ");
                } else if (board[row][col] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[row][col] + " ");
                }
            }
            System.out.println();
        }
    }
}
// Kullan
