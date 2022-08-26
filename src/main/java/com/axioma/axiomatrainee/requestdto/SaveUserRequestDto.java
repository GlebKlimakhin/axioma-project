package com.axioma.axiomatrainee.requestdto;

import com.axioma.axiomatrainee.model.Group;
import com.axioma.axiomatrainee.model.user.Role;
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
    String firstname;
    @NotBlank
    String lastname;
    @NotBlank
    String password;
    @NotNull
    Set<Group> groups;
    @NotNull
    Role role;
}
