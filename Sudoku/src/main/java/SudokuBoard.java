public class SudokuBoard {


    private SudokuSolver solver;
    private int[][] board = new int[9][9];

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        this.board[x][y] = value;
    }

    public SudokuBoard(int[][] board) {
        this.board = board;
    }

    public SudokuBoard(SudokuSolver solverek1) {
        solver = solverek1;
    }


    public boolean checkValid(int[][] tab) {

        //SPRAWDZANIE RZEDU
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = j + 1; k < 9; k++) {
                    if (tab[i][j] == tab[i][k]) {
                        return false;
                    }
                }
            }
        }
        //SPRAWDZANIE KOLUMNY
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                for (int k = i + 1; k < 9; k++) {
                    if (tab[i][j] == tab[k][j]) {
                        return false;
                    }
                }
            }
        }
        //SPRAWDZANIE MALYCH KWADRATOW
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //mały kwadrat (i, j)
                for (int checked = 0; checked < 9; checked++) {
                    for (int compared = checked + 1; compared < 9; compared++) {
                        if (tab[i * 3 + (checked / 3)][j * 3 + (checked % 3)]
                                == tab[i * 3 + (compared / 3)][j * 3 + (compared % 3)]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(final Object object) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (((SudokuBoard) object).get(i, j) != get(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                result += get(i, j) + " ";
            }
            result += "\n";
        }
        return result;
    }

    public void solveGame() {
        solver.solve(this);
    }


}

