package org.qbit.messanger.userservice.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    @NotBlank
    @Size(min = 1, max = 50)
    String userId;
}