package com.actuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "say-hello")
public class MyEndpoint {

    @ReadOperation
    public String say() {
        return "hello world";
    }

}
