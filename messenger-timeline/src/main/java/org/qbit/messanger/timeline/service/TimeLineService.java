package org.qbit.messanger.timeline.service;

import org.qbit.messanger.timeline.dto.PostDto;

import java.util.List;

public interface TimeLineService {

   List<PostDto> getFollowed(String ownerId);
}
