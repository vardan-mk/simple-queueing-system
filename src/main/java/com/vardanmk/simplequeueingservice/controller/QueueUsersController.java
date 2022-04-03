package com.vardanmk.simplequeueingservice.controller;

import com.vardanmk.simplequeueingservice.dto.QueueInfoUserDto;
import com.vardanmk.simplequeueingservice.dto.ServiceDto;
import com.vardanmk.simplequeueingservice.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user/queues")
public class QueueUsersController {

    private final QueueService queueService;

    private final Set<String> queueIds = Set.of("repairQueue", "paymentQueue");
    private final Set<String> serviceIdsForFirstQueue = Set.of("phoneRepair");
    private final Set<String> serviceIdsForSecondQueue = Set.of("phonePayments", "communalPayments");

    @Autowired
    public QueueUsersController(QueueService queueService) {
        this.queueService = queueService;
    }

    @GetMapping()
    public ResponseEntity<List<QueueInfoUserDto>> getQueues() {
        return ResponseEntity.ok(queueService.getQueuesInfoUsers());
    }

    @PostMapping("/{queueId}/services/{serviceId}/join/{userId}")
    public ResponseEntity<ServiceDto> joinServiceQueue(@PathVariable String queueId,
                                                       @PathVariable String serviceId,
                                                       @PathVariable int userId) {
        if (queueIds.contains(queueId) && (queueId.equals("repairQueue") && serviceIdsForFirstQueue.contains(serviceId))
        || (queueId.equals("paymentQueue") && serviceIdsForSecondQueue.contains(serviceId)))

            return ResponseEntity.ok(queueService.joinServiceQueue(queueId, serviceId, userId));

        else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
