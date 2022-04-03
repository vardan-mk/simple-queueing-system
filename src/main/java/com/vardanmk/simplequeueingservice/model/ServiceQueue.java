package com.vardanmk.simplequeueingservice.model;

import lombok.*;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceQueue {

    private String serviceId;
    private Queue<Integer> serviceQueue = new LinkedList<>();
}
