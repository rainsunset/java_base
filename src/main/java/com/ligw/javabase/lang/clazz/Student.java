package com.ligw.javabase.lang.clazz;


public class Student extends YangPerson {
    private String grade;

    public String school;

    public static void main(String[] args) {
        System.out.println("in Student main");
    }

    public Student() {
        System.out.println("new Student by no arges Constructor");
    }

    public Student(String grade, String school) {
        System.out.println("new Student by full arges Constructor");
        this.grade = grade;
        this.school = school;
    }

    private Student(String grade) {
        System.out.println("new Student by sinagle arges Constructor");
        this.grade = grade;
        this.school = "野鸡大学";
    }

    public String getGrade() {
        System.out.println("in public methord getGrade");
        return grade;
    }

    private void setGrade(String grade) {
        System.out.println("in private methord getGrade");
        this.grade = grade;
    }
}
