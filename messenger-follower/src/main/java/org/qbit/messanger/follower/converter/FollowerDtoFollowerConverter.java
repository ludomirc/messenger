package org.qbit.messanger.follower.converter;

import org.qbit.messanger.Converter;
import org.qbit.messanger.follower.dto.FollowerDto;
import org.qbit.messanger.follower.model.Follower;
import org.springframework.stereotype.Component;

@Component
public class FollowerDtoFollowerConverter implements Converter<FollowerDto, Follower> {
    @Override
    public Follower convert(FollowerDto source) {
        Follower target = new Follower();
        target.setOwnerId(source.getOwnerId());
        target.setObservedUserId(source.getObservedUserId());
        return target;
    }
}
