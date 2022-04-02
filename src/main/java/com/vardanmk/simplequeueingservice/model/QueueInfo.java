package com.vardanmk.simplequeueingservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueueInfo {
    private Set<String> serviceQueues;
    private int servicers;
    private int tts;
}
