package hr.mlinx.iii;

/**
 *
 * @param <Key> needs appropriate equals method
 * @param <Value> any
 */

public class HashMap<Key, Value> {

    private static final int LENGTH = 16;
    private final LinkedNode<Key, Value>[] map;

    @SuppressWarnings("unchecked")
    public HashMap() {
        map = new LinkedNode[LENGTH];
    }

    public void put(Key key, Value value) {
        int index = getIndex(key);
        LinkedNode<Key, Value> current = map[index];

        while (current != null) {
            if (key.equals(current.getKey())) {
                current.setValue(value);
                return;
            } else if (current.getNext() == null) {
                current.setNext(new LinkedNode<>(key, value));
                return;
            }

            current = current.getNext();
        }

        // create head of the linked list at 'index';
        // we only get here if it's currently null at that index
        map[index] = new LinkedNode<>(key, value);
    }

    public void remove(Key key) {
        int index = getIndex(key);
        LinkedNode<Key, Value> current = map[index], previous = null;

        while (current != null) {
            if (key.equals(current.getKey())) {
                if (previous != null) {
                    previous.setNext(current.getNext());
                } else {
                    map[index] = null;
                }

                return;
            }

            previous = current;
            current = current.getNext();
        }
    }

    public Value get(Key key) {
        LinkedNode<Key, Value> current = map[getIndex(key)];

        while (current != null) {
            if (key.equals(current.getKey())) {
                return current.getValue();
            }

            current = current.getNext();
        }

        return null;
    }

    private int getIndex(Key key) {
        return key.hashCode() & (LENGTH - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (LinkedNode<Key, Value> element : map) {
            sb.append(element);
            sb.append("\n");
        }

        return sb.toString();
    }

}
