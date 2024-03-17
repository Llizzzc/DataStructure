/**
 * 字符串匹配.
 *
 * @author lzc
 * @version 1.0
 * @version jdk17
 */
public class SubstringMatch {
    /**
     * 私有构造方法, 不允许创建实例.
     */
    private SubstringMatch() {}

    /**
     * 暴力匹配.
     *
     * @param s 给定主串
     * @param t 目标子串
     * @return 如果找到则返回目标子串在主串的索引, 否则返回-1
     */
    public static int bruteforce(String s, String t) {
        if (s.length() < t.length()) {
            return -1;
        }
        // 对主串逐个字符开始进行匹配, s[i, i + t.length() - 1] == t?
        for (int i = 0; i + t.length() - 1 < s.length(); i ++) {
            int j = 0;
            for ( ; j < t.length(); j ++) {
                if (s.charAt(i + j) != t.charAt(j)) {
                    break;
                }
            }
            if (j == t.length()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 滚动哈希.
     *
     * @param s 给定主串
     * @param t 目标子串
     * @return 如果找到则返回目标子串在主串的索引, 否则返回-1
     */
    public static int rabinKarp(String s, String t) {
        if (s.length() < t.length()) {
            return -1;
        }
        long MOD = (long) 1e9 + 7, tHash = 0, B = 256;
        for (int i = 0; i < t.length(); i ++) {
            tHash = (tHash * B + t.charAt(i)) % MOD;
        }

        long hash = 0, P = 1;
        for (int i = 0; i < t.length() - 1; i ++) {
            hash = (hash * B + s.charAt(i)) % MOD;
        }

        for (int i = 0; i < t.length() - 1; i ++) {
            P = (P * B) % MOD;
        }

        for (int i = t.length() - 1; i < s.length(); i ++) {
            hash = (hash * B + s.charAt(i)) % MOD;
            if (hash == tHash && equal(s, i - t.length() + 1, t.length(), t)) {
                return i - t.length() + 1;
            }
            hash = (hash - (s.charAt(i - t.length() + 1) * P) % MOD + MOD) % MOD;
        }
        return -1;
    }

    /**
     * kmp算法.
     *
     * @param s 给定主串
     * @param t 目标子串
     * @return 如果找到则返回目标目标子串在主串的索引, 否则返回-1
     */
    public static int kmp(String s, String t) {
        if (s.length() < t.length()) {
            return -1;
        }
        int[] lsp = getLSP(t);
        int i = 0, j = 0;
        while (i < s.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i ++;
                j ++;
                if (j == t.length()) {
                    return i - t.length();
                }
            } else if (j > 0) {
                j = lsp[j - 1];
            } else {
                i ++;
            }
        }
        return -1;
    }

    /**
     * 比较s[left, left + len - 1] == t?
     *
     * @param s 给定主串
     * @param left 要比较的s主串的起始位置
     * @param len 目标子串长度
     * @param t 目标子串
     * @return 相等返回true, 否则false
     */
    private static boolean equal(String s, int left, int len, String t) {
        for (int i = 0; i < len; i ++) {
            if (t.charAt(i) != s.charAt(left + i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取字符串t的各个子串的最长的既是前缀也是后缀的字符串.
     *
     * @param t 目标子串
     * @return border数组
     */
    private static int[] getLSP(String t) {
        int[] lsp = new int[t.length()];
        for (int i = 1; i < t.length(); i ++) {
            int a = lsp[i - 1];
            while (a > 0 && t.charAt(a) != t.charAt(i)) {
                a = lsp[a - 1];
            }
            if (t.charAt(i) == t.charAt(a)) {
                lsp[i] = a + 1;
            }
        }
        return lsp;
    }
}