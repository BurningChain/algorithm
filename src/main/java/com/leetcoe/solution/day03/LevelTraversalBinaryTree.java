package com.leetcoe.solution.day03;


import sun.misc.Queue;

import java.util.ArrayList;
import java.util.List;

public class LevelTraversalBinaryTree {

    public void display(TreeNode root) {

        if (null == root) {
            return;
        }
        System.out.println(root.getData() + ",");
        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(root.getLeft());
        nodeList.add(root.getRight());
        doDisplay(nodeList);
    }

    private void doDisplay(List<TreeNode> nodeList) {
        if (null == nodeList || nodeList.size() == 0) {
            return;
        }
        List<TreeNode> newList = new ArrayList<>();
        for (TreeNode node : nodeList) {
            if (null == node) {
                continue;
            }
            System.out.print(node.getData() + ",");
            newList.add(node.getLeft());
            newList.add(node.getRight());
        }
        System.out.println();
        doDisplay(newList);
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode('a');
        TreeNode b = new TreeNode('b');
        TreeNode c = new TreeNode('c');
        TreeNode d = new TreeNode('d');
        TreeNode e = new TreeNode('e');
        TreeNode f = new TreeNode('f');

        TreeNode g = new TreeNode('g');
        TreeNode h = new TreeNode('h');
        TreeNode i = new TreeNode('i');
        TreeNode j = new TreeNode('j');

        root.setLeft(b);
        root.setRight(c);
        c.setLeft(e);
        b.setLeft(d);
        d.setRight(f);

        new LevelTraversalBinaryTree().display(root);

    }
}


class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private char data;

    public TreeNode(char data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }
}
