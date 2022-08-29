package com.axioma.axiomatrainee.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRatingRequestDto {

    @NotNull
    private int ratingIncNumber;
    @NotNull
    private Long userId;
}
