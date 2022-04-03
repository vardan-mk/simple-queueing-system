package com.vardanmk.simplequeueingservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceModel {
    private String serviceId;
    private int waitingUsers;
    private int waitTime;
}
