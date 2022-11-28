import java.io.Serializable;

public interface Dao<T> {
    T read();

    void write(T obj);
}
