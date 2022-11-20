
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class SudokuFieldTest {
    private SudokuField sudokuField;
    private SudokuField sudokuFieldSecond;

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
      assertFalse(sudokuField.setFieldValue(-1));
      assertFalse(sudokuField.setFieldValue(10));
      assertTrue(sudokuField.setFieldValue(5));
      assertTrue(sudokuField.setFieldValue(9));

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
    }

    @Test
    public void hashCodeTest() {
        sudokuFieldSecond = new SudokuField();
        assertEquals(sudokuField.hashCode(), sudokuFieldSecond.hashCode());
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
}
