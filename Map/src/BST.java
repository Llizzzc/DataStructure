public class BST<K extends Comparable<K>, V> {

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.right = null;
            this.left = null;
        }
    }
    Node root;
    int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
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
     * @return 返回添加完之后返回的新树
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
        return node;
    }

    /**
     * 返回以node为根节点的二分搜索树中, key所在的节点.
     *
     * @param node 以node为根节点的二分搜索树
     * @param key 键
     * @return 返回key所在的节点
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
     * @return 若存在返回true, 否则返回false
     */
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    /**
     * 获取key所指的值.
     *
     * @param key 键
     * @return 返回key指的值.
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
     * @throws IllegalArgumentException 不存在key
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
     * @return 返回以node为根的树的最小值节点
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
     * @return 返回删除完之后返回的新树
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
     * @return 返回key所指的值
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
     * @return 返回删除完之后返回的新树
     */
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            // 找到比待删除节点大的最小节点，用这个节点顶替待删除节点
            Node successor = minimumR(node.right);
            successor.right = removeMinR(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }
}