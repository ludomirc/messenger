package org.qbit.messanger.post.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    @NotBlank
    @Size(min = 1, max = 50)
    private String userId;
}
