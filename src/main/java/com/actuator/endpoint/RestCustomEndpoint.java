package com.actuator.endpoint;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Log4j2
@RestControllerEndpoint(id = "my-custom-endpoint", enableByDefault = true)
public class RestCustomEndpoint {

    private Map<String, Integer> names = new ConcurrentHashMap<>();

    // curl http://localhost:8081/actuator/my-custom-endpoint/custom
    // curl http://localhost:8081/actuator/my-custom-endpoint/custom | jq .
    // http :8081/actuator/my-custom-endpoint/custom
    @GetMapping("/custom")
    public ResponseEntity customEndpoint() {
        return ResponseEntity.ok(new StrWrapper("what is up"));
    }

    @GetMapping("/custom2")
    public ResponseEntity customEndpoint2() {
        return ResponseEntity.ok(names);
    }

    // http -f POST :8081/actuator/my-custom-endpoint/hmm name=olappp other=zzzZzzz
    @PostMapping("/hmm")
    public void update(@Selector String name, @Selector String other) {
        log.info(String.format("Receiving: %s", name));
        log.info(String.format("Receiving: %s", other));
        names.put(name , names.computeIfAbsent(name, s -> 0) + 1);
    }

    @GetMapping("/hola/{name}")
    public void update(@PathVariable String name) {
        log.info(String.format("Receiving: %s", name));
        names.put(name , names.computeIfAbsent(name, s -> 0) + 1);
    }

    @PostMapping("/aver")
    public void testAver(@RequestBody String email) {
        log.info(String.format("Receiving: %s", email));
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
