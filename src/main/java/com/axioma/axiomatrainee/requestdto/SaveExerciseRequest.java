package com.axioma.axiomatrainee.requestdto;

import com.axioma.axiomatrainee.model.exercises.ExerciseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaveExerciseRequest {

    @NotBlank
    String data;

    @NotNull
    ExerciseType type;
}
