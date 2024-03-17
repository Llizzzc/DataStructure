/**
 * 用于测试的学生类.
 *
 * @author lzc
 * @version 2.0
 * @version jdk17
 */
public class Student implements Comparable<Student> {
    private String name;
    private int number;
    private int score;

    /**
     * 包含name, number, score的构造方法.
     *
     * @param name 姓名
     * @param number 学号
     * @param score 分数
     */
    public Student(String name, int number, int score) {
        this.name = name;
        this.number = number;
        this.score = score;
    }

    /**
     * 覆写Object类的equals方法.
     *
     * @param object 待比较的对象
     * @return 相等为true, 否则为false
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;   // 是否比较的是同一个类实例
        if (object == null) return false;  // 传入对象是否为空
        if (this.getClass() != object.getClass()) return false;   // 是否属于同一个类
        Student student = (Student) object;
        return this.number == student.number && this.name.equals(student.name);
    }

    /**
     * 覆写Comparable接口的compareTo方法.
     *
     * @param student 要比较的对象
     * @return 大于0说明比要比较的对象大, 小于0说明比要比较的对象小, 等于0说明相等
     */
    @Override
    public int compareTo(Student student) {
        return this.score - student.score;
    }

    /**
     * 覆写Object类的toString方法.
     *
     * @return 包含学生信息的字符串
     */
    @Override
    public String toString() {
        return String.format("Student(name: %s, number: %d, score: %d)", name, number, score);
    }
}