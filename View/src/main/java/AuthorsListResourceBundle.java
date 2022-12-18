import java.util.ListResourceBundle;

public class AuthorsListResourceBundle extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"1. ", "Michal Ferdzyn ",},
                {"2. ", "Artur Grzybek "}
        };
    }
}
