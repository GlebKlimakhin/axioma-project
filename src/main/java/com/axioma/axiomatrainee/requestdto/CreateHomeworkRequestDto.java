package com.axioma.axiomatrainee.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateHomeworkRequestDto {

    @NotNull
    List<Long> exercisesIds;

    @NotBlank
    String description;

    @NotNull
    long unixExpirationDate;

    @NotNull
    Iterable<Long> groupIds;
}
