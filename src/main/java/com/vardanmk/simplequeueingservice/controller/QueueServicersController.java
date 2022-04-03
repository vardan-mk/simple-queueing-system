package com.vardanmk.simplequeueingservice.controller;

import com.vardanmk.simplequeueingservice.dto.QueueInfoServicerDto;
import com.vardanmk.simplequeueingservice.dto.SurvicerResponseMessageDto;
import com.vardanmk.simplequeueingservice.model.QueueInfoServicerModel;
import com.vardanmk.simplequeueingservice.service.QueueService;
import com.vardanmk.simplequeueingservice.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/servicer/queues")
public class QueueServicersController {

    private final QueueService queueService;
    private final ValidationUtils validationUtils;

    @Autowired
    public QueueServicersController(QueueService queueService, ValidationUtils validationUtils) {
        this.queueService = queueService;
        this.validationUtils = validationUtils;
    }

    @GetMapping("/{queueId}/info/{servicerId}")
    public ResponseEntity<QueueInfoServicerDto> getServicesQueueDetails(@PathVariable String queueId, @PathVariable int servicerId) {
        if (validationUtils.validateQueueId(queueId)) {
            QueueInfoServicerModel infoServicerModel = queueService.getQueuesInfoServicers(queueId, servicerId);

            log.info("Servicer with id {} has requested to see details in {} queue", servicerId, queueId);
            return ResponseEntity.ok(QueueInfoServicerDto.builder()
                            .queueName(infoServicerModel.getQueueName())
                            .serviceInfo(infoServicerModel.getServiceInfo())
                    .build());
        } else {
            log.error("Servicer with id {} has try get info from wrong queue with id {}.", servicerId, queueId);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{queueId}/services/{serviceId}/servicers/{servicerId}/summon")
    public ResponseEntity<SurvicerResponseMessageDto> summonServiceQueueUser(@PathVariable String queueId,
                                                                    @PathVariable String serviceId,
                                                                    @PathVariable int servicerId) {
        if (validationUtils.validateQueueIdAndServiceId(queueId, serviceId)) {
            log.info("Servicer with id {} has serve current user from {} service queue", servicerId, serviceId);
            return ResponseEntity.ok(new SurvicerResponseMessageDto(queueService.summonServiceQueueUser(queueId, serviceId, servicerId)));
        } else {
            log.error("Servicer with id {} has try to serve wrong queue with id {} or service {}.", servicerId, queueId, serviceId);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
