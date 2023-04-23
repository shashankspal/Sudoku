package Sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class generateBoard {
    int[][] takeInput() throws FileNotFoundException {
        File file = new File("/Users/shashankpal/Downloads/sudoku.rtf");
        Scanner sc = new Scanner(file);

        int[][] board = new int[9][9];
        while (sc.hasNext()) {
            String[] tokens = sc.nextLine().split("\n");
            for (String token : tokens) {
                if (token.length() >= 1) {
                    int compare1 = Character.compare(token.charAt(0), '0');
                    int compare2 = Character.compare(token.charAt(0), '9');
                    if (compare1 >= 0 && compare2 <= 0)
                    {

                    }
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            int row = sc.nextInt();
            int col = 8;
            while (row > 0) {
                board[i][col] = row % 10;
                col--;
                row /= 10;
            }
        }
        return board;
    }
}
