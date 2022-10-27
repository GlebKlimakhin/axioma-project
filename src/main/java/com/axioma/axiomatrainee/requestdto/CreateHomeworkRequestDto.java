package com.axioma.axiomatrainee.requestdto;

import com.axioma.axiomatrainee.model.homeworks.RepeatRate;
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

    @NotNull
    String title;

    @NotBlank
    String description;

    long unixExpirationDate;

    @NotNull
    Long groupId;

    boolean isRepeatable;

    RepeatRate repeatRate;

    int daysToRepeat;

}
