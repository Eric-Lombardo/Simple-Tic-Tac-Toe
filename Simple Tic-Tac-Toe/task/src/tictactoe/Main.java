package tictactoe;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[] emptyBoard = "_________".toCharArray();
        char[][] board = getBoard(emptyBoard);
        boolean isGameOver = false;
        boolean isPlayer1Playing = true;

        displayBoard(board);

        while (!isGameOver) {
            board = verifyMove(scanner, board, isPlayer1Playing);
            isGameOver = displayBoard(board);
            isPlayer1Playing = !isPlayer1Playing;
        }
    }


    private static char[][] verifyMove(Scanner scanner, char[][] board, boolean isPlayer1Playing) {
        int nextMoveRow = scanner.nextInt();
        int nextMoveColumn = scanner.nextInt();
        boolean isValid = false;

        while (!isValid) {
            if (nextMoveRow < 1 || nextMoveRow > 3 || nextMoveColumn < 1 || nextMoveColumn > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                nextMoveRow = scanner.nextInt();
                nextMoveColumn = scanner.nextInt();
            } else if (board[nextMoveRow - 1][nextMoveColumn - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                nextMoveRow = scanner.nextInt();
                nextMoveColumn = scanner.nextInt();
            } else if (!isInteger(nextMoveRow) || !isInteger(nextMoveColumn)) {
                System.out.println("You should enter numbers!");
                nextMoveRow = scanner.nextInt();
                nextMoveColumn = scanner.nextInt();
            } else {
                isValid = true;
                board[nextMoveRow - 1][nextMoveColumn - 1] = isPlayer1Playing ? 'X' : 'O';
            }
        }

        return board;
    }

    private static boolean isInteger(double num) {
        return Math.floor(num) == num;
    }

    private static char[][] getBoard(char[] charArr) {
        return new char[][]{
                {charArr[0], charArr[1], charArr[2]},
                {charArr[3], charArr[4], charArr[5]},
                {charArr[6], charArr[7], charArr[8]}
        };
    }

    private static boolean displayBoard(char[][] board) {
        int countX = 0;
        int countO = 0;
        int countEmptyCell = 0;
        boolean diagForwardX = board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] == 'X';
        boolean diagBackX = board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[1][1] == 'X';
        boolean winX = diagBackX || diagForwardX;
        boolean diagForwardO = board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] == 'O';
        boolean diagBackO = board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[1][1] == 'O';
        boolean winO = diagBackO || diagForwardO;

        System.out.println("---------");
        for (char[] chars : board) {
            System.out.print("| ");
            for (char aChar : chars) {
                System.out.print(aChar + " ");
                if (aChar == '_') {
                    countEmptyCell++;
                }
            }
            System.out.println("|");
        }
        System.out.println("---------");

        for (char[] row : board) {
            for (char item : row) {
                if (item == 'X') {
                    countX++;
                } else if (item == 'O') {
                    countO++;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 'X') {
                winX = true;
            } else if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 'O') {
                winO = true;
            }
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 'X') {
                winX = true;
            } else if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 'O') {
                winO = true;
            }
        }

        if (countEmptyCell == 0 && (!winO && !winX)) {
            System.out.println("Draw");
            return true;
        }
        if (!winO && winX) {
            System.out.println("X wins");
            return true;
        }
        if (winO && !winX) {
            System.out.println("O wins");
            return true;
        }
        if (Math.abs(countO - countX) >= 2 || (winO && winX)) {
            System.out.println("Impossible");
            return true;
        }

        System.out.println("Game not finished");
        return false;
    }
}
