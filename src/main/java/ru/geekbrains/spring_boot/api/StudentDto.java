package ru.geekbrains.spring_boot.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Модель студента")
public class StudentDto {
    @Schema(description = "ID студента", example = "1")
    private Long id;
    @Schema(description = "Имя студента", maxLength = 255, minLength = 2, example = "Иван")
    private String name;
    @Schema(description = "Возраст студента", example = "21")
    private int age;
}
