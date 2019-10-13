package org.qbit.messanger.follower.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowerDto {

    @NotBlank
    @Size(min = 1, max = 50)
    private String ownerId;

    @NotBlank
    @Size(min = 1, max = 50)
    private String observedUserId;
}
