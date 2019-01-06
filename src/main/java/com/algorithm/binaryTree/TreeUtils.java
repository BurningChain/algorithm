package com.algorithm.binaryTree;


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树基本算法合集
 *
 * @author miracle~
 * create at 2019-01-06
 */
public class TreeUtils {


    /**
     * 获取二叉树的节点个数
     *
     * @param tree
     * @return
     */
    public int calculateNode(BinaryTree tree) {
        if (null == tree) {
            return 0;
        }

        return calculateNode(tree.getLeft()) + calculateNode(tree.getRight()) + 1;
    }


    /**
     * 求二叉树的最大深度
     *
     * @param tree
     * @return
     */
    public int maxDeepth(BinaryTree tree) {
        if (null == tree) {
            return 0;
        }

        int left = maxDeepth(tree.getLeft());
        int right = maxDeepth(tree.getRight());

        return Math.max(left, right) + 1;
    }


    /**
     * 二叉树翻转
     *
     * @param tree
     * @return
     */
    public BinaryTree invertTree(BinaryTree tree) {
        if (null == tree) {
            return null;
        }

        BinaryTree left = invertTree(tree.getLeft());
        BinaryTree right = invertTree(tree.getRight());
        tree.setLeft(right);
        tree.setRight(left);
        return tree;
    }

    /**
     * 求二叉树的最小深度
     *
     * @param tree
     * @return
     */
    public int minDeepth(BinaryTree tree) {
        if (null == tree) {
            return 0;
        }
        int left = minDeepth(tree.getLeft());
        int right = minDeepth(tree.getRight());

        return (0 == left || 0 == right) ? left + right + 1 : Math.min(left, right) + 1;
    }

    /**
     * 前序遍历， 递归算法
     *
     * @param tree
     * @return
     */
    public List<Integer> preOrder(BinaryTree tree) {
        if (null == tree) {
            return null;
        }

        List<Integer> list = new ArrayList<>();
        list.add(tree.getValue());
        preOrderSubTree(list, tree.getLeft());
        preOrderSubTree(list, tree.getRight());
        return list;
    }

    private List<Integer> preOrderSubTree(List<Integer> list, BinaryTree tree) {
        if (null == tree) {
            return list;
        }
        list.add(tree.getValue());
        preOrderSubTree(list, tree.getLeft());
        preOrderSubTree(list, tree.getRight());
        return list;
    }

    /**
     * 非循环前序遍历， 深度优先
     *
     * @param tree
     * @return
     */
    public List<Integer> preOrderNoLoopOne(BinaryTree tree) {
        if (null == tree) {
            return null;
        }

        Stack<BinaryTree> stack = new Stack<>();
        stack.push(tree);
        List<Integer> list = new ArrayList<>();
        while (!stack.empty() && null != tree) {
            BinaryTree pop = stack.pop();
            list.add(pop.getValue());
            if (null != pop.getRight()) {
                stack.push(pop.getRight());
            }
            if (null != pop.getLeft()) {
                stack.push(pop.getLeft());
            }
        }

        return list;
    }

    /**
     * 前序遍历非循环算法
     *
     * @param tree
     * @return
     */
    public List<Integer> preOrderNoLoop(BinaryTree tree) {
        if (null == tree) {
            return null;
        }

        List<Integer> list = new ArrayList<>();
        Stack<BinaryTree> stack = new Stack<>();
        BinaryTree current = tree;
        while (null != current || !stack.empty()) {
            if (null != current) {
                list.add(current.getValue());
                stack.push(current);
                current = current.getLeft();
            } else {
                current = stack.pop().getRight();
            }

        }

        return list;
    }

    /**
     * 递归中序遍历
     *
     * @param root
     * @param list
     * @return
     */
    public List<Integer> midOrderTraversal(BinaryTree root, List<Integer> list) {

        if (null == root) return list;

        midOrderTraversal(root.getLeft(), list);
        list.add(root.getValue());
        midOrderTraversal(root.getRight(), list);
        return list;
    }

    /**
     * 中序遍历， 非递归算法
     *
     * @param root
     * @return
     */
    public List<Integer> midOrderNoLoop(BinaryTree root) {
        if (null == root) {
            return null;
        }

        Stack<BinaryTree> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        BinaryTree current = root;
        while (null != current || !stack.empty()) {
            if (null != current) {
                stack.push(current);
                current = current.getLeft();
            } else {
                BinaryTree pop = stack.pop();
                list.add(pop.getValue());
                current = pop.getRight();
            }
        }
        return list;
    }
}
