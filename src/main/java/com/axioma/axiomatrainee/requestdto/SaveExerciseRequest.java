package com.axioma.axiomatrainee.requestdto;

import com.axioma.axiomatrainee.model.exercises.ExerciseSettings;
import com.axioma.axiomatrainee.model.exercises.ExerciseType;
import com.axioma.axiomatrainee.model.exercises.Question;
import com.axioma.axiomatrainee.model.exercises.QuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaveExerciseRequest {

    @NotBlank
    String data;
    @NotNull
    ExerciseType type;
    @NotBlank
    String name;
    @NotNull
    Integer difficulty;
    Set<QuestionDto> questions;
    ExerciseSettings settings;
}
