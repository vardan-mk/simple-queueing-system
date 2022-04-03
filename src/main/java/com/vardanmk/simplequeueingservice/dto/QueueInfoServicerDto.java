package com.vardanmk.simplequeueingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueueInfoServicerDto {

    private String queueName;
    private Map<String, Integer> serviceInfo;
}
