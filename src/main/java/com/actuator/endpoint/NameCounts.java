package com.actuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RestControllerEndpoint(id = "counts")
public class NameCounts {

    private Map<String, Integer> counts = new ConcurrentHashMap<>();

    @GetMapping
    public Map<String, Integer> counts() {
        return counts;
    }

    @GetMapping("/{name}")
    public Integer count(@PathVariable String name) {
        return counts.getOrDefault(name, 0);
    }

    @PostMapping
    public void increase(@Selector String name) {
        counts.put(name , counts.computeIfAbsent(name, s -> 0) + 1);
    }

}
