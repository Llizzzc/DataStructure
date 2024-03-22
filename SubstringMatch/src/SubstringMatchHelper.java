public class SubstringMatchHelper {

    private SubstringMatchHelper() {}

    /**
     * 测试搜索字串的耗时.
     *
     * @param matchName 匹配算法名称
     * @param s 给定主串
     * @param t 目标字串
     * @throws RuntimeException 匹配的索引不相等
     */
    public static void matchHelper(String matchName, String s, String t) {
        int pos = -1;
        long start = System.nanoTime();
        if (matchName.equals("bruteforce")) {
            pos = SubstringMatch.bruteforce(s, t);
        } else if (matchName.equals("rabinKarp")) {
            pos = SubstringMatch.rabinKarp(s, t);
        } else if (matchName.equals("kmp")) {
            pos = SubstringMatch.kmp(s, t);
        }
        long end = System.nanoTime();
        double time = (end -start) / 1.0e9;
        if (pos != s.indexOf(t)) {
            throw new RuntimeException("Match failed.");
        }
        System.out.printf("%s: res = %d, time = %f s\n", matchName, pos, time);
    }
}