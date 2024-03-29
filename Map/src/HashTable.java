import java.util.TreeMap;

public class HashTable<K, V> {
    private static final int upperTol = 10; // 每个地址存放的元素上界
    private static final int lowerTol = 2;  // 每个地址存放的元素下界
    private final int[] capacity = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    private int capacityIndex = 0;
    private TreeMap<K ,V>[] hashTable;
    private int size;
    private int M;

    public HashTable() {
        this.M = capacity[capacityIndex];
        this.size = 0;
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; i ++) {
            hashTable[i] = new TreeMap<>();
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * 添加元素.
     *
     * @param key 键
     * @param value 值
     */
    public void add(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size ++;
            if (size >= upperTol * M && capacityIndex + 1 < capacity.length) {
                capacityIndex ++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    /**
     * 移除key指向的值.
     *
     * @param key 键
     * @return 返回key所指的值
     */
    public V remove(K key) {
        TreeMap<K, V> map = hashTable[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size --;
            if (size < lowerTol * M && capacityIndex - 1 >= 0) {
                capacityIndex --;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    /**
     * 设置key所指的值.
     *
     * @param key 键
     * @param value 新值
     * @throws IllegalArgumentException 不存在key
     */
    public void set(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " doesn't exist.");
        }
        map.put(key, value);
    }

    /**
     * 获取key所指的值.
     *
     * @param key 键
     * @return 返回key指的值
     */
    public V get(K key) {
        return hashTable[hash(key)].get(key);
    }

    /**
     * 查看是否包含key所指的值.
     *
     * @param key 键
     * @return 若存在返回true, 否则返回false
     */
    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);
    }

    /**
     * 哈希表扩容/缩容.
     *
     * @param newM 新容量
     */
    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i ++) {
            newHashTable[i] = new TreeMap<>();
        }
        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i ++) {
            TreeMap<K, V> map = hashTable[i];
            for (K key : map.keySet()) {
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }
        this.hashTable = newHashTable;
    }
}