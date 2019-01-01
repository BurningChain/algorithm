package com.algorithm;

import sun.util.resources.cldr.rn.CurrencyNames_rn;

import java.util.*;

public class BinaryTree extends TreeNode<Entity> {

    public BinaryTree(Entity entity) {
        super(entity);
    }

    public BinaryTree(Entity entity, TreeNode<Entity> left, TreeNode<Entity> right) {
        super(entity, left, right);
    }

    /**
     * 最大深度
     */
    public int maxDeepth(TreeNode treeNode) {
        if (null == treeNode) {
            return 0;
        }

        return Math.max(maxDeepth(treeNode.getLeft()), maxDeepth(treeNode.getRight())) + 1;
    }

    /**
     * 二叉树最小深度
     *
     * @param treeNode
     * @return
     */
    public Integer minDeepth(TreeNode treeNode) {
        if (null == treeNode) {
            return 0;
        }
        return Math.min(minDeepth(treeNode.getLeft()), minDeepth(treeNode.getRight())) + 1;
    }

    /**
     * 二叉树的前序遍历
     *
     * @param treeNode
     * @param list
     * @return
     */
    public List<TreeNode> preOrderTraversal(TreeNode treeNode, List<TreeNode> list) {
        if (null == treeNode) {
            return list;
        }

        if (null == list) {
            list = new ArrayList<>();
        }
        list.add(treeNode);
        preOrderTraversal(treeNode.getLeft(), list);
        preOrderTraversal(treeNode.getRight(), list);
        return list;
    }

    public List<Object> preOrderTraversalNoLoop(TreeNode treeNode) {
        if (null == treeNode) {
            return null;
        }

        List<Object> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack();
        stack.push(treeNode);
        while (!stack.empty()) {
            TreeNode pop = stack.pop();
            list.add(pop.getT());
            if (null != treeNode.getRight()) {
                stack.push(treeNode.getRight());
            }

            if (null != treeNode.getLeft()) {
                stack.push(treeNode.getLeft());
            }

        }

        return list;
    }


    public List<Object> preOrderTraversal(TreeNode treeNode) {
        if (null == treeNode) {
            return null;
        }

        List<Object> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = treeNode;
        while (!stack.isEmpty() || null != current) {
            if (null != current) {
                stack.push(current);
                list.add(current.getT());
                current = current.getLeft();
            } else {
                current = stack.pop().getRight();
            }
        }
        return list;
    }


    /**
     * 中序遍历
     *
     * @param treeNode
     * @return
     */
    public List<Object> midTravesal(TreeNode treeNode) {
        List<Object> result = new ArrayList<>();
        if (null == treeNode) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = treeNode;
        while (!stack.empty() || null != cur) {
            if (null != cur) {
                stack.push(cur);
                cur = cur.getLeft();
            } else {
                cur = stack.pop();
                result.add(cur.getT());
                cur = cur.getRight();
            }

        }
        return result;
    }

    /**
     * 后序遍历
     *
     * @param root
     * @return
     */
    public List<Object> postTraversal(TreeNode root) {
        List<Object> list = new ArrayList<>();
        if (null == root) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.empty() || null != cur) {
            if (null != cur) {
                stack.push(cur);
                cur = cur.getRight();
            } else {
                cur = stack.pop();
                list.add(cur.getT());
                cur = cur.getLeft();
            }
        }
        return list;
    }


    /**
     * 自上而下遍历二叉树
     *
     * @param root
     * @return
     */
    public List<List<Object>> printInOrder(TreeNode root) {
        if (null == root) {
            return null;
        }

        List<List<Object>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Object> list = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                list.add(queue.poll().getT());
                if (null != queue.poll().getLeft()) {
                    queue.add(queue.poll().getLeft());
                }
                if (null != queue.poll().getRight()) {
                    queue.add(queue.poll().getRight());
                }
            }
            res.add(list);
        }
        return res;
    }


    /**
     * 自下而上遍历二叉树
     *
     * @param root
     * @return
     */
    public List<List<Object>> printInReverseOrder(TreeNode root) {

        if (null == root) {
            return null;
        }
        List<List<Object>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode current = null;
        queue.add(root);

        while (!queue.isEmpty()) {

            List<Object> list = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                current = queue.poll();
                list.add(current.getT());
                if (null != current.getLeft()) {
                    queue.add(current.getLeft());
                }
                if (null != current.getRight()) {
                    queue.add(current.getRight());
                }
            }
            res.add(0, list);
        }
        return res;
    }

    /**
     * 二叉树第K层节点个数
     *
     * @param root
     * @param k
     * @return
     */
    public int kLevelNumber(TreeNode root, int k) {
        if (null == root || k <= 0) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }

        return kLevelNumber(root.getLeft(), k - 1) + kLeafNumber(root.getRight(), k - 1);
    }

    /**
     * K层节点是叶子节点的节点个数
     *
     * @param root
     * @param k
     * @return
     */
    public int kLeafNumber(TreeNode root, int k) {
        if (null == root || k < 0) {
            return 0;
        }
        if (k == 1) {
            return root.getLeft() == null && root.getRight() == null ? 1 : 0;
        }

        return kLeafNumber(root.getLeft(), k - 1) + kLeafNumber(root.getRight(), k - 1);
    }


    /**
     * 判断两棵树是否相同
     *
     * @param one
     * @param two
     * @return
     */
    public boolean isSame(TreeNode one, TreeNode two) {
        if (null == one && null == two) {
            return true;
        } else if (null == one || null == two) {
            return false;
        } else if (!one.getT().equals(two.getT())) {
            return false;
        } else {
            return isSame(one.getLeft(), two.getLeft()) && isSame(one.getRight(), two.getRight());
        }
    }

    /**
     * 判断一棵树是否平衡二叉树
     *
     * @param treeNode
     * @return
     */
    public boolean isBalanceTree(TreeNode treeNode) {
       if (null == treeNode) {
           return true;
       }
        return Math.abs(maxDeepth(treeNode.getLeft()) - maxDeepth(treeNode.getRight())) <= 1 && isBalanceTree(treeNode.getLeft())
                && isBalanceTree(treeNode.getRight());
    }


    public TreeNode reverseTree(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        }

        TreeNode left = treeNode.getLeft();
        treeNode.setLeft(reverseTree(treeNode.getRight()));
        treeNode.setRight(reverseTree(left));

        return treeNode;
    }
}



