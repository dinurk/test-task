package pair;

public class SimplePair<T, V> implements Pair<T, V> {

    private T first;
    private V second;

    public SimplePair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public T first() {
        return first;
    }

    @Override
    public V second() {
        return second;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\tfirst:  " + first +
                "\n\tsecond: " + second +
                "\n}";
    }
}
