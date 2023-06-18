package hr.mlinx.iii;

public interface TreeNode<Key extends Comparable<Key>, Value> {

    Key getKey();
    void setKey(Key key);

    Value getValue();
    void setValue(Value value);

    TreeNode<Key, Value> getParent();
    void setParent(TreeNode<Key, Value> parent);

    TreeNode<Key, Value> getLeft();
    void setLeft(TreeNode<Key, Value> left);

    TreeNode<Key, Value> getRight();
    void setRight(TreeNode<Key, Value> right);

    boolean isRoot();
    boolean isLeftChild();

    boolean hasNoLeftChild();
    boolean hasNoRightChild();

    TreeNodeColor getColor();
    void setColor(TreeNodeColor color);

    int getHeight();
    void setHeight(int height);

    String keyValueString();

}
