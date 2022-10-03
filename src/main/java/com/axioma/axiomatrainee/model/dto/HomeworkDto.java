package com.axioma.axiomatrainee.model.dto;

import com.axioma.axiomatrainee.model.Group;
import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class HomeworkDto {

    Long id;
    String title;
    String description;
    Set<Exercise> exercises;
    Group group;
    Long creationDate;
    Long expirationDate;
}
