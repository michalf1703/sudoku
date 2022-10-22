import java.util.Arrays;
import  java.util.Random;

public class SudokuBoard {

    private int[][] board = new int[9][9];

    private boolean checkValid(int x) {
        //WSPOLRZEDNE KOMORKI SIATKI
        int row = x / 9;
        int column = x % 9;
        int number = board[row][column];
        //SPRAWDZANIE RZEDU
        for (int i = 0; i < column; i++) {
            if (number == board[row][i]) {
                return false;
            }
        }
        //SPRAWDZANIE KOLUMNY
        for (int i = 0; i < row; i++) {
            if (number == board[i][column]) {
                return false;
            }
        }
        //SPRAWDZANIE MALYCH KWADRATOW
        int rowSquare = row / 3;
        int columnSquare = column / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int newRowSquare = i + rowSquare * 3;
                int newColumnSquare = j + columnSquare * 3;
                if (number == board[newRowSquare][newColumnSquare]
                        && (newRowSquare * 9 + newColumnSquare) < x) {
                    return false;
                }
            }

        }
        return true;
    }

    public void fillBoard() {
        Random rand = new Random();
        int[] startTab = new int[81];
        for (int i = 0; i < 81; i++) {
            //ZNOWU WSPOLRZEDNE SIATKI
            int row = i / 9;
            int column = i % 9;

            boolean nextStep = false;
            if (startTab[i] == 0) {
                //jeżeli krok do przodu to ustawiamy nową wartość początkową
                startTab[i] = rand.nextInt(9) + 1;
                board[row][column] = startTab[i];
                do {
                    if (checkValid(i)) {
                        nextStep = true;
                        break;
                    }
                    board[row][column] = board[row][column] % 9 + 1;
                } while (board[row][column] != startTab[i]);
            } else {
                //jeżeli krok do tyłu to wykorzystujemy poprzednią wartość początkową
                board[row][column] = board[row][column] % 9 + 1;
                while (board[row][column] != startTab[i]) {
                    if (checkValid(i)) {
                        nextStep = true;
                        break;
                    }
                    board[row][column] = board[row][column] % 9 + 1;
                }
            }

            //jeżeli żaden nie pasuje to się cofamy
            if (!nextStep) {
                startTab[i] = 0;
                board[row][column] = 0;
                i -= 2;
            }
        }
    }

    public void print() {
        System.out.println(" ____________________");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 0) {
                    System.out.print("|");
                }

                System.out.print(board[i][j] + " ");
                if (j == 2 || j == 5) {
                    System.out.print("|");
                }
                if (j == 8) {
                    System.out.print("|");
                }

                if (j == 8 && i == 2) {
                    System.out.print("\n");
                    System.out.print(" --------------------");
                }

                if (j == 8 && i == 5) {
                    System.out.print("\n");
                    System.out.print(" --------------------");
                }


            }
            System.out.println();
        }
        System.out.println(" --------------------");
    }

    //metoda napisana do testow
    public int[][] getCopyBoard() {
        int[][] getBoard = new int[9][];
        for (int i = 0; i < 9; i++) {
            getBoard[i] = Arrays.copyOf(board[i],9);
        }
        return getBoard;
    }
}

