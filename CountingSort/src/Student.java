/**
 * 用于测试的学生类.
 *
 * @author lzc
 * @version 4.0
 * @version jdk17
 */
public class Student {
    private String name;
    private int score;

    /**
     * 包含name, score的构造方法.
     *
     * @param name 姓名
     * @param score 分数
     */
    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * 获取学生姓名.
     *
     * @return 学生姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 获取学生分数.
     *
     * @return 学生分数
     */
    public int getScore() {
        return score;
    }

    /**
     * 覆写Object类的toString方法.
     *
     * @return 包含学生信息的字符串
     */
    @Override
    public String toString() {
        return String.format("Student(name: %s, score: %d)", name, score);
    }
}