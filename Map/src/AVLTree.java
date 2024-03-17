import java.util.ArrayList;

/**
 * 二分平衡树实现.
 * <p>包括添加, 删除, 查询等操作.</p>
 *
 * @param <K> 支持泛型
 * @param <V> 支持泛型
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 * @see java.util.ArrayList
 */
public class AVLTree<K extends Comparable<K>, V> {

    /**
     * 内部节点类.
     */
    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;
        public int height;

        /**
         * 包含key, value的构造方法.
         *
         * @param key 键
         * @param value 值
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.right = null;
            this.left = null;
            this.height = 1;
        }
    }
    Node root;
    int size;

    /**
     * 无参数构造方法.
     */
    public AVLTree() {
        root = null;
        size = 0;
    }

    /**
     * 查询树的大小.
     *
     * @return 树大小
     */
    public int getSize() {
        return size;
    }

    /**
     * 查询树是否为空.
     *
     * @return 空为true, 否则为false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断是否当前满足BST.
     *
     * @return 满足为true, 否则false
     */
    public boolean isBST() {
        ArrayList<K> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 1; i < list.size(); i ++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 中序遍历.
     *
     * @param node 以node为根的节点
     * @param list 链表
     */
    private void inOrder(Node node, ArrayList<K> list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node.key);
        inOrder(node.right, list);
    }

    /**
     * 判断当前是否满足AVL.
     *
     * @return 满足为true, 否则false
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * 递归函数, 判断node为根的树是否保持平衡.
     *
     * @param node 以node为根的节点
     * @return 满足为true, 否则false
     */
    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 获取节点高度.
     *
     * @param node 以node为根的树
     * @return node的高度
     */
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * 获取节点平衡因子.
     *
     * @param node 以node为根的树
     * @return node的平衡因子
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 右旋转.
     *
     * @param y 失去平衡的节点
     * @return 维护好平衡之后的新树
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        // 右旋转过程
        x.right = y;
        y.left = T3;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    /**
     * 左旋转.
     *
     * @param y 失去平衡的节点
     * @return 维护好平衡之后的新树
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T3 = x.left;

        // 左旋转过程
        x.left = y;
        y.right = T3;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    /**
     * 添加键值对, 递归方式.
     *
     * @param key 键
     * @param value 值
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 递归函数, 在以node为根的树中尝试添加键值对.
     *
     * @param node 以node为根的树
     * @param key 键
     * @param value 值
     * @return 添加完之后返回的新树
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size ++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        // 更新height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        //平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0 ) {
            return rightRotate(node);
        }
        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    /**
     * 返回以node为根节点的二分搜索树中, key所在的节点.
     *
     * @param node 以node为根节点的二分搜索树
     * @param key 键
     * @return key所在的节点
     */
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    /**
     * 查看是否包含key所指的值.
     *
     * @param key 键
     * @return 存在为true, 否则false
     */
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    /**
     * 获取key所指的值.
     *
     * @param key 键
     * @return key指的值.
     */
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null? null: node.value;
    }

    /**
     * 设置key所指的值.
     *
     * @param key 键
     * @param value 新值
     * @throws IllegalArgumentException 不存在key则设置失败
     */
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + "doesn't exist.");
        }
        node.value = value;
    }

    /**
     * 递归函数, 获取以node为根的树的最小值节点.
     *
     * @param node 以node为根的树
     * @return 以node为根的树的最小值节点
     */
    private Node minimumR(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimumR(node.left);
    }

    /**
     * 递归函数, 删除以node为根的树的最小值节点.
     *
     * @param node 以node为根的树
     * @return 删除完之后返回的新树
     */
    private Node removeMinR(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMinR(node.left);
        return node;
    }

    /**
     * 移除key指向的值.
     *
     * @param key 键
     * @return key所指的值
     */
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    /**
     * 递归函数, 在以node为根的树中尝试删除key节点.
     *
     * @param node 以node为根的树
     * @param key 键
     * @return 删除完之后返回的新树
     */
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        Node retNode = null;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode =  node;
        } else {
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            }
            else if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            } else {
                // 找到比待删除节点大的最小节点，用这个节点顶替待删除节点
                Node successor = minimumR(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }
        if (retNode == null) {
            return null;
        }
        // 更新height
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        //平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0 ) {
            return rightRotate(retNode);
        }
        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }
        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }
}