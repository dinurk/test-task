package converter;

public interface Converter<T, V> {
    public V convert(T value);
}
