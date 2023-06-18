package hr.mlinx.iii;

import static hr.mlinx.iii.TreeNodeColor.RED;
import static hr.mlinx.iii.TreeNodeColor.BLACK;

public class RedBlackTree<Key extends Comparable<Key>, Value> implements Tree<Key, Value> {

    private TreeNode<Key, Value> root;
    private final TreeNode<Key, Value> nil;

    public RedBlackTree() {
        nil = new RedBlackNode<>(null, null);
        nil.setColor(BLACK);
        root = nil;
    }

    private TreeNode<Key, Value> search(TreeNode<Key, Value> node, Key key) {
        if (node != nil) {
            if (key.equals(node.getKey()))
                return node;

            return key.compareTo(node.getKey()) < 0
                    ? search(node.getLeft(), key)
                    : search(node.getRight(), key);
        }

        return nil;
    }

    @Override
    public Value search(Key key) {
        return search(root, key).getValue();
    }

    private TreeNode<Key, Value> min(TreeNode<Key, Value> node) {
        while (node.getLeft() != nil)
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
        while (node.getRight() != nil)
            node = node.getRight();

        return node;
    }

    @Override
    public Value max() {
        if (isTreeEmpty())
            return null;

        return max(root).getValue();
    }

    private void leftRotate(TreeNode<Key, Value> parent) {
        TreeNode<Key, Value> rightChild = parent.getRight();
        parent.setRight(rightChild.getLeft());

        if (rightChild.getLeft() != nil)
            rightChild.getLeft().setParent(parent);

        rightChild.setParent(parent.getParent());

        if (parent.getParent() == nil)
            root = rightChild;
        else if (parent.isLeftChild())
            parent.getParent().setLeft(rightChild);
        else
            parent.getParent().setRight(rightChild);

        rightChild.setLeft(parent);
        parent.setParent(rightChild);
    }

    private void rightRotate(TreeNode<Key, Value> parent) {
        TreeNode<Key, Value> leftChild = parent.getLeft();
        parent.setLeft(leftChild.getRight());

        if (leftChild.getRight() != nil)
            leftChild.getRight().setParent(parent);

        leftChild.setParent(parent.getParent());

        if (parent.getParent() == nil)
            root = leftChild;
        else if (parent.isLeftChild())
            parent.getParent().setLeft(leftChild);
        else
            parent.getParent().setRight(leftChild);

        leftChild.setRight(parent);
        parent.setParent(leftChild);
    }

    private void insertFixup(TreeNode<Key, Value> inserted) {
        while (inserted.getParent().getColor() == RED) {
            TreeNode<Key, Value> uncle;

            if (inserted.getParent().isLeftChild()) {
                uncle = inserted.getParent().getParent().getRight();

                if (uncle.getColor() == RED) {
                    inserted.getParent().setColor(BLACK);
                    uncle.setColor(BLACK);
                    inserted.getParent().getParent().setColor(RED);
                    inserted = inserted.getParent().getParent();
                } else {
                    if (inserted == inserted.getParent().getRight()) {
                        inserted = inserted.getParent();
                        leftRotate(inserted);
                    }

                    inserted.getParent().setColor(BLACK);
                    inserted.getParent().getParent().setColor(RED);
                    rightRotate(inserted.getParent().getParent());
                }
            } else {
                uncle = inserted.getParent().getParent().getLeft();

                if (uncle.getColor() == RED) {
                    inserted.getParent().setColor(BLACK);
                    uncle.setColor(BLACK);
                    inserted.getParent().getParent().setColor(RED);
                    inserted = inserted.getParent().getParent();
                } else {
                    if (inserted == inserted.getParent().getLeft()) {
                        inserted = inserted.getParent();
                        rightRotate(inserted);
                    }

                    inserted.getParent().setColor(BLACK);
                    inserted.getParent().getParent().setColor(RED);
                    leftRotate(inserted.getParent().getParent());
                }
            }
        }

        root.setColor(BLACK);
    }

    @Override
    public void insert(Key key, Value value) {
        TreeNode<Key, Value> current, parent;

        if ((current = search(root, key)) != nil) {
            current.setValue(value);
            return;
        }

        current = root;
        parent = nil;

        while (current != nil) {
            parent = current;

            current = key.compareTo(current.getKey()) < 0
                    ? current.getLeft()
                    : current.getRight();
        }

        TreeNode<Key, Value> newNode = new RedBlackNode<>(key, value);
        newNode.setParent(parent);

        if (parent == nil)
            root = newNode;
        else if (key.compareTo(parent.getKey()) < 0)
            parent.setLeft(newNode);
        else
            parent.setRight(newNode);

        newNode.setLeft(nil);
        newNode.setRight(nil);
        newNode.setColor(RED);

        insertFixup(newNode);
    }

    private void transplant(TreeNode<Key, Value> toTransplant, TreeNode<Key, Value> transplant) {
        if (toTransplant.getParent() == nil)
            root = transplant;
        else if (toTransplant.isLeftChild())
            toTransplant.getParent().setLeft(transplant);
        else
            toTransplant.getParent().setRight(transplant);

        transplant.setParent(toTransplant.getParent());
    }

    private void deleteFixup(TreeNode<Key, Value> node) {
        TreeNode<Key, Value> sibling;

        while (node != root && node.getColor() == BLACK) {
            if (node.isLeftChild()) {
                sibling = node.getParent().getRight();

                if (sibling.getColor() == RED) {
                    sibling.setColor(BLACK);
                    node.getParent().setColor(RED);
                    leftRotate(node.getParent());
                    sibling = node.getParent().getRight();
                }

                if (sibling.getLeft().getColor() == BLACK && sibling.getRight().getColor() == BLACK) {
                    sibling.setColor(RED);
                    node = node.getParent();
                } else {
                    if (sibling.getRight().getColor() == BLACK) {
                        sibling.getLeft().setColor(BLACK);
                        sibling.setColor(RED);
                        rightRotate(sibling);
                        sibling = node.getParent().getRight();
                    }

                    sibling.setColor(node.getParent().getColor());
                    node.getParent().setColor(BLACK);
                    sibling.getRight().setColor(BLACK);
                    leftRotate(node.getParent());
                    node = root;
                }
            } else {
                sibling = node.getParent().getLeft();

                if (sibling.getColor() == RED) {
                    sibling.setColor(BLACK);
                    node.getParent().setColor(RED);
                    rightRotate(node.getParent());
                    sibling = node.getParent().getLeft();
                }

                if (sibling.getRight().getColor() == BLACK && sibling.getLeft().getColor() == BLACK) {
                    sibling.setColor(RED);
                    node = node.getParent();
                } else {
                    if (sibling.getLeft().getColor() == BLACK) {
                        sibling.getRight().setColor(BLACK);
                        sibling.setColor(RED);
                        leftRotate(sibling);
                        sibling = node.getParent().getLeft();
                    }

                    sibling.setColor(node.getParent().getColor());
                    node.getParent().setColor(BLACK);
                    sibling.getLeft().setColor(BLACK);
                    rightRotate(node.getParent());
                    node = root;
                }
            }
        }

        node.setColor(BLACK);
    }

    @Override
    public void delete(Key key) {
        TreeNode<Key, Value> del = search(root, key);
        TreeNode<Key, Value> x;
        TreeNode<Key, Value> y = del;
        TreeNodeColor yOrgColor = y.getColor();

        if (del != nil) {
            if (del.getLeft() == nil) {
                x = del.getRight();
                transplant(del, del.getRight());
            } else if (del.getRight() == nil) {
                x = del.getLeft();
                transplant(del, del.getLeft());
            } else {
                y = min(del.getRight());
                yOrgColor = y.getColor();
                x = y.getRight();

                if (y != del.getRight()) {
                    transplant(y, y.getRight());
                    y.setRight(del.getRight());
                    y.getRight().setParent(y);
                } else {
                    x.setParent(y);
                }

                transplant(del, y);
                y.setLeft(del.getLeft());
                y.getLeft().setParent(y);
                y.setColor(del.getColor());
            }

            if (yOrgColor == BLACK)
                deleteFixup(x);
        }
    }

    private int height(TreeNode<Key, Value> node) {
        if (node == nil)
            return 0;

        int leftHeight = height(node.getLeft()) + 1;
        int rightHeight = height(node.getRight()) + 1;

        return Math.max(leftHeight, rightHeight);
    }

    @Override
    public int balance() {
        if (root == nil)
            return 0;

        return height(root.getLeft()) - height(root.getRight());
    }

    private boolean isTreeEmpty() {
        return root == nil;
    }

    private void buildInorderString(TreeNode<Key, Value> node, StringBuilder sb) {
        if (node != nil) {
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
        return "Recursive representation from root:\n" + (root == nil ? "[]" : root) + "\n^height=" + height(root) + ", balance factor=" + balance();
    }

}
