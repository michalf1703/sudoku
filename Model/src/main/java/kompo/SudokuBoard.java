package kompo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.List;



public class SudokuBoard implements Serializable, Cloneable {

    private static final Logger logger = Logger.getLogger(SudokuBoard.class.getName());

    private SudokuSolver solver;

    private final SudokuField[][] board = new SudokuField[9][9];

    public SudokuBoard(SudokuSolver solverek1) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = new SudokuField();
            }
        }
        solver = solverek1;
    }


    public SudokuBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = new SudokuField();
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                this.set(i, k, board[i][k].getFieldValue());
            }
        }
    }


    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        try {
            board[x][y].setFieldValue(value);
        } catch (WrongFieldException e) {
            logger.error("Tried to input invalid value into board");

        }
    }

    public boolean isEditableField(int axisX, int axisY) {
        return board[axisX][axisY].isEmptyField();
    }

    public void setEditableField(int axisX, int axisY) {
        this.board[axisX][axisY].isEmptyField();
    }

    public boolean checkBoard() {
        //sprawdzanie wierszy
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int j2 = j + 1; j2 < 9; j2++) {
                    if (board[i][j].getFieldValue() == board[i][j2].getFieldValue()) {
                        return false;
                    }
                }
            }
        }

        //sprawdzanie kolumn
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                for (int i2 = i + 1; i2 < 9; i2++) {
                    if (board[i][j].getFieldValue() == board[i2][j].getFieldValue()) {
                        return false;
                    }
                }
            }
        }

        //sprawdzanie małych kwadratów
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //mały kwadrat (I, J)
                for (int checked = 0; checked < 9; checked++) {
                    for (int compared = checked + 1; compared < 9; compared++) {
                        if (board[i * 3 + (checked / 3)][j * 3 + (checked % 3)].getFieldValue()
                                == board[i * 3 + (compared / 3)][j * 3
                                + (compared % 3)].getFieldValue()) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public void solveGame() {
        try {
            solver.solve(this);
        } catch (WrongFieldException e) {
            logger.error("Tried to input invalid value into board");
        }
    }

    public SudokuRow getRow(int row) {
        SudokuField[] fields = new SudokuField[SudokuMethods.SIZE];
        System.arraycopy(board[row], 0, fields, 0, 9);

        return new SudokuRow(List.of(fields));
    }

    public SudokuColumn getColumn(int column) {
        SudokuField[] fields = new SudokuField[SudokuMethods.SIZE];
        System.arraycopy(board[column], 0, fields, 0, 9);

        return new SudokuColumn(List.of(fields));
    }

    public SudokuBox getBox(int rowIndex, int columnIndex) {
        SudokuField[] fields = new SudokuField[SudokuMethods.SIZE];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields[index++] = board[rowIndex * 3 + i][columnIndex * 3 + j];
            }
        }

        return new SudokuBox(List.of(fields));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder().append(board, that.board).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(board).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("board", board).toString();
    }

    @Override
    public SudokuBoard clone() {
        SudokuBoard bordzik = new SudokuBoard(solver);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    bordzik.set(i, j, this.get(i, j));
                } catch (WrongFieldException e) {
                    logger.info("Tried to input invalid value into board");
                }
            }
        }
            return bordzik;
    }


}




