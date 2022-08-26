package com.axioma.axiomatrainee.model;

import com.axioma.axiomatrainee.model.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity(name = "group")
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    @NotBlank
    String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "groups_homeworks",
    joinColumns = @JoinColumn(name = "group_id"),
    inverseJoinColumns = @JoinColumn(name = "homework_id"))
    Set<Homework> homeworks;

    @ManyToMany
    @JoinTable(name = "users_groups",
    joinColumns = @JoinColumn(name = "group_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<User> users;
}
