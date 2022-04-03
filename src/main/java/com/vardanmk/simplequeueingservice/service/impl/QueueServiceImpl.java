package com.vardanmk.simplequeueingservice.service.impl;

import com.vardanmk.simplequeueingservice.dto.QueueInfoServicerDto;
import com.vardanmk.simplequeueingservice.dto.QueueInfoUserDto;
import com.vardanmk.simplequeueingservice.dto.ServiceDto;
import com.vardanmk.simplequeueingservice.model.InitQueue;
import com.vardanmk.simplequeueingservice.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    private Map<String, InitQueue> queues;

    @Override
    public List<QueueInfoUserDto> getQueuesInfoUsers() {

        return queues.entrySet().stream()
                .map(queue -> new QueueInfoUserDto(queue.getKey(),
                        queue.getValue().getServiceNames(),
                        queue.getValue().getServicers()))
                .collect(Collectors.toList());
    }

    @Override
    public ServiceDto joinServiceQueue(String queueId, String serviceId, int userId) {
        InitQueue selectedQueue = queues.get(queueId);
        Queue<Integer> serviceQueue = selectedQueue.getQueueServices().get(serviceId);
        serviceQueue.add(userId);
        int count = serviceQueue.size() - 1;
        int waitTime = count * selectedQueue.getTts();
        return ServiceDto.builder()
                .serviceId(serviceId)
                .count(count)
                .waitTime(waitTime)
                .build();
    }

    @Override
    public QueueInfoServicerDto getQueuesInfoServicers(String queueName, int servicerId) {
        return new QueueInfoServicerDto(queueName, queues.get(queueName).getServiceInfo());
    }

    @Override
    public String summonServiceQueueUser(String queueId, String serviceId, int servicerId) {

        InitQueue selectedQueue = queues.get(queueId);
        Queue<Integer> serviceQueue = selectedQueue.getQueueServices().get(serviceId);

        if (serviceQueue.size() > 1) {
            return "now we are serving user " + serviceQueue.poll() + ", next in the queue is user " + serviceQueue.peek();
        } else if (!serviceQueue.isEmpty()) {
            return "now we are serving user " + serviceQueue.poll() + ", There is no users waiting in a queue.";
        } else {
            return "There is no users in the queue, we are welcome...";
        }
    }
}
