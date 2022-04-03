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

        InitQueue repairQueue = createInitQueue(serviceQueuesRepair, servicersRepair, ttsRepair);

        InitQueue paymentQueue = createInitQueue(serviceQueuesPayment, servicersPayment, ttsPayment);

        return Map.of(repairQueueName, repairQueue, paymentQueueName, paymentQueue);
    }

    private InitQueue createInitQueue(String[] serviceList, int servicersCount, int tts) {

        Map<String, Queue<Integer>> serviceMap = new HashMap<>();
        Arrays.stream(serviceList).forEach(id -> {
            Queue<Integer> serviceQueue = new LinkedList<>();
            serviceMap.put(id, serviceQueue);
        });

        return InitQueue.builder()
                .queueServices(serviceMap)
                .servicersCount(servicersCount)
                .tts(tts)
                .build();
    }
}
