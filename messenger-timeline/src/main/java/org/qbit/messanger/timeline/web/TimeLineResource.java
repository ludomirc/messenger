package org.qbit.messanger.timeline.web;


import org.qbit.messanger.timeline.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/v001/timeline")
public class TimeLineResource {

    @Autowired
    TimeLineService timeLineService;

    @GetMapping("/{userId}")
    ResponseEntity<?> getTimeLine(@Valid @Size(min = 1, max = 50) @PathVariable String userId){
        return new ResponseEntity(timeLineService.getFollowed(userId), HttpStatus.OK);
    }
}
