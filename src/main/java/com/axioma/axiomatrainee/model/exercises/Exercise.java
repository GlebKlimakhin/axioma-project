package com.axioma.axiomatrainee.model.exercises;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "exercises")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    @NotBlank
    String name;

    @Column(name = "data")
    @NotBlank
    String data;

    @Column(name = "difficulty")
    @NotNull
    Integer difficulty;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NotBlank
    ExerciseType exerciseType;

    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt;

    @OneToMany(mappedBy = "exercise", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Question> questions;

    //todo scopes
    //todo interval for 5, 4, 3, not_done
}