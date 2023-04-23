package Sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DriverClass {
    public static void main(String[] args) throws IOException {
//        generateBoard generateBoard = new generateBoard();
//        int[][] board = generateBoard.takeInput();
        File file = new File("/Users/shashankpal/Downloads/sudoku.rtf");
        Scanner sc = new Scanner(file);
        File outputFile = new File("/Users/shashankpal/Downloads/output.txt");
        FileWriter fileWriter = new FileWriter(outputFile);
        int[][] board = new int[9][9];
        Sudoku sudoku;
        int i = 0;
        int x = 1;
        while (sc.hasNext()) {
            String[] tokens = sc.nextLine().split("\n");
            for (String token : tokens) {
                if (token.length() >= 1) {
                    int compare1 = Character.compare(token.charAt(0), '0');
                    int compare2 = Character.compare(token.charAt(0), '9');
                    if (compare1 >= 0 && compare2 <= 0) {
                        for (int j = 0; j < 9; j++) {
                            board[i][j] = token.charAt(j) - '0';
                        }
                        i++;
                        if (i == 9) {
                            i = 0;
                            System.out.println("Solution " + x);

                            sudoku = new Sudoku(board);
                            sudoku.solveSudoku();
                            sudoku.printBoard();
                            sudoku.printToFile(fileWriter, x);
                            x++;
                        }
                    }
                }
            }
        }
        fileWriter.close();

    }
}
