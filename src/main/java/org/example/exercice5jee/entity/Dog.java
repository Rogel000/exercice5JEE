package org.example.exercice5jee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String breed;
    private LocalDate birthDate;


    public Dog(String name, String breed, LocalDate birthDate) {
        this.name = name;
        this.breed = breed;
        this.birthDate = birthDate;
    }
}
