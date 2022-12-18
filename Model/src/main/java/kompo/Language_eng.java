package kompo;

import java.io.Serializable;
import java.util.ListResourceBundle;

public class Language_eng extends ListResourceBundle implements Serializable {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"_wrongFieldValue", "Must be <1,9>"},
                {"_wrongLength", "Length must be equals 9"}
        };
    }
}
