package Sudoku;

import java.io.FileWriter;
import java.io.IOException;

public class Sudoku {
    int[][] rows;   // used to keep track of numbers filled in rows
    int[][] cols;   // used to keep track of numbers filled in columns
    int[][] subGrid;  // used to keep track of numbers filled in subgrid
    boolean solved; //to check if the board is solved
    int[][] board;

    public Sudoku(int[][] board) {
        this.board = board;
        rows = new int[10][10];
        cols = new int[10][10];
        subGrid = new int[10][10];
        solved = false;
    }

    void solveSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0) {
                    place(board[i][j], i, j, board);
                }
            }
        }
        backtrack(0, 0, board);
    }

    void place(int d, int row, int col, int[][] board) {
        int idx = (row / 3) * 3 + (col / 3);    //0 to 9 numbers are marked for each subgrid this will make searching faster in a subgrid
        rows[row][d]++;
        cols[col][d]++;
        subGrid[idx][d]++;
        board[row][col] = d;
    }

    void backtrack(int row, int col, int[][] board) {
        if (board[row][col] == 0) {
            for (int d = 1; d <= 9; d++) {
                if (couldPlace(d, row, col)) {  //if the number d can be placed, then it will be placed and then other cells will be checked
                    place(d, row, col, board);
                    placeNext(row, col, board);
                    if (!solved) {  //if solution is not reached by placing the number d then it will be removed
                        remove(d, row, col, board);
                    }
                }
            }
        } else {
            placeNext(row, col, board);
        }
    }

    boolean couldPlace(int d, int row, int col) {
        int idx = (row / 3) * 3 + (col / 3);
        return rows[row][d] + cols[col][d] + subGrid[idx][d] == 0;
    }

    void remove(int d, int row, int col, int[][] board) {
        int idx = (row / 3) * 3 + (col / 3);
        rows[row][d]--;
        cols[col][d]--;
        subGrid[idx][d]--;
        board[row][col] = 0;
    }

    void placeNext(int row, int col, int[][] board) {
        if (row == 8 && col == 8) { // if the last row and column is reached then sudoku is solved
            solved = true;
            return;
        }
        if (col == 8) { //else backtrack
            backtrack(row + 1, 0, board);
        } else {
            backtrack(row, col + 1, board);
        }
    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(board[i][j]);
            System.out.println();
        }
    }

    void printToFile(FileWriter fileWriter, int solutionNumber) throws IOException {
        fileWriter.write("Solution " + solutionNumber + "\n");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fileWriter.write((char) board[i][j] + '0');
            }
            fileWriter.write("\n");
        }
        fileWriter.write("\n");
    }
}
