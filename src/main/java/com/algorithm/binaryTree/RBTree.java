package com.algorithm.binaryTree;

public class RBTree<T extends Comparable<T>> {

    private RBNode<T> root;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public class RBNode<T extends Comparable<T>> {
        boolean color;
        T key;
        RBNode<T> left;
        RBNode<T> right;
        RBNode<T> parent;

        public RBNode(boolean color, T key, RBNode<T> left, RBNode<T> right, RBNode<T> parent) {
            this.key = key;
            this.color = color;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public T getKey() {
            return this.key;
        }

        @Override
        public String toString() {
            return "RBNode{" +
                    "color=" + color +
                    ", key=" + key +
                    ", left=" + left +
                    ", right=" + right +
                    ", parent=" + parent +
                    '}';
        }
    }

    public RBTree() {
        root = null;
    }

    public RBNode<T> parentOf(RBNode<T> node) {
        return node != null ? node.parent : null;
    }

    public boolean colorOf(RBNode<T> node) {
        return null != node ? node.color : BLACK;
    }

    public boolean isRed(RBNode<T> node) {
        return null != node && node.color == RED;
    }

    public boolean isBlack(RBNode<T> node) {
        // return null != node && node.color == BLACK;
        return !isRed(node);
    }

    private void setBlack(RBNode<T> node) {
        if (null != node) {
            node.color = BLACK;
        }
    }

    private void setRed(RBNode<T> node) {
        if (null != node) {
            node.color = RED;
        }
    }

    private void setParent(RBNode<T> node, RBNode<T> parent) {
        if (null != node) {
            node.parent = parent;
        }
    }

    private void setColor(RBNode<T> node, boolean color) {
        if (null != node) {
            node.color = color;
        }
    }


    /**
     * 左旋
     * @param node
     */
    public void leftRotate(RBNode<T> node) {

        RBNode<T> right = node.right;

        if (null != right.left) {
            node.right = right.left;
            right.left.parent = node;
        }

        right.parent = node.parent;

        if (null != node.parent) {
            if (node == node.parent.left) {
                node.parent.left = right;
            } else {
                node.parent.right = right;
            }
        } else {
            this.root = right;
        }

        node.parent = right;
        right.left = node;
    }

    /**
     * 右旋
     * @param node
     */
    public void rightRotate(RBNode<T> node) {

        RBNode<T> left = node.left;

        if (null != left.right) {
            left.right.parent = node;
            node.left = left.right;
        }

        left.parent = node.parent;

        if (null != node.parent) {
            if (node == node.parent.left) {
                node.parent.left = left;
            } else {
                node.parent.right = left;
            }
        } else {
            this.root = left;
        }

        node.parent = left;
        left.right = node;
    }

    // 插入节点
    public void insert(RBNode<T> node) {
        int cmp;
        RBNode<T> parent = null;
        RBNode<T> current = this.root;

        while (null != current) {
            parent = current;
            cmp = node.key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        node.parent = parent;
        if (parent != null) {
            cmp = node.key.compareTo(parent.key);
            if (cmp < 0) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        } else {
            this.root = node;
        }

        node.color = RED;
    }

    public void insertKey(T key) {
        if (null != key) {
            RBNode<T> node = new RBNode<>(BLACK, key, null, null, null);
            insert(node);
        }
        throw new IllegalArgumentException("key cannot be null");
    }

    /**
     * 插入修正，三种情况，只有当父节点为红色时才考虑修正，若父节点为黑色则不考虑修正：
     * 1. 叔叔节点也为红色；
     * 2. 叔叔节点为黑色，当前节点是右子节点
     * 3. 叔叔节点为黑色，当前节点是左子节点
     * @param node
     */
    private void insertFixUp(RBNode<T> node) {
        RBNode<T> parent, grandParent;

        // 判断父亲节点不为空，且父亲节点是红色，此时祖父节点一定不为空，且一定是黑色
        while (null != (parent = parentOf(node)) && isRed(parent)) {
            //
            grandParent = parentOf(parent);

            if (parent == grandParent.left) {
                RBNode<T> uncle = grandParent.right;
                if (isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(grandParent);
                    node = grandParent;
                    continue;
                } else if (isBlack(uncle) && node == parent.left) {

                } else if (isBlack(uncle) && node == parent.right) {

                }


            }
        }

    }

}
