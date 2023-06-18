package hr.mlinx.iii;

public class BinarySearchTree<Key extends Comparable<Key>, Value> implements Tree<Key, Value> {

    protected TreeNode<Key, Value> root;

    protected TreeNode<Key, Value> search(TreeNode<Key, Value> node, Key key) {
        if (node != null) {
            if (key.equals(node.getKey()))
                return node;

            return key.compareTo(node.getKey()) < 0
                    ? search(node.getLeft(), key)
                    : search(node.getRight(), key);
        }

        return null;
    }

    @Override
    public Value search(Key key) {
        TreeNode<Key, Value> found = search(root, key);

        if (found != null)
            return found.getValue();

        return null;
    }

    protected TreeNode<Key, Value> min(TreeNode<Key, Value> node) {
        while (node.getLeft() != null)
            node = node.getLeft();

        return node;
    }

    @Override
    public Value min() {
        if (isTreeEmpty())
            return null;

        return min(root).getValue();
    }

    private TreeNode<Key, Value> max(TreeNode<Key, Value> node) {
        while (node.getRight() != null)
            node = node.getRight();

        return node;
    }

    @Override
    public Value max() {
        if (isTreeEmpty())
            return null;

        return max(root).getValue();
    }

    @Override
    public void insert(Key key, Value value) {
        TreeNode<Key, Value> current, parent;

        if ((current = search(root, key)) != null) {
            current.setValue(value);
            return;
        }

        current = root;
        parent = null;

        while (current != null) {
            parent = current;

            current = key.compareTo(current.getKey()) < 0
                    ? current.getLeft()
                    : current.getRight();
        }

        TreeNode<Key, Value> newNode = new BinarySearchNode<>(key, value);
        newNode.setParent(parent);

        if (parent == null)
            root = newNode;
        else if (key.compareTo(parent.getKey()) < 0)
            parent.setLeft(newNode);
        else
            parent.setRight(newNode);
    }

    private void transplant(TreeNode<Key, Value> toTransplant, TreeNode<Key, Value> transplant) {
        if (toTransplant.isRoot())
            root = transplant;
        else if (toTransplant.isLeftChild())
            toTransplant.getParent().setLeft(transplant);
        else
            toTransplant.getParent().setRight(transplant);

        if (transplant != null)
            transplant.setParent(toTransplant.getParent());
    }

    @Override
    public void delete(Key key) {
        TreeNode<Key, Value> del = search(root, key);

        if (del != null) {
            if (del.hasNoLeftChild())
                transplant(del, del.getRight());
            else if (del.hasNoRightChild())
                transplant(del, del.getLeft());
            else {
                TreeNode<Key, Value> min = min(del.getRight());

                if (min != del.getRight()) {
                    transplant(min, min.getRight());
                    min.setRight(del.getRight());
                    min.getRight().setParent(min);
                }

                transplant(del, min);
                min.setLeft(del.getLeft());
                min.getLeft().setParent(min);
            }
        }
    }

    private int height(TreeNode<Key, Value> node) {
        if (node == null)
            return 0;

        int leftHeight = height(node.getLeft()) + 1;
        int rightHeight = height(node.getRight()) + 1;

        return Math.max(leftHeight, rightHeight);
    }

    @Override
    public int balance() {
        if (root == null)
            return 0;

        return height(root.getLeft()) - height(root.getRight());
    }

    protected boolean isTreeEmpty() {
        return root == null;
    }

    protected void buildInorderString(TreeNode<Key, Value> node, StringBuilder sb) {
        if (node != null) {
            buildInorderString(node.getLeft(), sb);
            sb.append(node.keyValueString());
            buildInorderString(node.getRight(), sb);
        }
    }

    @Override
    public String inorderString() {
        String initialStr = "Inorder representation:\n";

        if (isTreeEmpty())
            return initialStr + "[]";

        StringBuilder sb = new StringBuilder(initialStr);
        buildInorderString(root, sb);

        return sb.toString() + "\n^height=" + height(root) + ", balance factor=" + balance();
    }

    @Override
    public String recursiveString() {
        return "Recursive representation from root:\n" + (root == null ? "[]" : root) + "\n^height=" + height(root) + ", balance factor=" + balance();
    }

}
