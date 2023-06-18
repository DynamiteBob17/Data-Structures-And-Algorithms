package hr.mlinx.iii;

/**
 *
 * @param <Key> implements Comparable, needs appropriate equals method
 * @param <Value> any
 */

public interface Tree<Key extends Comparable<Key>, Value> {

    Value search(Key key);
    void insert(Key key, Value value);
    void delete(Key key);

    Value min();
    Value max();

    int balance();

    String inorderString();
    String recursiveString();

}
