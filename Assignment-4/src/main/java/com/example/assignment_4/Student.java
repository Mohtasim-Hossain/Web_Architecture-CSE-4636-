package com.example.assignment_4;

import jakarta.persistence.*;


@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentId")
    private int studentId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name")
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semester")
    private int semester;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cgpa")
    private float cgpa;

    public Student() {

    }

    public Student(int studentId, String name, int semester, float cgpa) {
        this.studentId = studentId;
        this.name = name;
        this.semester = semester;
        this.cgpa = cgpa;
    }


    // getters and setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

}

