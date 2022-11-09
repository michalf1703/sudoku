import java.util.Arrays;
import java.util.List;

public class SudokuBoard {


    private SudokuSolver solver;
    //private final SudokuField[][] board = new SudokuField[9][9];

    private List<List<SudokuField>>board;

    public SudokuBoard(SudokuSolver solverek1) {
        board = Arrays.asList(new List[9]);
     //   induce();
        for (int i = 0; i < 9; i++)
        {
            board.set(i,Arrays.asList(new SudokuField[9]));
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board.get(i).set(j,new SudokuField());
            }
        }
        solver = solverek1;
    }
   /* private void induce() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board.get(i).set(j,new SudokuField());
            }
        }
    }*/

  /*  public SudokuBoard(int[][] sudokuBoard) {
        induce();
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                this.set(i,k,sudokuBoard[i][k]);
            }
        }
    }
*/
    public int get(int x, int y) {
        return board.get(x).get(y).getFieldValue();
    }

    public void set(int x, int y, int value) {

        this.board.get(x).get(y).setFieldValue(value);
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
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i,board.get(row).get(i));
        }

        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int column) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i,board.get(i).get(column));
        }

        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int row, int column) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields.set(index++,board.get(row * 3 + i).get(column * 3 + j));
            }
        }

        return new SudokuBox(fields);
    }
}

