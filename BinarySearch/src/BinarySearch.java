public class BinarySearch {

    private BinarySearch() {}

    /**
     * 递归实现.
     *
     * @param data 目标数组
     * @param target 待查找元素
     * @return 返回目标元素索引, 若不存在则返回-1
     */
    public static <E extends Comparable<E>> int searchR(E[] data, E target) {
        return searchR(data, 0, data.length - 1, target);
    }

    /**
     * 递归函数, 在[l, r]中尝试寻找target.
     *
     * @param data 目标数组
     * @param l 左边界
     * @param r 右边界
     * @param target 待查找元素
     * @return 返回目标元素索引, 若不存在则返回-1
     */
    private static <E extends Comparable<E>> int searchR(E[] data, int l, int r, E target) {
        if (l > r) return -1;
        int mid = l + (r - l) / 2;
        if (data[mid].compareTo(target) == 0) {
            return mid;
        }
        if (data[mid].compareTo(target) < 0) {
            return searchR(data, mid + 1, r, target);
        }
        return searchR(data, l, mid - 1, target);
    }

    /**
     * 非递归实现.
     *
     * @param data 目标数组
     * @param target 待查找元素
     * @return 返回目标元素索引, 若不存在则返回-1
     */
    public static <E extends Comparable<E>> int search(E[] data, E target) {
        int l = 0, r = data.length - 1;
        // 在data[l, r]中查找target
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) == 0) return mid;
            if (data[mid].compareTo(target) < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 使用>=target的最小索引思路实现二分查找.
     *
     * @param data 目标数组
     * @param target 待查找元素
     * @return 返回目标元素索引, 若不存在则返回-1
     */
    public static <E extends Comparable<E>> int search2(E[] data, E target) {
        int l = 0, r = data.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) < 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        if(l < data.length && data[l].compareTo(target) == 0) {
            return l;
        }
        return -1;
    }

    /**
     * 查找大于目标元素的最小索引.
     *
     * @param data 目标数组
     * @param target 待查找元素
     * @return 返回大于target的最小索引, 若未找到则返回data.length
     */
    public static <E extends Comparable<E>> int upper(E[] data, E target) {
        int l = 0, r = data.length;
        // 在data[l, r]中寻找解
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) <= 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    /**
     * 查找大于target的最小索引, 存在target返回最大索引.
     *
     * @param data 目标数组
     * @param target 待查找元素
     * @return 若大于target, 返回最小索引, 若等于target, 返回最大索引
     */
    public static <E extends Comparable<E>> int upperCeil(E[] data, E target) {
        int u  = upper(data, target);
        if (u - 1 >= 0 && data[u - 1].compareTo(target) == 0) {
            return u - 1;
        }
        return u;
    }

    /**
     * 查找大于等于目标元素的最小索引.
     *
     * @param data 目标数组
     * @param target 待查找元素
     * @return 返回大于等于target的最小索引
     */
    public static <E extends Comparable<E>> int lowerCeil(E[] data, E target) {
        int u = upper(data, target);
        if (u - 1 >= 0 && data[u - 1].compareTo(target) == 0) {
            u --;
            while (u >= 0 && data[u].compareTo(target) == 0) {
                u --;
            }
            return u + 1;
        }
        return u;
    }

    /**
     * 查找大于等于目标元素的最小索引.
     *
     * @param data 目标数组
     * @param target 待查找元素
     * @return 返回大于等于target的最小索引
     */
    public static <E extends Comparable<E>> int lowerCeil2(E[] data, E target) {
        int l = 0, r = data.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) < 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    /**
     * 查找小于目标元素的最大的索引.
     *
     * @param data 目标数组
     * @param target 待查找元素
     * @return 返回小于target的最大索引, 未找到则返回-1
     */
    public static <E extends Comparable<E>> int lower(E[] data, E target) {
        int l = -1 ,r = data.length - 1;
        while (l < r) {
            // 当l, r相邻时, 让mid上取整, 解决死循环
            int mid = l + (r - l + 1) / 2;
            if (data[mid].compareTo(target) < 0) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    /**
     * 查找小于target的最大索引, 存在target返回最小索引.
     *
     * @param data 目标数组
     * @param target 待查找元素
     * @return 若小于target, 返回最大索引, 若等于target, 返回最小索引
     */
    public static <E extends Comparable<E>> int lowerFloor(E[] data, E target) {
        int l = lower(data, target);
        if (l + 1 <= data.length - 1 && data[l + 1].compareTo(target) == 0) {
            return l + 1;
        }
        return l;
    }

    /**
     * 查找小于等于target的最大索引.
     *
     * @param data 目标数组
     * @param target 待查找元素
     * @return 返回小于等于target的最大索引
     */
    public static <E extends Comparable<E>> int upperFloor(E[] data, E target) {
        int l = lower(data, target);
        if (l + 1 <= data.length - 1 && data[l + 1].compareTo(target) == 0) {
            l ++;
            while (l <= data.length - 1 && data[l].compareTo(target) == 0) {
                l ++;
            }
            return l - 1;
        }
        return l;
    }
}