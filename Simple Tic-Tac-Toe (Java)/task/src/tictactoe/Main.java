package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int n = 3;
        char[][] matrix = new char[n][n];
        int index = 0;

        // Заполняем матрицу
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = str.charAt(index++);
            }
        }

        // Печатаем игровое поле
        System.out.println("---------");
        for (char[] row : matrix) {
            System.out.print("| ");
            for (char ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

        // Анализ игрового состояния
        analyzeGame(matrix);
    }

    public static void analyzeGame(char[][] matrix) {
        int n = matrix.length;

        // Подсчитываем количество X и O
        int countX = 0, countO = 0, emptyCells = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 'X') countX++;
                else if (matrix[i][j] == 'O') countO++;
                else if (matrix[i][j] == '_') emptyCells++;
            }
        }

        // Проверяем победителей
        boolean xWins = isWinner('X', matrix);
        boolean oWins = isWinner('O', matrix);

        // Проверка состояния
        if (xWins && oWins || Math.abs(countX - countO) > 1) {
            System.out.println("Impossible");
        } else if (xWins) {
            System.out.println("X wins");
        } else if (oWins) {
            System.out.println("O wins");
        } else if (emptyCells > 0) {
            System.out.println("Game not finished");
        } else {
            System.out.println("Draw");
        }
    }

    public static boolean isWinner(char player, char[][] matrix) {
        int n = matrix.length;

        // Проверка строк
        for (int i = 0; i < n; i++) {
            boolean rowWin = true;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != player) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) return true;
        }

        // Проверка столбцов
        for (int j = 0; j < n; j++) {
            boolean colWin = true;
            for (int i = 0; i < n; i++) {
                if (matrix[i][j] != player) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) return true;
        }

        // Проверка главной диагонали
        boolean mainDiagonalWin = true;
        for (int i = 0; i < n; i++) {
            if (matrix[i][i] != player) {
                mainDiagonalWin = false;
                break;
            }
        }
        if (mainDiagonalWin) return true;

        // Проверка обратной диагонали
        boolean antiDiagonalWin = true;
        for (int i = 0; i < n; i++) {
            if (matrix[i][n - 1 - i] != player) {
                antiDiagonalWin = false;
                break;
            }
        }
        if (antiDiagonalWin) return true;

        // Если ничего не найдено, возвращаем false
        return false;
    }
}
