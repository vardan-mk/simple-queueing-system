package com.vardanmk.simplequeueingservice.service;

import com.vardanmk.simplequeueingservice.dto.QueueInfoServicerDto;
import com.vardanmk.simplequeueingservice.dto.QueueInfoUserDto;
import com.vardanmk.simplequeueingservice.dto.ServiceDto;

import java.util.List;

public interface QueueService {
    List<QueueInfoUserDto> getQueuesInfoUsers();
    QueueInfoServicerDto getQueuesInfoServicers(String queueName, int servicerId);

    ServiceDto joinServiceQueue(String queueId, String serviceId, int userId);
    String summonServiceQueueUser(String queueId, String serviceId, int servicerId);
}
