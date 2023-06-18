package hr.mlinx.iii;

public class AVLNode<Key extends Comparable<Key>, Value> extends BinarySearchNode<Key, Value> {

    private int height;

    public AVLNode(Key key, Value value) {
        super(key, value);
        height = 1;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

}
