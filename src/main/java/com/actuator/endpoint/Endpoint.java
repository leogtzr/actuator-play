package com.actuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@org.springframework.boot.actuate.endpoint.annotation.Endpoint(id = "say-hello")
public class Endpoint {

    @ReadOperation
    public String say() {
        return "hello world";
    }

}
