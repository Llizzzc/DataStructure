import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二分搜索树.
 * <p>包括添加, 遍历, 删除等操作, 不包含重复元素.</p>
 *
 * @param <E>
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 * @see java.util.Deque
 * @see java.util.LinkedList
 * @see java.util.Queue
 */
public class BST<E extends Comparable<E>> {

    /**
     * 内部节点类.
     */
    private class Node {
        E e;
        Node left, right;

        /**
         * 包含e的构造方法.
         *
         * @param e 节点元素
         */
        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }
    Node root;
    int size;

    /**
     * 无参数构造方法.
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * 获取树大小.
     *
     * @return 树大小
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断树是否为空.
     *
     * @return 空为true, 否则fasle
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向二分搜索树添加元素, 递归写法.
     *
     * @param e 待插入元素
     */
    public void addR(E e) {
        root = addR(root, e);
    }

    /**
     * 在以node为根的树中尝试添加元素e.
     *
     * @param node 以node为根的树
     * @param e 待插入元素
     * @return 完成插入之后, 新的node为根组成的树.
     */
    private Node addR(Node node, E e) {
        // 如果node是空树, 直接创建节点
        if (node == null) {
            size ++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = addR(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = addR(node.right, e);
        }
        return node;
    }

    /**
     * 添加元素非递归写法.
     *
     * @param e 待插入元素
     */
    public void add(E e) {
        // 根为空时
        if (root == null) {
            root = new Node(e);
            size ++;
            return;
        }
        // 跟踪待插入位置的上一个节点
        Node pre = root;
        while (pre != null) {
            if (e.compareTo(pre.e) < 0) {
                // 左子树如果为空, 直接生成新节点
                if (pre.left == null) {
                    pre.left = new Node(e);
                    size ++;
                    return;
                }
                // 否则进入左子树
                pre = pre.left;
            } else if (e.compareTo(pre.e) > 0) {
                // 如果右子树为空, 直接生成新节点
                if (pre.right == null) {
                    pre.right = new Node(e);
                    size ++;
                    return;
                }
                // 否则进入右子树
                pre = pre.right;
            } else {
                // 如果该元素存在, 直接返回
                return;
            }
        }
    }

    /**
     * 查看是否包含元素e, 递归写法.
     *
     * @param e 待查找元素
     * @return 存在为true, 否则为false
     */
    public boolean containsR(E e) {
        return containsR(root, e);
    }

    /**
     * 在以node为根的树中尝试查找e.
     *
     * @param node 以node为根的树
     * @param e 待查找元素
     * @return 存在为true, 否则为false
     */
    private boolean containsR(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return containsR(node.left, e);
        } else {
            return containsR(node.right, e);
        }
    }

    /**
     * 查看非递归写法.
     *
     * @param e
     * @return 存在为true, 否则为false
     */
    public boolean contains(E e) {
        if (root == null) {
            return false;
        }
        Node cur = root;
        while (cur != null) {
            if (e.compareTo(cur.e) == 0) {
                return true;
            } else if (e.compareTo(cur.e) < 0) {    // 进入左子树
                cur = cur.left;
            } else {    // 进入右子树
                cur = cur.right;
            }
        }
        return false;
    }

    /**
     * 前序遍历, 中左右, 递归写法.
     */
    public void preOrderR() {
        preOrderR(root);
    }

    /**
     * 对node为根的树进行前序遍历.
     *
     * @param node 以node为根的树
     */
    private void preOrderR(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrderR(node.left);
        preOrderR(node.right);
    }

    /**
     * 前序遍历, 中左右, 非递归写法.
     */
    public void preOrder() {
        Deque<Node> st = new LinkedList<>();    // 使用栈
        st.addLast(root);

        // 栈中还有元素时
        while (!st.isEmpty()) {
            Node cur = st.removeLast(); // 取出栈顶
            System.out.println(cur.e);
            // 有右子树则加入栈
            if (cur.right != null) {
                st.addLast(cur.right);
            }
            // 有左子树则加入栈
            if (cur.left != null) {
                st.addLast(cur.left);
            }
        }
    }

    /**
     * 中序遍历, 左中右, 递归写法.
     */
    public void inOrderR() {
        inOrderR(root);
    }

    /**
     * 对node为根的树进行中序遍历.
     *
     * @param node 以node为根的树
     */
    private void inOrderR(Node node) {
        if (node == null) {
            return;
        }
        inOrderR(node.left);
        System.out.println(node.e);
        inOrderR(node.right);
    }

    /**
     * 中序遍历, 左中右, 非递归写法.
     */
    public void inOrder() {
        Deque<Node> st = new LinkedList<>();    // 使用栈
        Node cur = root;
        st.addLast(cur);
        // 将左子树依次入栈
        while (!st.isEmpty()) {
            while (cur.left != null) {
                st.addLast(cur.left);
                cur = cur.left;
            }
            // 左子树都入栈之后, 栈顶出栈
            Node node = st.removeLast();
            System.out.println(node.e);
            // 如果当前节点有右子树, 入栈
            if (node.right != null) {
                st.addLast(node.right);
                Node rNode = node.right;
                // 右子树有左子树的话, 依次入栈
                while (rNode.left != null) {
                    st.addLast(rNode.left);
                    rNode = rNode.left;
                }
            }
        }
    }

    /**
     * 后序遍历, 左右中, 递归写法.
     */
    public void postOrderR() {
        postOrderR(root);
    }

    /**
     * 对node为根的树进行后序遍历.
     *
     * @param node 以node为根的树
     */
    private void postOrderR(Node node) {
        if (node == null) {
            return;
        }
        postOrderR(node.left);
        postOrderR(node.right);
        System.out.println(node.e);
    }

    /**
     * 后序遍历, 左右中, 非递归写法.
     */
    public void postOrder() {
        Deque<Node> st = new LinkedList<>();   // 使用栈
        Node cur = root;
        Node flat = null;   // 标记最近出栈的节点
        // 左子树依次入栈
        st.addLast(cur);
        while (!st.isEmpty()) {
            while (cur.left != null) {
                st.addLast(cur.left);
                cur = cur.left;
            }
            // 获取栈顶元素
            Node node = st.getLast();
            // 右孩子非空且右孩子没有入栈过
            if (node.right != null && node.right != flat) {
                st.addLast(node.right);
                // 从右孩子开始, 向左走, 依次入栈
                Node rLeftNode = node.right;
                while (rLeftNode.left != null) {
                    st.addLast(rLeftNode.left);
                    rLeftNode = rLeftNode.left;
                }
            } else { // 无右孩子则出栈当前节点, 并标记
                Node p = st.removeLast();
                flat = p;
                System.out.println(p.e);
            }
        }
    }

    /**
     * 层次遍历.
     */
    public void levelOrder() {
        Queue<Node> q = new LinkedList<>(); // 使用队列
        q.add(root);
        // 队列还有元素时
        while (!q.isEmpty()) {
            Node cur = q.remove();  // 打印出队的元素
            System.out.println(cur.e);
            // 左子树入队
            if (cur.left != null) {
                q.add(cur.left);
            }
            // 右子树入队
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
    }

    /**
     * 查找最小值, 递归写法.
     *
     * @return 最小值
     * @throws RuntimeException 树中没元素时, 查找失败
     */
    public E minimumR() {
        if (getSize() == 0) {
            throw new RuntimeException("FindMin failed, empty tree now.");
        }
        return minimumR(root).e;
    }

    /**
     * 递归函数, 在以node为根的树中尝试查找最小值.
     *
     * @param node 以node为根的树
     * @return 最小值节点
     */
    private Node minimumR(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimumR(node.left);
    }

    /**
     * 查找最小值, 非递归写法.
     *
     * @return 最小值
     * @throws RuntimeException 树中没元素时, 查找失败
     */
    public E minimum() {
        if (getSize() == 0) {
            throw new RuntimeException("FindMin failed, empty tree now.");
        }
        Node cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur.e;
    }

    /**
     * 查找最大值, 递归写法.
     *
     * @return 最大值
     * @throws RuntimeException 树中没元素时, 查找失败
     */
    public E maximumR() {
        if (getSize() == 0) {
            throw new RuntimeException("FindMax failed, empty tree now.");
        }
        return maximumR(root).e;
    }

    /**
     * 递归函数, 在以node为根的树中尝试查找最大值.
     *
     * @param node 以node为根的树
     * @return 最大值节点
     */
    private Node maximumR(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximumR(node.right);
    }

    /**
     * 查找最大值, 非递归写法.
     *
     * @return 最大值
     * @throws RuntimeException 树中没元素时, 查找失败
     */
    public E maximum() {
        if (getSize() == 0) {
            throw new RuntimeException("FindMax failed, empty tree now.");
        }
        Node cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.e;
    }

    /**
     * 删除最小值结点, 递归写法.
     *
     * @return 最小值
     */
    public E removeMinR() {
        E ret = minimumR();
        root = removeMinR(root);
        return ret;
    }

    /**
     * 递归函数, 在以node为根的树中尝试删除最小值.
     *
     * @param node 以node为根的树
     * @return 删除最小值之后返回的新树
     */
    private Node removeMinR(Node node) {
        // 如果没有左子树了, 则当前节点为要删除的节点
        if (node.left == null) {
            Node rightNode = node.right;    // 保存该节点的右子树
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMinR(node.left);
        return node;
    }

    /**
     * 删除最小值结点, 非递归写法.
     *
     * @return 最小值
     */
    public E removeMin() {
        E ret = minimum();
        // root没有左子树, 则root为最小的节点
        if (root.left == null) {
            root = root.right;
            size --;
            return ret;
        }
        Node pre = root;
        Node cur = root.left;
        // 找到待删除节点的上一个节点
        while (cur.left != null) {
            pre = cur;
            cur = cur.left;
        }
        pre.left = cur.right;
        cur.right = null;
        size --;
        return ret;
    }

    /**
     * 删除最大值结点, 递归写法.
     *
     * @return 最大值
     */
    public E removeMaxR() {
        E ret = maximumR();
        root = removeMaxR(root);
        return ret;
    }

    /**
     * 递归函数, 在以node为根的树中尝试删除最大值.
     *
     * @param node 以node为根的树
     * @return 删除最大值之后返回的新树
     */
    private Node removeMaxR(Node node) {
        // 当前节点没有右子树了
        if (node.right == null) {
            Node leftNode = node.left;  // 保存该节点的左子树
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMaxR(node.right);
        return node;
    }

    /**
     * 删除最大值结点, 非递归写法.
     *
     * @return 最大值
     */
    public E removeMax() {
        E ret = maximum();
        // 如果root没有右子树, 则root为最大的节点
        if (root.right == null) {
            root = root.left;
            size --;
            return ret;
        }
        Node pre = root;
        Node cur = root.right;
        // 找到待删除节点的上一个节点
        while (cur.right != null) {
            pre = cur;
            cur = cur.right;
        }
        pre.right = cur.left;
        cur.left = null;
        size --;
        return ret;
    }

    /**
     * 删除任意一个元素.
     *
     * @param e 待删除元素
     * @throws RuntimeException 树为空时, 删除失败
     */
    public void remove(E e) {
        if (getSize() == 0) {
            throw new RuntimeException("Remove failed, empty tree now.");
        }
        root = remove(root, e);
    }

    /**
     * 在以node为根的树中尝试删除e.
     *
     * @param node 以node为根的树
     * @param e 待删除元素
     * @return 删除e元素, 返回的新树
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {  // 待删除节点比当前节点小
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {   // 待删除节点比当前节点大
            node.right = remove(node.right, e);
            return node;
        } else {    // 当前节点为要删除的节点
            // 如果该节点右子树为空
            if (node.right == null) {
                Node leftNode = node.left;  // 保存节点的左子树
                node.left = null;
                size --;
                return leftNode;
            }
            // 如果该节点的左子树为空
            if (node.left == null) {
                Node rightNode = node.right;    // 保存节点的右子树
                node.right = null;
                size --;
                return rightNode;
            }
            // 如果左右子树都存在的话
            // 找到比待删除节点大的最小节点, 或者比这个节点小的最大节点, 用这个节点顶替待该节点
            Node successor = minimumR(node.right);  // 获得该节点的后继
            successor.right = removeMinR(node.right);   // 在右子树中删除后继节点
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    /**
     * 获取指定元素的前驱节点值, 该元素不一定是树中存在的元素, 递归.
     *
     * @param e 待查找元素
     * @return e的前驱节点的值
     * @throws RuntimeException 树为空时, 获取前驱失败
     */
    public E floor(E e) {
        if (getSize() == 0) {
            throw new RuntimeException("Floor failed, empty tree now.");
        }
        // 若e比树中最小元素还小, 则不存在前驱节点
        if (e.compareTo(minimumR()) < 0) {
            return null;
        }
        return floor(root, e).e;
    }

    /**
     * 在node为根的树中尝试找到前驱节点.
     *
     * @param node 以node为根的节点
     * @param e 待查找元素
     * @return e的前驱节点
     */
    private Node floor(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            return floor(node.left, e);
        }
        if (e.compareTo(node.e) == 0) { // 如果树中有该元素, 则该node就是e的前驱
            return node;
        }
        // e > node.e 时, node可能为前驱节点, 还需向右找是否存在比node更大的前驱
        Node temp = floor(node.right, e);
        if (temp != null) {
            return temp;
        }
        return node;
    }

    /**
     * 获取指定元素的后继节点值, 该元素不一定是树中存在的元素, 递归.
     *
     * @param e 待查找元素
     * @return e的后继节点的值
     * @throws RuntimeException 树为空时, 获取后继失败
     */
    public E ceil(E e) {
        if (getSize() == 0) {
            throw new RuntimeException("Ceil failed, empty tree now.");
        }
        // 若e比树中最大元素还大，则不存在后继节点
        if (e.compareTo(maximumR()) > 0) {
            return null;
        }
        return ceil(root, e).e;
    }

    /**
     * 在node为根的树中尝试找到后继节点.
     *
     * @param node 以node为根的节点
     * @param e 待查找元素
     * @return e的后继节点
     */
    private Node ceil(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) > 0) {
            return ceil(node.right, e);
        }
        if (e.compareTo(node.e) == 0) {     // 如果树中有该元素, 则该node就是e的后继
            return node;
        }
        // e < node.e 时, node可能为后继节点, 还需向左找是否存在比node更小的后继
        Node temp = ceil(node.left, e);
        if (temp != null) {
            return temp;
        }
        return node;
    }

    /**
     * 覆写Object类的toString方法.
     *
     * @return 包含树信息的字符串
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    /**
     * 生成以node为根节点，深度为depth的描述字符串.
     *
     * @param node 以node为头的树
     * @param depth 深度
     * @param res 字符串构造器
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    /**
     * 构造深度字符串.
     *
     * @param depth 深度
     * @return 描述深度的字符串
     */
    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i ++) {
            res.append("--");
        }
        return res.toString();
    }
}