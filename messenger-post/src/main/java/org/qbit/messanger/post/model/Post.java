package org.qbit.messanger.post.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "post")
@Getter
@Setter
@ToString
public class Post extends AbstractPersistable<Long> {

    @Size(min = 1, max = 50)
    @Column(name = "user_id", length = 50, nullable = false)
    private String userId;

    @Size(min = 1, max = 140)
    @Column(name = "body", length = 140, nullable = false)
    private String body;

    public void setId(Long id) {
        super.setId(id);
    }
}
