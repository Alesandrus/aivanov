package ru.job4j.tree;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class SimpleTree.
 *
 * @author Alexander Ivanov
 * @since 09.04.2017
 * @version 1.0
 */
public class SimpleTree<E> {
    private Leaf<E> root;

    private static class Leaf<E> {
        E key;
        ArrayList<Leaf<E>> children = new ArrayList<>();

        Leaf(E key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return key.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Leaf<?> leaf = (Leaf<?>) o;
            return Objects.equals(key, leaf.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    public Leaf<E> addChild(Leaf parent, E key) {
        Leaf<E> son = null;
        if (root == null) {
            root = parent;
            son = new Leaf<E>(key);
            parent.children.add(son);
        } else {
            Leaf<E> leaf = findParent(parent, root);
            if (leaf != null) {
                son = new Leaf(key);
                leaf.children.add(son);
            }
        }
        return son;
    }

    public List<E> getParentChildren(Leaf<E> leaf) {
        Leaf<E> parent = this.findParent(leaf, root);
        List<E> list = new ArrayList<E>();
        for (int i = 0; i < parent.children.size(); i++) {
            list.add(parent.children.get(i).key);
        }
        return list;
    }

    public List<E> getChildren() {
        List<E> list = new ArrayList<E>();
        if (root != null) {
            getRecursiveListOfChildren(root, list);
        }
        return list;
    }

    private List<E> getRecursiveListOfChildren(Leaf<E> leaf, List<E> list) {
        Leaf<E> currentRoot = leaf;
        for (int i = 0; i < currentRoot.children.size(); i++) {
            list.add(currentRoot.children.get(i).key);
            if (currentRoot.children.get(i).children.size() > 0) {
                getRecursiveListOfChildren(currentRoot.children.get(i), list);
            }
        }
        return list;
    }

    private Leaf<E> findParent(Leaf<E> parent, Leaf<E> currentRoot) {
        if (currentRoot.equals(parent)) {
            return currentRoot;
        }
        Leaf<E> leafForRec;
        Leaf<E> leafParent;
        for (int i = 0; i < currentRoot.children.size(); i++) {
            leafForRec = currentRoot.children.get(i);
            leafParent = findParent(parent, leafForRec);
            if (leafParent != null) {
                return leafParent;
            }
        }
        return null;
    }

    public Leaf<E> findLeaf(Leaf<E> leaf) {
        Leaf<E> element = findParent(leaf, root);
        return element;
    }

    public static void main(String[] args) {
        SimpleTree<Integer> tree = new SimpleTree<>();
        Leaf<Integer> leafFirst = new Leaf<>(1);
        Leaf<Integer> son1 = tree.addChild(leafFirst, 2);
        Leaf<Integer> s2 = tree.addChild(son1, 3);
        Leaf<Integer> s3 = tree.addChild(son1, 4);
        Leaf<Integer> s4 = tree.addChild(son1, 5);
        Leaf<Integer> s5 = tree.addChild(s3, 7);


       /* for (int i = 0; i < son1.children.size(); i++) {
            System.out.println(son1.children.get(i).key);
        }
        System.out.println(tree.findParent(new Leaf<>(7), tree.root));

        for (Integer i : tree.getParentChildren(son1)) {
            System.out.println(i);
        }*/

       for (Integer i : tree.getChildren()) {
           System.out.println(i);
       }

       Leaf<Integer> leafForSearch = new Leaf<>(10);
        System.out.println(tree.findLeaf(leafForSearch));
    }
}
