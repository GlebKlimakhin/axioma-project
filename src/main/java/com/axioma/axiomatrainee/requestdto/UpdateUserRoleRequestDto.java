package com.axioma.axiomatrainee.requestdto;

import com.axioma.axiomatrainee.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateUserRoleRequestDto {
    @NotNull
    private Long userId;
    @NotNull
    private Role role;
}
