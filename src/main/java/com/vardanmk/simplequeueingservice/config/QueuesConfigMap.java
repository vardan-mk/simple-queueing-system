package com.vardanmk.simplequeueingservice.config;

import com.vardanmk.simplequeueingservice.model.QueueInfo;
import com.vardanmk.simplequeueingservice.model.ServiceQueue;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;

@Getter
@Configuration
@ConfigurationProperties(prefix = "queues")
public class QueuesConfigMap {
    private Map<String, QueueInfo> queues;
}
