package com.vardanmk.simplequeueingservice.service;

import com.vardanmk.simplequeueingservice.model.QueueInfoServicerModel;
import com.vardanmk.simplequeueingservice.model.QueueInfoUserModel;
import com.vardanmk.simplequeueingservice.model.ServiceModel;

import java.util.List;

public interface QueueService {
    List<QueueInfoUserModel> getQueuesInfoUsers();
    QueueInfoServicerModel getQueuesInfoServicers(String queueName, int servicerId);

    ServiceModel joinServiceQueue(String queueId, String serviceId, int userId);
    String summonServiceQueueUser(String queueId, String serviceId, int servicerId);
}
