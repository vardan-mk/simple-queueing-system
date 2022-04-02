package com.vardanmk.simplequeueingservice.controller;

import com.vardanmk.simplequeueingservice.dto.QueueInfoDto;
import com.vardanmk.simplequeueingservice.dto.ServiceDto;
import com.vardanmk.simplequeueingservice.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QueueController {

    private final QueueService queueService;

    @Autowired
    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @GetMapping("/queues")
    public ResponseEntity<List<QueueInfoDto>> getQueues() {
        return ResponseEntity.ok(queueService.getQueuesInfo());
    }

    @PostMapping("/queues/{queueId}/services/{serviceId}/join")
    public ResponseEntity<ServiceDto> joinServiceQueue(@PathVariable String queueId, @PathVariable String serviceId) {
        return ResponseEntity.ok(queueService.joinServiceQueue(queueId, serviceId));
    }

    @PostMapping("/queues/{queueId}/services/{serviceId}/servicers/{servicerId}/summon")
    public ResponseEntity<String> summonServiceQueueUser(@PathVariable String queueId,
                                                   @PathVariable String serviceId,
                                                   @PathVariable int servicerId) {
        return ResponseEntity.ok(queueService.summonServiceQueueUser(queueId, serviceId, servicerId));
    }
}
