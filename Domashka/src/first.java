//import javax.swing.JFrame;
//public class first {
//    public static void main(String[] args) {
//    Game game = new Game();
//    JFrame window = new JFrame("2048");
//    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    window.setResizable(false);
//    window.add(game);
//    window.setLocationRelativeTo(null);
//    window.setVisible(true);
//    game.start();
//    }
//}
import java.util.Random;
import java.util.Scanner;

public class first{
    private static final int SIZE = 4; // Размер поля 4x4
    private static final int WIN_VALUE = 2048; // Значение для победы
    private static int[][] board = new int[SIZE][SIZE];
    private static Random rand = new Random();

    public static void main(String[] args) {
        initializeBoard();
        while (true) {
            printBoard();
            if (checkWin()) {
                System.out.println("Поздравляем! Вы выиграли!");
                break;
            }
            if (isBoardFull() && !canMove()) {
                System.out.println("Игра окончена. Вы проиграли!");
                break;
            }
            makeMove();
            addRandomTile();
        }
    }

    private static void initializeBoard() {
        addRandomTile();
        addRandomTile();
    }

    private static void addRandomTile() {
        int emptyCount = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) emptyCount++;
            }
        }
        int randIndex = rand.nextInt(emptyCount);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    if (randIndex == 0) {
                        board[i][j] = rand.nextBoolean() ? 2 : 4;
                        return;
                    }
                    randIndex--;
                }
            }
        }
    }

    private static boolean checkWin() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == WIN_VALUE) return true;
            }
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }

    private static boolean canMove() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) return true;
                if (i < SIZE - 1 && board[i][j] == board[i + 1][j]) return true;
                if (j < SIZE - 1 && board[i][j] == board[i][j + 1]) return true;
            }
        }
        return false;
    }

    private static void makeMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите направление (w/a/s/d): ");
        String input = scanner.nextLine();
        switch (input) {
            case "w":
                moveUp();
                break;
            case "a":
                moveLeft();
                break;
            case "s":
                moveDown();
                break;
            case "d":
                moveRight();
                break;
            default:
                System.out.println("Неверный ввод!");
        }
    }

    private static void moveUp() {
        for (int j = 0; j < SIZE; j++) {
            for (int i = 1; i < SIZE; i++) {
                if (board[i][j] != 0) {
                    int targetRow = i;
                    while (targetRow > 0 && board[targetRow - 1][j] == 0) {
                        board[targetRow - 1][j] = board[targetRow][j];
                        board[targetRow][j] = 0;
                        targetRow--;
                    }
                    if (targetRow > 0 && board[targetRow - 1][j] == board[targetRow][j]) {
                        board[targetRow - 1][j] *= 2;
                        board[targetRow][j] = 0;
                    }
                }
            }
        }
    }

    private static void moveLeft() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 1; j < SIZE; j++) {
                if (board[i][j] != 0) {
                    int targetCol = j;
                    while (targetCol > 0 && board[i][targetCol - 1] == 0) {
                        board[i][targetCol - 1] = board[i][targetCol];
                        board[i][targetCol] = 0;
                        targetCol--;
                    }
                    if (targetCol > 0 && board[i][targetCol - 1] == board[i][targetCol]) {
                        board[i][targetCol - 1] *= 2;
                        board[i][targetCol] = 0;
                    }
                }
            }
        }
    }

    private static void moveDown() {
        for (int j = 0; j < SIZE; j++) {
            for (int i = SIZE - 2; i >= 0; i--) {
                if (board[i][j] != 0) {
                    int targetRow = i;
                    while (targetRow < SIZE - 1 && board[targetRow + 1][j] == 0) {
                        board[targetRow + 1][j] = board[targetRow][j];
                        board[targetRow][j] = 0;
                        targetRow++;
                    }
                    if (targetRow < SIZE - 1 && board[targetRow + 1][j] == board[targetRow][j]) {
                        board[targetRow + 1][j] *= 2;
                        board[targetRow][j] = 0;
                    }
                }
            }
        }
    }

    private static void moveRight() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = SIZE - 2; j >= 0; j--) {
                if (board[i][j] != 0) {
                    int targetCol = j;
                    while (targetCol < SIZE - 1 && board[i][targetCol + 1] == 0) {
                        board[i][targetCol + 1] = board[i][targetCol];
                        board[i][targetCol] = 0;
                        targetCol++;
                    }
                    if (targetCol < SIZE - 1 && board[i][targetCol + 1] == board[i][targetCol]) {
                        board[i][targetCol + 1] *= 2;
                        board[i][targetCol] = 0;
                    }
                }
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] == 0 ? "." : board[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
