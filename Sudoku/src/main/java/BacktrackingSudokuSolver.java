import java.util.Random;

public class BacktrackingSudokuSolver {
    public void solve(final SudokuBoard board) {
        Random rand = new Random();
        int[] startTab = new int[81];  //wartości początkowe
        for (int i = 0; i < 81; i++) {
            //WSPOLRZEDNE SIATKI
            int row = i / 9;
            int column = i % 9;

            boolean nextStep = false;
            if (startTab[i] == 0) {
                //JESLI KROK DO PRZODU TO USTALAMY NOWA WARTOSC POCZATKOWA
                startTab[i] = rand.nextInt(9) + 1;
                board.set(row, column, startTab[i]);

                do {
                    if (checkValid(i, board)) {
                        nextStep = true;
                        break;
                    }
                    board.set(row, column, (board.get(row, column) % 9 + 1));
                } while (board.get(row, column) != startTab[i]);

            } else {
                //JESLI KROK DO TYLU TO WYKORZYSTUJEMY POPRZEDNIA WARTOSC
                board.set(row, column, (board.get(row, column) % 9 + 1));
                while (board.get(row, column) != startTab[i]) {
                    if (checkValid(i, board)) {
                        nextStep = true;
                        break;
                    }
                    board.set(row, column, (board.get(row, column) % 9 + 1));
                }
            }

            //JEZELI NIE PASUJE TO SIE COFAMY
            if (!nextStep) {
                startTab[i] = 0;
                board.set(row, column, 0);
                i -= 2;
            }
        }
    }

    private boolean checkValid(int x, final SudokuBoard board) {
        int row = x / 9;
        int column = x % 9;
        int number = board.get(row, column);

        //Sprawdzanie wiersza
        for (int i = 0; i < column; i++) {
            if (number == board.get(row, i)) {
                return false;
            }
        }

        //Sprawdzanie kolumny
        for (int i = 0; i < row; i++) {
            if (number == board.get(i, column)) {
                return false;
            }
        }

        //Sprawdzanie malego kwadratu
        int rowSquare = row / 3;
        int columnSquare = column / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int newRowSquare = i + rowSquare * 3;
                int newColumnSquare = j + columnSquare * 3;
                if (board.get(newRowSquare, newColumnSquare) == number
                        && (newRowSquare * 9 + newColumnSquare) < x) {
                    return false;
                }
            }
        }

        return true;
    }
}
