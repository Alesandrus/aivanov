package ru.job4j.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for SimpleTree.
 *
 * @author Alexander Ivanov
 * @since 14.04.2017
 * @version 1.0
 */
public class SimpleTreeTest {
    /**
     * Test addChild().
     * Adding child if tree has not leafs.
     */
    @Test
    public void whenAddChildToTreeIfTreeIsEmptyThenReturnChild() {
        SimpleTree<Integer> tree = new SimpleTree<>();
        SimpleTree.Leaf<Integer> leaf = new SimpleTree.Leaf<>(10);
        SimpleTree.Leaf<Integer> resultSon = tree.addChild(leaf, 100);
        SimpleTree.Leaf<Integer> checkSon = new SimpleTree.Leaf<>(100);
        assertThat(resultSon, is(checkSon));
    }

    /**
     * Test addChild().
     * Adding child if tree has not parent of this child.
     */
    @Test
    public void whenAddChildToTreeButTreeHasNotThisParentThenReturnNoSuchParentException() {
        SimpleTree<String> tree = new SimpleTree<>();
        SimpleTree.Leaf<String> leaf = new SimpleTree.Leaf<>("Root");
        tree.addChild(leaf, "firstSon");
        tree.addChild(leaf, "secondSon");
        String resultException = null;
        try {
            tree.addChild(new SimpleTree.Leaf("thirdSon"), "grandSon");
        } catch (NoSuchParentException e) {
            resultException = "No parent";
        }
        String checkException = "No parent";
        assertThat(resultException, is(checkException));
    }

    /**
     * Test getChildren().
     */
    @Test
    public void whenInvokeGetChildrenThenReturnListOfAllKeys() {
        SimpleTree<String> tree = new SimpleTree<>();
        SimpleTree.Leaf<String> leaf = new SimpleTree.Leaf<>("Root");
        tree.addChild(leaf, "firstSon");
        tree.addChild(leaf, "secondSon");
        tree.addChild(new SimpleTree.Leaf("firstSon"), "firstGrandSon");
        tree.addChild(new SimpleTree.Leaf("firstSon"), "seconfGrandSon");
        List<String> resultList = tree.getChildren();
        List<String> checkList = new ArrayList<>(Arrays.asList("firstSon", "firstGrandSon",
                "seconfGrandSon", "secondSon"));
        assertThat(resultList, is(checkList));
    }

    /**
     * Test getChildrenFromParent().
     */
    @Test
    public void whenInvokeGetChildrenFromParentThenReturnListOfKeysSons() {
        SimpleTree<String> tree = new SimpleTree<>();
        SimpleTree.Leaf<String> leaf = new SimpleTree.Leaf<>("Root");
        tree.addChild(leaf, "firstSon");
        tree.addChild(leaf, "secondSon");
        tree.addChild(new SimpleTree.Leaf("firstSon"), "firstGrandSon");
        tree.addChild(new SimpleTree.Leaf("firstSon"), "seconfGrandSon");
        List<String> resultList = tree.getChildrenFromParent(new SimpleTree.Leaf<>("Root"));
        List<String> checkList = new ArrayList<>(Arrays.asList("firstSon", "secondSon"));
        assertThat(resultList, is(checkList));
    }

    /**
     * Test contains().
     * If tree has not element.
     */
    @Test
    public void whenAddElementsAndInvokeContainsTreeOtherElementThenReturnFalse() {
        SimpleTree<String> tree = new SimpleTree<>();
        SimpleTree.Leaf<String> leaf = new SimpleTree.Leaf<>("Root");
        tree.addChild(leaf, "firstSon");
        tree.addChild(leaf, "secondSon");
        tree.addChild(new SimpleTree.Leaf("firstSon"), "firstGrandSon");
        tree.addChild(new SimpleTree.Leaf("firstSon"), "secondGrandSon");
        boolean resultContain = tree.contains("thirdSon");
        boolean checkContain = false;
        assertThat(resultContain, is(checkContain));
    }

    /**
     * Test contains().
     * If tree has same element.
     */
    @Test
    public void whenAddElementsAndInvokeContainsTreeOneOfThemThenReturnTrue() {
        SimpleTree<String> tree = new SimpleTree<>();
        SimpleTree.Leaf<String> leaf = new SimpleTree.Leaf<>("Root");
        tree.addChild(leaf, "firstSon");
        tree.addChild(leaf, "secondSon");
        tree.addChild(new SimpleTree.Leaf("firstSon"), "firstGrandSon");
        tree.addChild(new SimpleTree.Leaf("firstSon"), "secondGrandSon");
        boolean resultContain = tree.contains("secondSon");
        boolean checkContain = true;
        assertThat(resultContain, is(checkContain));
    }

    /**
     * Test isBalancedTree().
     * If tree has balance return true.
     */
    @Test
    public void whenMakeBalancedTreeAndInvokeIsBalancedTreeThenReturnTrue() {
        SimpleTree<String> tree = new SimpleTree<>();
        SimpleTree.Leaf<String> leaf = new SimpleTree.Leaf<>("Root");
        tree.addChild(leaf, "firstSon");
        tree.addChild(leaf, "secondSon");
        tree.addChild(new SimpleTree.Leaf("firstSon"), "firstGrandSon");
        tree.addChild(new SimpleTree.Leaf("firstSon"), "seconfGrandSon");
        boolean resultBalance = tree.isBalancedTree();
        boolean checkBalance = true;
        assertThat(resultBalance, is(checkBalance));
    }

    /**
     * Test isBalancedTree().
     * If tree has not balance return false.
     */
    @Test
    public void whenMakeNoBalancedTreeAndInvokeIsBalancedTreeThenReturnFalse() {
        SimpleTree<String> tree = new SimpleTree<>();
        SimpleTree.Leaf<String> leaf = new SimpleTree.Leaf<>("Root");
        tree.addChild(leaf, "firstSon");
        tree.addChild(leaf, "secondSon");
        tree.addChild(new SimpleTree.Leaf("firstSon"), "firstGrandSon");
        tree.addChild(new SimpleTree.Leaf("firstSon"), "seconfGrandSon");
        tree.addChild(new SimpleTree.Leaf("secondSon"), "thirdGrandSon");
        boolean resultBalance = tree.isBalancedTree();
        boolean checkBalance = false;
        assertThat(resultBalance, is(checkBalance));
    }
}