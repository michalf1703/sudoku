package kompo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
public class SudokuFieldTest {
    private SudokuField sudokuField;
    private SudokuField sudokuFieldSecond;

    private SudokuSolver solver = new BacktrackingSudokuSolver();

    @BeforeEach
    public void setUp() {
        sudokuField = new SudokuField();
    }

    @Test
    public void getFieldValueTest() {
        assertEquals(sudokuField.getFieldValue(), 0);
    }

    @Test
    public void setFieldValueTest() {
        sudokuField.setFieldValue(5);
        assertEquals(sudokuField.getFieldValue(), 5);
    }

   @Test
    public void setWrongOrGoodFieldValueTest() {
      assertTrue(sudokuField.setFieldValue(5));
      assertTrue(sudokuField.setFieldValue(9));
       assertThrows(WrongFieldException.class, () -> {sudokuField.setFieldValue(25);});

       assertThrows(WrongFieldException.class, () -> {sudokuField.setFieldValue(-8);});

    }
    @Test
    public void toStringTest() {
        assertNotNull(sudokuField.toString());
    }

    @Test
    public void equalsTest() {
        sudokuFieldSecond = new SudokuField();
        assertTrue(sudokuField.equals(sudokuFieldSecond)
                && sudokuFieldSecond.equals(sudokuField));
        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        SudokuField field4 = new SudokuField();
        SudokuField field5 = new SudokuField();

        BacktrackingSudokuSolver back = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(back);
        field1.setFieldValue(1);
        field2.setFieldValue(7);
        field4.setFieldValue(1);
        field5.setFieldValue(1);

        //obiekty sobie równe
        assertTrue(field1.equals(field1));
        // różne obiekty
        assertFalse(field1.equals(field2));
        //jeśli x.equals(y) i y.equals(z), to także x.equals(z)
        assertTrue(field1.equals(field4));
        assertTrue(field4.equals(field5));
        assertTrue(field1.equals(field5));

        //zmiana wartosci
        field1.setFieldValue(7);
        assertFalse(field1.equals(field4));

        assertFalse(field1.equals(null));
        assertFalse(field1.equals(solver));

    }

    @Test
    public void hashCodeTest() {
        sudokuFieldSecond = new SudokuField();
        assertEquals(sudokuField.hashCode(), sudokuFieldSecond.hashCode());

        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        SudokuField field4 = new SudokuField();
        SudokuField field3;

        field3 = field1;
        field1.setFieldValue(1);
        field2.setFieldValue(7);
        field4.setFieldValue(1);

        //spójność:obiekty, które są sobie równe, muszą zwracać ten sam kod hashCode
        assertEquals(field1.hashCode(),field4.hashCode());
        assertTrue(field1.equals(field4));

        //spójność : wartość hashCode() może się zmienić tylko wtedy, gdy zmieni się właściwość, która jest w equals().
        field1.setFieldValue(2);
        assertNotEquals(field1.hashCode(),field4.hashCode());
    }

    @Test
    public void hashCodeAndEqualsTest() {
        SudokuField sb = new SudokuField();
        SudokuField sb2 = sb;
        int hash1;
        int hash2;
        hash1 = sb.hashCode();
        hash2 = sb2.hashCode();
        assertEquals(sb,sb2);
        assertTrue(sb.equals(sb2));
    }

    @Test
    public void compareToTest() {
        sudokuFieldSecond = new SudokuField();

        sudokuField.setFieldValue(4);
        sudokuFieldSecond.setFieldValue(4);
        assertEquals(sudokuField.compareTo(sudokuFieldSecond), 0);

        sudokuField.setFieldValue(8);
        sudokuFieldSecond.setFieldValue(4);
        assertEquals(sudokuField.compareTo(sudokuFieldSecond), 1);

        sudokuField.setFieldValue(4);
        sudokuFieldSecond.setFieldValue(8);
        assertEquals(sudokuField.compareTo(sudokuFieldSecond), -1);
        assertThrows(NullPointerException.class, () -> {
            sudokuField.compareTo(null);
        });
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        sudokuField = new SudokuField(8);
        sudokuFieldSecond = (SudokuField) sudokuField.clone();

        assertTrue(sudokuField.equals(sudokuFieldSecond)
                && sudokuFieldSecond.equals(sudokuField));
    }
}
