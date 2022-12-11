import java.util.ArrayList;
import java.util.List;

public class SudokuColumn extends SudokuMethods {
    public SudokuColumn(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>(getSudokuMethodsList());
        return new SudokuColumn(fields);
    }
}
