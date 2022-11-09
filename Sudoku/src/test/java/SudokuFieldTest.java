
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SudokuFieldTest {
    private SudokuField sudokuField;

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

}
