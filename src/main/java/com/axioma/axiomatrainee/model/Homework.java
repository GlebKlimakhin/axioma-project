package com.axioma.axiomatrainee.model;

import com.axioma.axiomatrainee.model.exercises.Exercise;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "homeworks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "description")
    String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name ="homeworks_exercises",
            joinColumns = @JoinColumn(name = "homework_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    Set<Exercise> exercises;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "groups_homeworks",
            joinColumns = @JoinColumn(name = "homework_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    @JsonIgnore
      Set<Group> groups;

    @CreationTimestamp
    @Column(name = "creation_date")
    Date creationDate;

    @Column(name = "expiration_date")
    Date expirationDate;
}
