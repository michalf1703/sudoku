package kompo;

import java.util.ArrayList;
import java.util.List;


public class SudokuBox extends SudokuMethods {
    public SudokuBox(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>(getSudokuMethodsList());
        return new SudokuBox(fields);
    }
}
