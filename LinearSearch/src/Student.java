public class Student {
    private String name;
    private Integer number;

    public Student(String name, Integer number) {
        this.name = name;
        this.number = number;
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
    public String toString() {
        return String.format("Student(name: %s, number: %d)", name, number);
    }
}