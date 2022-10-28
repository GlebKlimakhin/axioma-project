package com.axioma.axiomatrainee.model.homeworks;

import com.axioma.axiomatrainee.model.Group;
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
@NamedEntityGraph(name = "Homeworks.exercises",
        attributeNodes = @NamedAttributeNode("exercises")
)
@Builder
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name ="homeworks_exercises",
            joinColumns = @JoinColumn(name = "homework_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    Set<Exercise> exercises;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "group_id", nullable = false)
    Group group;

    @CreationTimestamp
    @Column(name = "creation_date")
    Date creationDate;

    @Column(name = "expiration_date")
    Date expirationDate;

    @Column(name = "repeat_rate")
    @Enumerated(EnumType.STRING)
    RepeatRate repeatRate;

    @Column(name = "days_to_repeat")
    int daysToRepeat;
}