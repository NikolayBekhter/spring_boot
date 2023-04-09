package ru.geekbrains.spring_boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring_boot.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
