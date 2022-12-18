package kompo;

import java.io.Serializable;
import java.util.ListResourceBundle;

public class WrongSomething_eng extends ListResourceBundle implements Serializable {

    /*------------------------ METHODS REGION ------------------------*/
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"_wrongFieldValue", "Must be <1,9>"},
                {"_wrongLength", "Length must be equals 9"}
        };
    }
}
