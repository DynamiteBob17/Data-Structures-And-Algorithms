package hr.mlinx.iii;

public class BinarySearchNode<Key extends Comparable<Key>, Value> implements TreeNode<Key, Value> {

    protected Key key;
    protected Value value;
    protected TreeNode<Key, Value> parent, left, right;

    public BinarySearchNode(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Key getKey() {
        return key;
    }

    @Override
    public void setKey(Key key) {
        this.key = key;
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public TreeNode<Key, Value> getParent() {
        return parent;
    }

    @Override
    public void setParent(TreeNode<Key, Value> parent) {
        this.parent = parent;
    }

    @Override
    public TreeNode<Key, Value> getLeft() {
        return left;
    }

    @Override
    public void setLeft(TreeNode<Key, Value> left) {
        this.left = left;
    }

    @Override
    public TreeNode<Key, Value> getRight() {
        return right;
    }

    @Override
    public void setRight(TreeNode<Key, Value> right) {
        this.right = right;
    }

    @Override
    public boolean isRoot() {
        return parent == null;
    }

    @Override
    public boolean isLeftChild() {
        return this == parent.getLeft();
    }

    @Override
    public boolean hasNoLeftChild() {
        return left == null;
    }

    @Override
    public boolean hasNoRightChild() {
        return right == null;
    }

    @Override
    public TreeNodeColor getColor() { return null; }

    @Override
    public void setColor(TreeNodeColor color) {}

    @Override
    public int getHeight() { return -1; }

    @Override
    public void setHeight(int height) {}

    @Override
    public String keyValueString() {
        return String.format("[key=%s, value=%s]", key, value);
    }

    @Override
    public String toString() {
        return String.format("[key=%s, value=%s, left=%s, right=%s]",
                key, value, left, right);
    }

}
