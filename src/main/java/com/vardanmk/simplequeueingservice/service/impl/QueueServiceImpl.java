package com.vardanmk.simplequeueingservice.service.impl;

import com.vardanmk.simplequeueingservice.model.InitQueue;
import com.vardanmk.simplequeueingservice.model.QueueInfoServicerModel;
import com.vardanmk.simplequeueingservice.model.QueueInfoUserModel;
import com.vardanmk.simplequeueingservice.model.ServiceModel;
import com.vardanmk.simplequeueingservice.service.QueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    private Map<String, InitQueue> queues;

    private final int ONE = 1;

    @Override
    public List<QueueInfoUserModel> getQueuesInfoUsers() {

        log.info("Providing Queues and Services details as Response.");
        return queues.entrySet().stream()
                .map(queue -> QueueInfoUserModel.builder()
                        .queueId(queue.getKey())
                        .services(queue.getValue().getServiceNames())
                        .servicers(queue.getValue().getServicersCount())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ServiceModel joinServiceQueue(String queueId, String serviceId, int userId) {
        InitQueue selectedQueue = queues.get(queueId);
        Queue<Integer> serviceQueue = selectedQueue.getQueueServices().get(serviceId);
        serviceQueue.add(userId);
        int count = serviceQueue.size() - 1;
        int waitTime = count * selectedQueue.getTts();
        log.info("User with id {} joined to service queue with id {}, where are {} waiting users with total time if {} minutes.", userId, serviceId, count, waitTime);
        return ServiceModel.builder()
                .serviceId(serviceId)
                .waitingUsers(count)
                .waitTime(waitTime)
                .build();
    }

    @Override
    public QueueInfoServicerModel getQueuesInfoServicers(String queueName, int servicerId) {
        log.info("Survicer with id {} requested to get details from {} queue.", servicerId, queueName);
        return QueueInfoServicerModel.builder()
                .queueName(queueName)
                .serviceInfo(queues.get(queueName).getServiceInfo())
                .build();
    }

    @Override
    public String summonServiceQueueUser(String queueId, String serviceId, int servicerId) {

        InitQueue selectedQueue = queues.get(queueId);
        Queue<Integer> serviceQueue = selectedQueue.getQueueServices().get(serviceId);


        if (serviceQueue.size() > ONE) {
            log.info("Survicer with id {} serving user with id {} for {} service.", servicerId, serviceQueue.peek(), serviceId);
            return "now we are serving user " + serviceQueue.poll() + ", next in the queue is user " + serviceQueue.peek();
        } else if (serviceQueue.size() == ONE) {
            log.info("Survicer with id {} serving user with id {} for {} service and there is no other waiting user.", servicerId, serviceQueue.peek(), serviceId);
            return "now we are serving user " + serviceQueue.poll() + ", There is no users waiting in a queue.";
        } else {
            log.info("There is no any waiting user in the queue.");
            return "There is no users in the queue, we are welcome...";
        }
    }
}
