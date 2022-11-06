public class SudokuBoard {


    private SudokuSolver solver;
    private SudokuField[][] board = new SudokuField[9][9];
   // private int[][] board1 = new int[9][9];

    public SudokuBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = new SudokuField();
            }
        }
    }
    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        this.board[x][y].setFieldValue(value);
    }

  //  public SudokuBoard(int[][] board) {
    //    this.board = board;
  //  }

    public SudokuBoard(SudokuSolver solverek1) {
        solver = solverek1;
    }



    public boolean checkValid(int[][] tab) {

        //SPRAWDZANIE RZEDU
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = j + 1; k < 9; k++) {
                    if (board[i][j].getFieldValue() == board[i][k].getFieldValue()) {
                        return false;
                    }
                }
            }
        }
        //SPRAWDZANIE KOLUMNY
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                for (int k = i + 1; k < 9; k++) {
                    if (board[i][j].getFieldValue() == board[k][j].getFieldValue()) {
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
                        if (board[i * 3 + (checked / 3)][i * 3 + (checked % 3)].getFieldValue() ==
                                board[i * 3 + (compared / 3)][j * 3 + (compared % 3)].getFieldValue()) {
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

