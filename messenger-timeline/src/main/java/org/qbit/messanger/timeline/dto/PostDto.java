package org.qbit.messanger.timeline.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class PostDto implements Serializable {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String userId;

    @NotBlank
    @Size(min = 1, max = 140)
    private String body;
}
