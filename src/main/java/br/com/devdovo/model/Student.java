package br.com.devdovo.model;

import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {

    private int id;
    private String name;
    public static List<Student> studentList;

    static {
        studentListRepository();
    }

    public Student(int id,String name) {
        this(name);
        this.id = id;
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(){

    }

    private static void studentListRepository(){
        studentList = new ArrayList<>(asList(new Student(1,"Deku"),new Student(2,"Todoroki")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
