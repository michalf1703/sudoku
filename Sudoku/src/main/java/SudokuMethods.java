import java.util.ArrayList;
import java.util.List;

public abstract class SudokuMethods {
    public static final int SIZE = 9;
    private List<SudokuField> fields;

    public SudokuMethods(final List<SudokuField> fields) {
        this.fields = fields;
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int i2 = i + 1; i2 < 9; i2++) {
                if (fields.get(i).getFieldValue() == fields.get(i2).getFieldValue()) {
                    return false;
                }
            }
        }

        return true;
    }
    public List<Integer> getFields() {
        List<Integer> valueList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            valueList.add(fields.get(i).getFieldValue());
        }

        return valueList;
    }
}
