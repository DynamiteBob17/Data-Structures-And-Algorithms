package hr.mlinx.iii;

/**
 *
 * @param <Key> Key class has to already implement or override equals and hashCode methods properly
 * @param <Value> any
 */

public class LinkedNode<Key, Value> {

    private final Key key;
    private Value value;
    private LinkedNode<Key, Value> next;

    public LinkedNode(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public LinkedNode<Key, Value> getNext() {
        return next;
    }

    public void setNext(LinkedNode<Key, Value> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return String.format("[key=%s, value=%s, next=%s]", key, value, next);
    }

}
