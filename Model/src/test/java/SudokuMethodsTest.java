package kompo;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuMethodsTest {

    @Test
    public void verifyValidTest() throws WrongFieldException {
        SudokuMethods sudokuRow = new SudokuColumn(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
       assertTrue(sudokuRow.verify());
    }

    @Test
    public void verifyInvalidTest() throws WrongFieldException {
        SudokuMethods sudokuRow = new SudokuMethods(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(3),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9))) {
        };
        assertFalse(sudokuRow.verify());
    }

    @Test
    public void getFieldsTest() throws WrongFieldException {
        SudokuMethods sudokuRow = new SudokuMethods(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9))) {
        };

        List<Integer> sudokuRowList = sudokuRow.getFields();

        List<Integer> valueList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            valueList.add(i + 1);
        }

        assertEquals(sudokuRowList,valueList);
    }

    @Test
    public void toStringTest() throws WrongFieldException {
        SudokuMethods sudokuRow = new SudokuMethods(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9))) {
        };
        assertNotNull(sudokuRow.toString());
    }

    @Test
    public void equalsTest() throws WrongFieldException {
        SudokuMethods sudokuRow = new SudokuMethods(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9))) {
        };
        SudokuMethods sudokuRowSecond = new SudokuMethods(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9))) {
        };

        assertTrue(sudokuRowSecond.equals(sudokuRowSecond));
    }

    @Test
    public void hashCodeTest() throws WrongFieldException {
        SudokuMethods sudokuRow = new SudokuMethods(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9))) {
        };
        SudokuMethods sudokuRowSecond = new SudokuMethods(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9))) {
        };

        assertEquals(sudokuRow.hashCode(), sudokuRowSecond.hashCode());
    }

}
