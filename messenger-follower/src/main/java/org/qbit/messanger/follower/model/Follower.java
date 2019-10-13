package org.qbit.messanger.follower.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "follower",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"owner_Id", "observed_user_id"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Follower extends AbstractPersistable<Long> {

    @Size(min = 1, max = 50)
    @Column(name = "owner_Id", length = 50, nullable = false)
    private String ownerId;

    @Size(min = 1, max = 50)
    @Column(name = "observed_user_id", length = 50, nullable = false)
    private String observedUserId;
}
