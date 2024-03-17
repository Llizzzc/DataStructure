/**
 * 用于测试的学生类.
 *
 * @author lzc
 * @version 3.0
 * @version jdk17
 */
public class Student {
    private String name;
    private int number;

    /**
     * 包含name, number的构造方法.
     *
     * @param name 姓名
     * @param number 学号
     */
    public Student(String name, int number) {
        this.name = name;
        this.number = number;
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
     * 覆写Object类的toString方法.
     *
     * @return 包含学生信息的字符串
     */
    @Override
    public String toString() {
        return String.format("Student(name: %s, number: %d)", name, number);
    }
}