package com.axioma.axiomatrainee.requestdto;

import com.axioma.axiomatrainee.model.user.Role;
import com.axioma.axiomatrainee.model.user.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {
    String username;

    String password;

    String firstname;

    String lastname;

    Status status;

    String email;

    Role role;


}
