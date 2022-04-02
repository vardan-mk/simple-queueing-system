package com.vardanmk.simplequeueingservice.service;

import com.vardanmk.simplequeueingservice.dto.QueueInfoDto;
import com.vardanmk.simplequeueingservice.dto.ServiceDto;

import java.util.List;

public interface QueueService {
    List<QueueInfoDto> getQueuesInfo();
    ServiceDto joinServiceQueue(String queueId, String serviceId);
    String summonServiceQueueUser(String queueId, String serviceId, int servicerId);
}
