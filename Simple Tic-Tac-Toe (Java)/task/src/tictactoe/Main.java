package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] matrix = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };

        char currentPlayer = 'X';

        // Печатаем игровое поле
        printGameField(matrix);

        // Анализ игрового состояния
        while(!analyzeGame(matrix)) {

            // Ход игрока
            userMove(scanner, matrix, currentPlayer);

            // Переключение игрока
            currentPlayer = switchPlayer(currentPlayer);

            // Печатаем игровое поле
            printGameField(matrix);

        }
    }

    public static void userMove(Scanner scanner, char[][] matrix, char currentPlayer) {
        int x = 0, y = 0;

        while (true) {
            String usrStep = scanner.nextLine();

            if (isValidInput(usrStep)) {
                String[] arr = usrStep.split(" ");
                x = Integer.parseInt(arr[0]);
                y = Integer.parseInt(arr[1]);

                // Проверка границ
                if (x > 0 && y > 0 && x <= 3 && y <= 3) {
                    // Проверка, свободна ли ячейка
                    if (checkCell(matrix, x - 1, y - 1)) { // Преобразование к индексу массива (0-based)
                        matrix[x - 1][y - 1] = currentPlayer;
                        break; // Выход из цикла, если ход выполнен успешно
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }
    }

    public static char switchPlayer(char currentPlayer) {
        return (currentPlayer == 'X') ? 'O' : 'X';

    }

    public static boolean isValidInput(String input) {
        return input.matches("\\d+ \\d+");
    }

    public static boolean checkCell(char[][] matrix, int x, int y) {
        return matrix[x][y] == '_';
    }

    public static void printGameField(char[][] matrix) {
        System.out.println("---------");
        for (char[] row : matrix) {
            System.out.print("| ");
            for (char ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static boolean analyzeGame(char[][] matrix) {
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
            return true;
        } else if (xWins) {
            System.out.println("X wins");
            return true;
        } else if (oWins) {
            System.out.println("O wins");
            return true;
        } else if (emptyCells > 0) {
            // System.out.println("Game not finished");
            return false;
        } else {
            System.out.println("Draw");
            return true;
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
