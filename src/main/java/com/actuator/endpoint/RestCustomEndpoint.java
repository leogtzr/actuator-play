package com.actuator.endpoint;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Log4j2
@RestControllerEndpoint(id = "my-this-shit")
public class RestCustomEndpoint {

    private Map<String, Integer> names = new ConcurrentHashMap<>();

    // curl http://localhost:8081/actuator/my-this-shit/custom
    // curl http://localhost:8081/actuator/my-this-shit/custom | jq .
    // http :8081/actuator/my-this-shit/custo
    @GetMapping("/custom")
    public ResponseEntity customEndpoint() {
        return ResponseEntity.ok(new StrWrapper("what is up"));
    }

    @GetMapping("/custom2")
    public ResponseEntity customEndpoint2() {
        return ResponseEntity.ok(names);
    }

    @PostMapping("/hmm")
    public void update(@Selector String name) {
        log.info(String.format("Receiving: %s", name));
        names.put(name , names.computeIfAbsent(name, s -> 0) + 1);
    }

    private static final class StrWrapper {

        private final String x;

        public StrWrapper(String x) {
            this.x = x;
        }

        public String getX() {
            return x;
        }

        @Override
        public String toString() {
            return "StrWrapper{" +
                    "x='" + x + '\'' +
                    '}';
        }
    }

}
