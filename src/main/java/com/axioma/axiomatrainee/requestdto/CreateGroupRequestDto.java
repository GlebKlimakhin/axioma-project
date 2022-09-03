package com.axioma.axiomatrainee.requestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateGroupRequestDto {

    @NotBlank
    @JsonProperty
    String name;

    @JsonProperty
    List<Long> userIds;
}
