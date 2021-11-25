package com.netcracker.domain;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;
    private Faculty faculty;
    private String hello;

    private static final long serialVersionUID = -3708255418883911216L;

    public Student(String name, int age, Faculty faculty) {
        this.name = name;
        this.age = age;
        this.faculty = faculty;
    }

    public Student() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", faculty=" + faculty +
                '}';
    }
}
