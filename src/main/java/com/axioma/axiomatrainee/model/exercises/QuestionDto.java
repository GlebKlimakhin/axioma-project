package com.axioma.axiomatrainee.model.exercises;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionDto {
    String question;
    Set<String> answers;
    String rightAnswer;
}
