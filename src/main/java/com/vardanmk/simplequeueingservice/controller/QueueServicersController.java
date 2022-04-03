package com.vardanmk.simplequeueingservice.controller;

import com.vardanmk.simplequeueingservice.dto.QueueInfoServicerDto;
import com.vardanmk.simplequeueingservice.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/servicer/queues")
public class QueueServicersController {

    private final QueueService queueService;

    private final Set<String> queueIds = Set.of("repairQueue", "paymentQueue");
    private final Set<String> serviceIdsForFirstQueue = Set.of("phoneRepair");
    private final Set<String> serviceIdsForSecondQueue = Set.of("phonePayments", "communalPayments");

    @Autowired
    public QueueServicersController(QueueService queueService) {
        this.queueService = queueService;
    }

    @GetMapping("/{queueId}/info/{servicerId}")
    public ResponseEntity<QueueInfoServicerDto> getQueues(@PathVariable String queueId, @PathVariable int servicerId) {
        if (queueIds.contains(queueId))
            return ResponseEntity.ok(queueService.getQueuesInfoServicers(queueId, servicerId));
        else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{queueId}/services/{serviceId}/servicers/{servicerId}/summon")
    public ResponseEntity<String> summonServiceQueueUser(@PathVariable String queueId,
                                                                    @PathVariable String serviceId,
                                                                    @PathVariable int servicerId) {
        if (queueIds.contains(queueId) && (queueId.equals("repairQueue") && serviceIdsForFirstQueue.contains(serviceId))
                || (queueId.equals("paymentQueue") && serviceIdsForSecondQueue.contains(serviceId)))

            return ResponseEntity.ok(queueService.summonServiceQueueUser(queueId, serviceId, servicerId));
        else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
