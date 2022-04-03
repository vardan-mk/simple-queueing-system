package com.vardanmk.simplequeueingservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueueInfoUserModel {
    private String queueId;
    private Set<String> services;
    private int servicers;
}
