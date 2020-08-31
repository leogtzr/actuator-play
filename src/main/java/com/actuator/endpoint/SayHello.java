package com.actuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "say-hello")
public class SayHello {

    @ReadOperation
    public String say() {
        return "hello world";
    }

    @ReadOperation
    public String say(@Selector String name) {
        return "Hello " + name + "!";
    }

}
