package com.netcracker;

import com.netcracker.domain.Faculty;
import com.netcracker.domain.Student;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Serialization {

    private static File file = Paths.get(".", "src", "main", "resources", "serialization.ser").toFile();;

    public static void main(String[] args) {
        Faculty csit = new Faculty("CSIT");
        Faculty biology = new Faculty("biology");
        Student firstStudent = new Student("Ваня", 20, csit);
        Student secondStudent = new Student("Андрей", 21, biology);
        try (
//                ObjectOutputStream outputStream =
//                        new ObjectOutputStream(
//                                new FileOutputStream(file));
                MyObjectInputStream<Student> inputStream =
                        new MyObjectInputStream<>(
                                new FileInputStream(file)
                        )
        ) {
//            outputStream.writeObject(firstStudent);
//            outputStream.writeObject(csit);
//            outputStream.writeObject(secondStudent);

//            Student student1 = (Student) inputStream.readObject();
//            Student student2 = (Student) inputStream.readObject();
//            System.out.println(student1);
//            System.out.println(student2);

            List<Student> studentsWithMyRealization = getStudentsWithMyRealization(inputStream);
            studentsWithMyRealization.forEach(System.out::println);


//            List<Student> studentsFromStream = getStudentsFromStream(inputStream);

//            studentsFromStream.forEach(System.out::println);
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }

    public static List<Student> getStudentsFromStream(ObjectInputStream inputStream) {
        List<Student> students = new ArrayList<>();

        while(true) {
            try {
                Student student = (Student) inputStream.readObject();
                students.add(student);
            } catch (IOException ioException) {
                return students;
            } catch (ClassCastException classCastException) {
                continue;
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Student> getStudentsWithMyRealization(MyObjectInputStream<Student> inputStream) throws ClassNotFoundException {
        List<Student> students = new ArrayList<>();

        MyObjectInputStream.Result<Student> result = null;
        while ((result = inputStream.next()).isHasNext()) {
            Student value = result.getValue();
            students.add(value);
        }
        return students;
    }
}
