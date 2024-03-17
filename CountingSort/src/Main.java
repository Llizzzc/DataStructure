import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // 稳定性分析
       int n = 26 * 26 * 26 * 26;
       Student[] students = new Student[n];
       int k = 0;
       Random rnd = new Random();
       for (char c1 = 'a'; c1 <= 'z'; c1 ++) {
           for (char c2 = 'a'; c2 <= 'z'; c2 ++) {
               for (char c3 = 'a'; c3 <= 'z'; c3 ++) {
                   for (char c4 = 'a'; c4 <= 'z'; c4 ++) {
                       students[k] = new Student("" + c1 + c2 + c3 + c4, rnd.nextInt(101));
                       k ++;
                   }
               }
           }
       }
       int R = 101;
       int[] cnt = new int[R];
       // O(n)
       for (Student student : students) {
           cnt[student.getScore()] ++;
       }

       int[] index = new int[R + 1];
       // O(R)
       for (int i = 0; i < R; i ++) {
           index[i + 1] = index[i] + cnt[i];
       }

       Student[] temp = new Student[n];
       // O(n)
       for (Student student : students) {
           temp[index[student.getScore()]] = student;
           index[student.getScore()] ++;
       }
       // O(n)
       for (int i = 0; i < n; i ++) {
           students[i] = temp[i];
       }

       // 验证
        for (int i = 1; i < n; i ++) {
            if (students[i - 1].getScore() > students[i].getScore()) {
                throw new RuntimeException("Sort failed.");
            }
            if (students[i - 1].getScore() == students[i].getScore()) {
                if (students[i - 1].getName().compareTo(students[i].getName()) > 0) {
                    throw new RuntimeException("None stable Sort.");
                }
            }
        }

        int[] arr = {0, 1, 2, 3, 2, 2, 1, 1, 3, 0, 0, 1, 2, 3, 0};
        CountingSort.countingSort(arr, 4);
        System.out.println(Arrays.toString(arr));
    }
}