package com.vardanmk.simplequeueingservice.utils;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidationUtils {
    private final Set<String> queueIds = Set.of("repairQueue", "paymentQueue");
    private final Set<String> serviceIdsForFirstQueue = Set.of("phoneRepair");
    private final Set<String> serviceIdsForSecondQueue = Set.of("phonePayments", "communalPayments");

    public boolean validateQueueId(String queueId) {
        return queueIds.contains(queueId);
    }

    public boolean validateQueueIdAndServiceId(String queueId, String serviceId) {
        return queueIds.contains(queueId) && (queueId.equals("repairQueue") && serviceIdsForFirstQueue.contains(serviceId))
                || (queueId.equals("paymentQueue") && serviceIdsForSecondQueue.contains(serviceId));
    }
}
