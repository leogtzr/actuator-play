package com.actuator.endpoint;

import lombok.Data;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RestControllerEndpoint(id = "my-this-shit")
public class RestCustomEndpoint {

    @GetMapping("/custom")
    public ResponseEntity customEndpoint() {
        //return new ResponseEntity<>("REST endpoint", HttpStatus.OK);
        return ResponseEntity.ok(new StrWrapper("what is up"));
    }

    @Data
    @AllArgsConstructor
    private static final class StrWrapper {
        private final String x;
    }

}
