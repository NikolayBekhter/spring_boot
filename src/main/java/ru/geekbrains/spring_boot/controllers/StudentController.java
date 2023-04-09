package ru.geekbrains.spring_boot.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring_boot.api.AppError;
import ru.geekbrains.spring_boot.api.StudentDto;
import ru.geekbrains.spring_boot.converters.StudentConverter;
import ru.geekbrains.spring_boot.services.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
@Tag(name = "Студенты", description = "Методы работы с студентами")
public class StudentController {
    private final StudentService service;
    private final StudentConverter converter;

    @Operation(
            summary = "Запрос на создание нового студента",
            responses = {
                    @ApiResponse(
                            description = "Студент успешно создан", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = StudentDto.class))
                    )
            }
    )
    @PostMapping
    public StudentDto saveOrUpdate(@RequestBody StudentDto studentDto) {
        return converter.entityToDto(service.saveOrUpdate(studentDto));
    }

    @Operation(
            summary = "Запрос на получение студента по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StudentDto.class))
                    ),
                    @ApiResponse(
                            description = "Студент не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public StudentDto findById(@PathVariable @Parameter(description = "Идентификатор студента", required = true) Long id) {
        return converter.entityToDto(service.findById(id));
    }

    @Operation(
            summary = "Запрос на получение списка всех студентов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StudentDto.class))
                    )
            }
    )
    @GetMapping
    public List<StudentDto> findAll() {
        return service.findAll()
                .stream()
                .map(converter::entityToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping
    public void delete(Long id) {
        service.delete(id);
    }
}
