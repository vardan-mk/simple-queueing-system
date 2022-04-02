package com.vardanmk.simplequeueingservice.service.impl;

import com.vardanmk.simplequeueingservice.config.QueuesConfigMap;
import com.vardanmk.simplequeueingservice.dto.QueueInfoDto;
import com.vardanmk.simplequeueingservice.dto.ServiceDto;
import com.vardanmk.simplequeueingservice.model.ServiceQueue;
import com.vardanmk.simplequeueingservice.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    private QueuesConfigMap queues;

    @Override
    public List<QueueInfoDto> getQueuesInfo() {

        return queues.getQueues().entrySet().stream()
                .map(queue -> new QueueInfoDto(queue.getKey(), queue.getValue().getServiceQueues()))
                .collect(Collectors.toList());
    }

    @Override
    public ServiceDto joinServiceQueue(String queueId, String serviceId) {
        int i = 0;
        ServiceQueue serviceQueue = new ServiceQueue();
        Queue<Integer> usersQueue = new ArrayDeque<>();
        usersQueue.add(++i);
        serviceQueue.getServiceQueue().put(serviceId, usersQueue);
        return null;
    }

    @Override
    public String summonServiceQueueUser(String queueId, String serviceId, int servicerId) {
        return null;
    }
}
