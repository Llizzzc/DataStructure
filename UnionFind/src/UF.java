/**
 * 并查集接口.
 * <p>包括查询, 连接等操作.</p>
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public interface UF {
    /**
     * 查询并查集大小.
     *
     * @return 并查集大小
     */
    int getSize();

    /**
     * 判断两个元素是否属于一个集合.
     *
     * @param p 元素p
     * @param q 元素q
     * @return 属于一个集合为true, 否则false
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