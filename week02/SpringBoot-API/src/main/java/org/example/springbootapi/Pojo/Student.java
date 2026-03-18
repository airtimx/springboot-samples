package org.example.springbootapi.Pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private String name;
    private Gender gender;
    private int age;
    private Long id;
    private Phone phone;
    private LocalDate birthday;
}
