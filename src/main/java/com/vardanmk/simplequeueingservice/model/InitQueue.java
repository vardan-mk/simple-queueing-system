package com.vardanmk.simplequeueingservice.model;

import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InitQueue {
    private Map<String, Queue<Integer>> queueServices;
    private int servicersCount;
    private int tts;

    public Set<String> getServiceNames() {
        return queueServices.keySet();
    }

    public Map<String, Integer> getServiceInfo() {
        return queueServices.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));
    }
}
