package com.axioma.axiomatrainee.model.user;

import com.axioma.axiomatrainee.model.Group;
import com.axioma.axiomatrainee.utill.ValidEmail;
import com.axioma.axiomatrainee.utill.ValidPassword;
import com.axioma.axiomatrainee.utill.ValidUsername;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties("groups")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "username")
    @ValidUsername
    String username;

    @Column(name = "password")
    @ValidPassword
    @JsonIgnore
    String password;

    @Column(name = "firstname")
    @Size(min = 3, max = 12, message = "firstname cannot be less than 3, and more than 12 characters")
    @NotBlank
    String firstname;

    @Column(name = "lastname")
    @Size(min = 3, max = 20, message = "lastname cannot be less than 3, and more than 12 characters")
    @NotBlank
    String lastname;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotBlank
    Status status;

    @Column(name = "email")
    @ValidEmail
    String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "users_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
            @JsonManagedReference
    Set<Group> groups;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @NotNull
    @NotBlank
    Role role;

    @Column(name = "rating")
    @NotNull
    private Integer rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}