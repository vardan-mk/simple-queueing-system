package com.vardanmk.simplequeueingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceDto {
    private String serviceId;
    private int waitingUsers;
    private int waitTime;
}
