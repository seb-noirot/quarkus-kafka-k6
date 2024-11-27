package com.sebnoirot.demo;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class MessageProcessor {

    @Incoming("incoming-messages")
    @Outgoing("outgoing-messages")
    @Blocking
    public String process(String request) throws InterruptedException {
        return "Processed " + request;
    }
}
