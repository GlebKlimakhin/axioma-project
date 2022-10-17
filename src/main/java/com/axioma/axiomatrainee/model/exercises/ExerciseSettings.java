package com.axioma.axiomatrainee.model.exercises;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "exercise_settings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExerciseSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(value = EnumType.STRING)
    private OperationsType operationsType;
    private int digitsAmount;
    private int rowsAmount;
    private int digitsLimit;
}
