package model;

public class Student extends Person {

    private String course;
    private int semester;
    private double cgpa;
    private double familyIncome;

    public Student(
            String id,
            String name,
            String course,
            int semester,
            double cgpa,
            double familyIncome) {

        super(id, name);
        this.course = course;
        this.semester = semester;
        this.cgpa = cgpa;
        this.familyIncome = familyIncome;
    }

    public String getCourse() {
        return course;
    }

    public int getSemester() {
        return semester;
    }

    public double getCgpa() {
        return cgpa;
    }

    public double getFamilyIncome() {
        return familyIncome;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}