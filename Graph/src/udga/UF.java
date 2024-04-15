package udga;

public interface UF {

    int getSize();

    /**
     * 判断两个元素是否属于一个集合.
     *
     * @param p 元素p
     * @param q 元素q
     * @return 若属于一个集合返回true, 否则返回false
     */
    boolean isConnected(int p, int q);

    /**
     * 连接两个元素.
     *
     * @param p 元素p
     * @param q 元素q
     */
    void unionElements(int p, int q);
}