package kompo;

import java.io.Serializable;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;







public class SudokuField implements Serializable,Cloneable, Comparable<SudokuField> {
    private int value;
    private boolean isEmptyField;
    private ResourceBundle listBundle = ResourceBundle.getBundle("Language");


    public SudokuField() {

    }

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return this.value;
    }

    public boolean isEmptyField() {
        return isEmptyField;
    }


    public void setEmptyField() {
        isEmptyField = true;
    }

    public boolean setFieldValue(int value) {
        if (value < 0 || value > 9) {
            throw new WrongFieldException(listBundle.getObject("WrongFieldValue").toString());
        }
        this.value = value;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuField that = (SudokuField) o;

        return new EqualsBuilder().append(value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("value", value).toString();
    }

    @Override
    public int compareTo(SudokuField o) {
        ResourceBundle bundle = ResourceBundle.getBundle("Language");
        if (o != null) {
            if (this.getFieldValue() == o.getFieldValue()) {
                return 0;
            } else if (this.getFieldValue() > o.getFieldValue()) {
                return 1;
            } else {
                return -1;
            }
        } else {
            throw new NullPointerException(bundle.getString("NotNull"));
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}