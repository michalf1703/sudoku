public abstract class SudokuMethods {
    public static final int SIZE = 9;
    protected SudokuField[] fields;

    public SudokuMethods(final SudokuField[] fields) {
        this.fields = fields;
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int i2 = i + 1; i2 < 9; i2++) {
                if (fields[i].getFieldValue() == fields[i2].getFieldValue()) {
                    return false;
                }
            }
        }

        return true;
    }
}
