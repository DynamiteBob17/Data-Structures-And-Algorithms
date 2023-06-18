package hr.mlinx.iii;

public class RedBlackNode<Key extends Comparable<Key>, Value> extends BinarySearchNode<Key, Value> {

    private TreeNodeColor color;

    public RedBlackNode(Key key, Value value) {
        super(key, value);
    }

    @Override
    public TreeNodeColor getColor() {
        return color;
    }

    @Override
    public void setColor(TreeNodeColor color) {
        this.color = color;
    }

    @Override
    public String keyValueString() {
        return String.format("[key=%s, value=%s, color=%s]", key, value, color);
    }

    @Override
    public String toString() {
        return String.format("[key=%s, value=%s, color=%s, left=%s, right=%s]",
                key, value, color, left, right);
    }

}
