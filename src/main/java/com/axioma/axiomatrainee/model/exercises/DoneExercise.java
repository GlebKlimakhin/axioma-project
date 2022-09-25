package com.axioma.axiomatrainee.model.exercises;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "done_exercises")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoneExercise {

    @EmbeddedId
    DoneExerciseId doneExerciseId;

    @Column(name = "score")
    @NotNull
    Double score;

    @Enumerated
    @Column(name = "type")
    @NotBlank
    ExerciseType exerciseType;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "done_at")
    Date doneAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DoneExercise that = (DoneExercise) o;
        return doneExerciseId != null && Objects.equals(doneExerciseId, that.doneExerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doneExerciseId);
    }
}
