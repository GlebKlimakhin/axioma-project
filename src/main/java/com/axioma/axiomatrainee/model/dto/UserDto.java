package com.axioma.axiomatrainee.model.dto;

import com.axioma.axiomatrainee.model.Group;
import com.axioma.axiomatrainee.model.user.Role;
import com.axioma.axiomatrainee.model.user.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;

    String username;

    String password;

    String firstname;

    String lastname;

    Status status;

    String email;

    List<Group> groups;

    Role role;
}
