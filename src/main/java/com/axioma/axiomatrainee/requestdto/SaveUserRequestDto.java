package com.axioma.axiomatrainee.requestdto;

import com.axioma.axiomatrainee.model.Group;
import com.axioma.axiomatrainee.model.user.Role;
import com.axioma.axiomatrainee.utill.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserRequestDto {

    @NotBlank
    String username;
    @NotBlank
    String password;
    @NotBlank
    String firstname;
    @NotBlank
    String lastname;
    @ValidEmail
    String email;
    Set<Group> groups;
    @NotNull
    Role role;
}
