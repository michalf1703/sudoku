public class SudokuBoard {


    private SudokuSolver solver;
    private final SudokuField[][] board = new SudokuField[9][9];


    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        this.board[x][y].setFieldValue(value);
    }


    private void generate() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = new SudokuField();
            }
        }
    }

    public SudokuBoard(SudokuSolver solverek1) {
        generate();
        solver = solverek1;
    }

    public SudokuBoard(int[][] sudokuBoard) {
        generate();
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                this.set(i,k,sudokuBoard[i][k]);
            }
        }
    }

    public boolean checkBoard(int[][] tab) {

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

    public SudokuRow getRow(int row) {
        SudokuField[] fields = new SudokuField[SudokuMethods.SIZE];
        for (int i = 0; i < 9; i++) {
            fields[i] = board[row][i];
        }

        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int column) {
        SudokuField[] fields = new SudokuField[SudokuMethods.SIZE];
        for (int i = 0; i < 9; i++) {
            fields[i] = board[i][column];
        }

        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int rowIndex, int columnIndex) {
        SudokuField[] fields = new SudokuField[SudokuMethods.SIZE];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields[index++] = board[rowIndex * 3 + i][columnIndex * 3 + j];
            }
        }

        return new SudokuBox(fields);
    }
}

