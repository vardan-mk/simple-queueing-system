package com.vardanmk.simplequeueingservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Queue;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceQueue {

    private Map<String, Queue<Integer>> serviceQueue;
}
