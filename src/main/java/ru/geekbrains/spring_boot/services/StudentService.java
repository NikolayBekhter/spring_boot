package ru.geekbrains.spring_boot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring_boot.api.*;
import ru.geekbrains.spring_boot.converters.StudentConverter;
import ru.geekbrains.spring_boot.entities.Student;
import ru.geekbrains.spring_boot.repositories.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;
    private final StudentConverter converter;

    public Student saveOrUpdate(StudentDto studentDto) {
        if (studentDto.getId() != null) {
            Student studentFromDb = repository.findById(studentDto.getId())
                    .orElseThrow(()-> new ResourceNotFoundException("Студент с id: " + studentDto.getId() + " не найден!"));
            if (studentDto.getName() != null) {
                studentFromDb.setName(studentDto.getName());
            }
            if (studentDto.getAge() != 0) {
                studentFromDb.setAge(studentDto.getAge());
            }
            return repository.save(studentFromDb);
        } else {
            return repository.save(converter.dtoToEntity(studentDto));
        }
    }

    public Student findById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Студент с id: " + id + " не найден!"));
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
