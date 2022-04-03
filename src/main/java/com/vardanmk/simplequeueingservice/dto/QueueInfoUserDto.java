package com.vardanmk.simplequeueingservice.dto;

import lombok.*;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueueInfoUserDto {
    private String queueId;
    private Set<String> services;
    private int servicers;
}
