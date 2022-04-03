package com.vardanmk.simplequeueingservice.controller;

import com.vardanmk.simplequeueingservice.dto.QueueInfoUserDto;
import com.vardanmk.simplequeueingservice.dto.ServiceDto;
import com.vardanmk.simplequeueingservice.model.QueueInfoUserModel;
import com.vardanmk.simplequeueingservice.model.ServiceModel;
import com.vardanmk.simplequeueingservice.service.QueueService;
import com.vardanmk.simplequeueingservice.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/user/queues")
public class QueueUsersController {

    private final QueueService queueService;
    private final ValidationUtils validationUtils;

    @Autowired
    public QueueUsersController(QueueService queueService, ValidationUtils validationUtils) {
        this.queueService = queueService;
        this.validationUtils = validationUtils;
    }

    @GetMapping()
    public ResponseEntity<List<QueueInfoUserDto>> getQueues() {
        List<QueueInfoUserModel> infoUserModels = queueService.getQueuesInfoUsers();

        List<QueueInfoUserDto> infoUserModelsDto = infoUserModels.stream()
                .map(infoUserModel -> QueueInfoUserDto.builder()
                        .queueId(infoUserModel.getQueueId())
                        .services(infoUserModel.getServices())
                        .servicers(infoUserModel.getServicers())
                .build())
                .collect(Collectors.toList());

        log.info("User has requested Queues info list.");

        return ResponseEntity.ok(infoUserModelsDto);
    }

    @PostMapping("/{queueId}/services/{serviceId}/join/{userId}")
    public ResponseEntity<ServiceDto> joinServiceQueue(@PathVariable String queueId,
                                                       @PathVariable String serviceId,
                                                       @PathVariable int userId) {
        if (validationUtils.validateQueueIdAndServiceId(queueId, serviceId)) {

            ServiceModel serviceModel = queueService.joinServiceQueue(queueId, serviceId, userId);

            log.info("User with id {} has requested to join {} service queue.", userId, serviceId);

            return ResponseEntity.ok(ServiceDto.builder()
                            .serviceId(serviceModel.getServiceId())
                            .waitingUsers(serviceModel.getWaitingUsers())
                            .waitTime(serviceModel.getWaitTime())
                    .build());
        } else {
            log.error("User with id {} has requested wrong queue with id {} or service {}.", userId, queueId, serviceId);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
