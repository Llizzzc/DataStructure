public class Main {
    public static void main(String[] args) {
        // 功能测试
        String s = "hello, dell lee";
        String t = "de";
        SubstringMatchHelper.matchHelper("bruteforce", s, t);
        SubstringMatchHelper.matchHelper("rabinKarp", s, t);
        SubstringMatchHelper.matchHelper("kmp", s, t);
        System.out.println();

        String s2 = FileOperation.readFile("pride-and-prejudice.txt");
        String t2 = "china";
        SubstringMatchHelper.matchHelper("bruteforce", s2, "xxx");
        SubstringMatchHelper.matchHelper("rabinKarp", s2, "xxx");
        SubstringMatchHelper.matchHelper("kmp", s2, "xxx");
        System.out.println();

        SubstringMatchHelper.matchHelper("bruteforce", s2, t2);
        SubstringMatchHelper.matchHelper("rabinKarp", s2, t2);
        SubstringMatchHelper.matchHelper("kmp", s2, t2);
        System.out.println();

        // Worst test
        int n = 1000000, m = 1000;
        StringBuilder s3 =  new StringBuilder();
        for (int i = 0; i < n; i ++) {
            s3.append('a');
        }
        StringBuilder t3 = new StringBuilder();
        for (int i = 0; i < m - 1; i ++) {
            t3.append('a');
        }
        t3.append('b');
        SubstringMatchHelper.matchHelper("bruteforce", s3.toString(), t3.toString());
        SubstringMatchHelper.matchHelper("rabinKarp", s3.toString(), t3.toString());
        SubstringMatchHelper.matchHelper("kmp", s3.toString(), t3.toString());
    }
}