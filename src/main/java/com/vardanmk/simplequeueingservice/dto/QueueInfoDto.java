package com.vardanmk.simplequeueingservice.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueueInfoDto {
    private String queueId;
    private Set<String> services;
}
