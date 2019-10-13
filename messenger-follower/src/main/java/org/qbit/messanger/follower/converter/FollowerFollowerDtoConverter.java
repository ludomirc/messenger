package org.qbit.messanger.follower.converter;

import org.qbit.messanger.Converter;
import org.qbit.messanger.follower.dto.FollowerDto;
import org.qbit.messanger.follower.model.Follower;
import org.springframework.stereotype.Component;

@Component
public class FollowerFollowerDtoConverter  implements Converter<Follower, FollowerDto> {
    @Override
    public FollowerDto convert(Follower source) {
        FollowerDto target = new FollowerDto();
        target.setOwnerId(source.getOwnerId());
        target.setObservedUserId(source.getObservedUserId());
        return target;
    }
}
