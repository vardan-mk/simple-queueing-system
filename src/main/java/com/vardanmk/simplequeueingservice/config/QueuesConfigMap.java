package com.vardanmk.simplequeueingservice.config;

import com.vardanmk.simplequeueingservice.model.InitQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class QueuesConfigMap {

    @Value("${queues.repairQueue.id}")
    private String repairQueueName;

    @Value("${queues.repairQueue.services}")
    private String[] serviceQueuesRepair;

    @Value("${queues.repairQueue.servicers}")
    private int servicersRepair;

    @Value("${queues.repairQueue.tts}")
    private int ttsRepair;

    @Value("${queues.paymentQueue.id}")
    private String paymentQueueName;

    @Value("${queues.paymentQueue.services}")
    private String[] serviceQueuesPayment;

    @Value("${queues.paymentQueue.servicers}")
    private int servicersPayment;

    @Value("${queues.paymentQueue.tts}")
    private int ttsPayment;

    @Bean
    public Map<String, InitQueue> getQueues() {

        Map<String, Queue<Integer>> repairServiceMap = new HashMap<>();
        Arrays.stream(serviceQueuesRepair).forEach(id -> {
            Queue<Integer> serviceQueue = new LinkedList<>();
            repairServiceMap.put(id, serviceQueue);
        });

        InitQueue repairQueue = InitQueue.builder()
                .queueServices(repairServiceMap)
                .servicers(servicersRepair)
                .tts(ttsRepair)
                .build();

        Map<String, Queue<Integer>> paymentServiceMap = new HashMap<>();
        Arrays.stream(serviceQueuesPayment).forEach(id -> {
            Queue<Integer> serviceQueue = new LinkedList<>();
            paymentServiceMap.put(id, serviceQueue);
        });

        InitQueue paymentQueue = InitQueue.builder()
                .queueServices(paymentServiceMap)
                .servicers(servicersPayment)
                .tts(ttsPayment)
                .build();

        return Map.of(repairQueueName, repairQueue, paymentQueueName, paymentQueue);
    }
}
