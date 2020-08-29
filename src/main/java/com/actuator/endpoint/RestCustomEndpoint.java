package com.actuator.endpoint;

import lombok.Data;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@RestControllerEndpoint(id = "my-this-shit")
public class RestCustomEndpoint {

    // curl http://localhost:8081/actuator/my-this-shit/custom
    // curl http://localhost:8081/actuator/my-this-shit/custom | jq .
    // http :8081/actuator/my-this-shit/custo
    @GetMapping("/custom")
    public ResponseEntity customEndpoint() {
        return ResponseEntity.ok(new StrWrapper("what is up"));
    }

    @Data
    @AllArgsConstructor
    private static final class StrWrapper {
        private final String x;
    }

}
