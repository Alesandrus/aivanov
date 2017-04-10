package ru.job4j.tree;

/**
 * Class BinTree.
 *
 * @author Alexander Ivanov
 * @since 10.04.2017
 * @version 1.0
 */
public class BinTree<E extends Comparable> {

    private Node<E> root;

    public void put(E key) {
        Node<E> nextNode = root;
        Node<E> currentNode = null;
        while (nextNode != null) {
            int comp = key.compareTo(nextNode.key);
            if (comp < 0) {
                currentNode = nextNode;
                nextNode = nextNode.left;
            } else if (comp > 0) {
                currentNode = nextNode;
                nextNode = nextNode.right;
            }
        }
        Node<E> newNode = new Node<>(key);
        if (currentNode == null) {
            root = newNode;
        } else {
            if (key.compareTo(currentNode.key) < 0) {
                currentNode.left = newNode;
            } else {
                currentNode.right = newNode;
            }
        }
    }

    public boolean contains(E key) {
        Node<E> node = root;
        while (node != null) {
            int comp = key.compareTo(node.key);
            if (comp == 0) {
                return true;
            } else {
                if (comp > 0) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
        }
        return false;
    }

    private class Node<E> {
        E key;
        Node<E> left;
        Node<E> right;

        Node(E key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {
        BinTree<Integer> tree = new BinTree<>();
        tree.put(8);
        tree.put(4);
        tree.put(5);
        tree.put(7);
        tree.put(25);
        System.out.println(tree.contains(25));
    }
}
