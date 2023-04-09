package ru.geekbrains.spring_boot.converters;

import org.springframework.stereotype.Service;
import ru.geekbrains.spring_boot.api.StudentDto;
import ru.geekbrains.spring_boot.entities.Student;

@Service
public class StudentConverter {
    public StudentDto entityToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setAge(student.getAge());
        return studentDto;
    }

    public Student dtoToEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        return student;
    }
}
