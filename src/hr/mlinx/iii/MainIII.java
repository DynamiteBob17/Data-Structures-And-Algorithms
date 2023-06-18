package hr.mlinx.iii;

import hr.mlinx.util.Util;

public class MainIII {

    public static void main(String[] args) {
        HashMap<String, Long> map = new HashMap<>();
        System.out.println("MAP >");
        System.out.println();

        for (int i = 0; i < 16; ++i)
            map.put(randomString(), randomLong());
        System.out.println("Random fill map:\n" + map);

        System.out.println("Put some values for keys: 'test', '_test'...");
        System.out.println();

        map.put("test", 9132L);
        System.out.println("Value for 'test'=" + map.get("test"));
        map.remove("test");
        map.remove("test");
        System.out.println("Attempt to remove key '_test' twice...");
        System.out.println("Value for 'test' after both deletions=" + map.get("test"));

        System.out.println();

        map.put("test_", 1412L);
        System.out.println("Value for '_test'=" + map.get("test_"));
        System.out.println("Changed value for key '_test'...");
        map.put("test_", 10000000L);
        System.out.println("Value for '_test'=" + map.get("test_"));
        System.out.println();

        System.out.println("Map after modifications:\n" + map);
        System.out.println("-----------------------------------------");

        int amount = 16;
        int[] ints = new int[amount];
        String[] strings = new String[amount];
        for (int i = 0; i < amount; ++i) {
            ints[i] = randomInt();
            strings[i] = randomString();
        }

        System.out.println("BINARY TREES >");
        System.out.println("(Balance factor is the difference of the left and right subtree heights of the root node)");
        System.out.println();

        testTreeOne(new BinarySearchTree<>(), ints, strings, "BST");
        testTreeTwo(new BinarySearchTree<>());

        testTreeOne(new RedBlackTree<>(), ints, strings, "RED BLACK TREE");
        testTreeTwo(new RedBlackTree<>());

        testTreeOne(new AVLTree<>(), ints, strings, "AVL TREE");
        testTreeTwo(new AVLTree<>());
    }

    private static void testTreeOne(Tree<Integer, String> tree, int[] ints, String[] strings, String type) {
        System.out.println(type + ":");
        System.out.println();
        for (int i = 0; i < 16; ++i)
            tree.insert(ints[i], strings[i]);
        System.out.println("Random fill:");
        System.out.println(tree.inorderString());
        System.out.println(tree.recursiveString());
        System.out.println();
    }

    private static void testTreeTwo(Tree<Integer, String> tree) {
        tree.insert(1, "a");
        tree.insert(3, "c");
        tree.insert(2, "b");
        tree.insert(2, "g");
        tree.insert(4, "d");
        tree.insert(5, "e");
        System.out.println("New manually filled tree:");
        System.out.println(tree.inorderString());
        System.out.println(tree.recursiveString());
        System.out.println();
        keySearch(tree, 2);
        keySearch(tree, 3);
        minSearch(tree);
        maxSearch(tree);
        System.out.println();
        deleteKey(tree, 1);
        deleteKey(tree, 4);
        deleteKey(tree, 2);
        tree.delete(2);
        System.out.println("Attempted to delete key 2 again...");
        System.out.println(tree.inorderString());
        System.out.println();
        tree.delete(3);
        tree.delete(3);
        tree.delete(5);
        System.out.println("Deleted all other keys...");
        System.out.println(tree.inorderString());
        System.out.println(tree.recursiveString());
        System.out.println();
        keySearch(tree, 1);
        minSearch(tree);
        maxSearch(tree);
        System.out.println("-----------------------------------------");
    }

    private static void keySearch(Tree<Integer, String> tree, int key) {
        System.out.println("Search for key " + key + "=" + tree.search(key));
    }

    private static void minSearch(Tree<Integer, String> tree) {
        System.out.println("Min=" + tree.min());
    }

    private static void maxSearch(Tree<Integer, String> tree) {
        System.out.println("Max=" + tree.max());
    }

    private static void deleteKey(Tree<Integer, String> tree, int key) {
        tree.delete(key);
        System.out.println("Deleted key " + key + "...");
        System.out.println(tree.inorderString());
        System.out.println();
    }

    private static String randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;

        return Util.R.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static long randomLong() {
        return Util.R.nextLong(0, Long.MAX_VALUE);
    }

    private static int randomInt() {
        return Util.R.nextInt(0, 200);
    }

}
