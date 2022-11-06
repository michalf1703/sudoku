public class SudokuField {
    private int value;

    public SudokuField() {

    }

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return this.value;
    }

    public boolean setFieldValue(int value) {
        if (value < 0 || value > 9) {
            return false;
        }
        this.value = value;
        return true;
    }
}