public class Student implements Comparable<Student> {
    private String name;
    private Integer number;
    private Integer score;

    public Student(String name, int number, int score) {
        this.name = name;
        this.number = number;
        this.score = score;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (object instanceof Student s) {
            return this.number.equals(s.number) && this.name.equals(s.name);
        }
        return false;
    }

    @Override
    public int compareTo(Student student) {
        return this.score - student.score;
    }

    @Override
    public String toString() {
        return String.format("Student(name: %s, number: %d, score: %d)", name, number, score);
    }
}