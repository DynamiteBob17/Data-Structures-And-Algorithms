package hr.mlinx.iii;

// ignore parent fields of nodes for simplicity

public class AVLTree<Key extends Comparable<Key>, Value> extends BinarySearchTree<Key, Value> {

    private int getHeightOfNode(TreeNode<Key, Value> node) {
        if (node == null)
            return 0;

        return node.getHeight();
    }

    private void updateNodeHeight(TreeNode<Key, Value> node) {
        node.setHeight(Math.max(
                getHeightOfNode(node.getLeft()),
                getHeightOfNode(node.getRight())
        ) + 1);
    }

    private int balanceFactor(TreeNode<Key, Value> node) {
        if (node == null)
            return 0;

        return getHeightOfNode(node.getLeft()) - getHeightOfNode((node.getRight()));
    }

    private TreeNode<Key, Value> rightRotate(TreeNode<Key, Value> parent) {
        TreeNode<Key, Value> leftChild = parent.getLeft();
        TreeNode<Key, Value> rightChildOfLeftChild = leftChild.getRight();

        leftChild.setRight(parent);
        parent.setLeft(rightChildOfLeftChild);

        updateNodeHeight(parent);
        updateNodeHeight(leftChild);

        return leftChild;
    }

    private TreeNode<Key, Value> leftRotate(TreeNode<Key, Value> parent) {
        TreeNode<Key, Value> rightChild = parent.getRight();
        TreeNode<Key, Value> leftChildOfRightChild = rightChild.getLeft();

        rightChild.setLeft(parent);
        parent.setRight(leftChildOfRightChild);

        updateNodeHeight(parent);
        updateNodeHeight(rightChild);

        return rightChild;
    }

    private TreeNode<Key, Value> insert(TreeNode<Key, Value> node, Key key, Value value) {
        if (node == null)
            return new AVLNode<>(key, value);

        if (key.compareTo(node.getKey()) < 0)
            node.setLeft(insert(node.getLeft(), key, value));
        else
            node.setRight(insert(node.getRight(), key, value));

        updateNodeHeight(node);

        int balanceFactor = balanceFactor(node);

        if (balanceFactor > 1 && key.compareTo(node.getLeft().getKey()) < 0)
            return rightRotate(node);

        if (balanceFactor < -1 && key.compareTo(node.getRight().getKey()) > 0)
            return leftRotate(node);

        if (balanceFactor > 1 && key.compareTo(node.getLeft().getKey()) > 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        if (balanceFactor < -1 && key.compareTo(node.getRight().getKey()) < 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    @Override
    public void insert(Key key, Value value) {
        TreeNode<Key, Value> possibleDuplicate = search(root, key);
        if (possibleDuplicate != null) {
            possibleDuplicate.setValue(value);
            return;
        }

        root = insert(root, key, value);
    }

    private TreeNode<Key, Value> delete(TreeNode<Key, Value> node, Key key) {
        if (node == null)
            return null;

        if (key.compareTo(node.getKey()) < 0)
            node.setLeft(delete(node.getLeft(), key));
        else if (key.compareTo(node.getKey()) > 0)
            node.setRight(delete(node.getRight(), key));
        else {
            if (node.hasNoLeftChild() || node.hasNoRightChild()) {
                TreeNode<Key, Value> temp = node.hasNoLeftChild()
                        ? node.getRight()
                        : node.getLeft();

                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                TreeNode<Key, Value> temp = min(node.getRight());
                node.setKey(temp.getKey());
                node.setValue(temp.getValue());
                node.setRight(delete(node.getRight(), temp.getKey()));
            }
        }

        if (node == null)
            return null;

        updateNodeHeight(node);

        int balanceFactor = balanceFactor(node);

        if (balanceFactor > 1) {
            if (balanceFactor(node.getLeft()) < 0) {
                node.setLeft(leftRotate(node.getLeft()));
            }
            return rightRotate(node);
        }

        if (balanceFactor < -1) {
            if (balanceFactor(node.getRight()) > 0) {
                node.setRight(rightRotate(node.getRight()));
            }
            return leftRotate(node);
        }

        return node;
    }

    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

}
