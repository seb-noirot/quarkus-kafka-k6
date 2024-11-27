package com.sebnoirot.demo;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import io.smallrye.reactive.messaging.memory.InMemorySink;
import io.smallrye.reactive.messaging.memory.InMemorySource;

import org.eclipse.microprofile.reactive.messaging.spi.Connector;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.awaitility.Awaitility.await;

@QuarkusTest
@QuarkusTestResource(InMemoryConnectorLifecycleManager.class)
class MessageProcessorIntegrationTest {

  @Inject
  @Connector("smallrye-in-memory")
  InMemoryConnector connector;

  @Test
  void test() {
    InMemorySource<String> toProcess = connector.source("incoming-messages");
    InMemorySink<String> processed = connector.sink("outgoing-messages");

    toProcess.send("Bonjour");
    await()
        .untilAsserted(() -> assertEquals("Processed Bonjour", processed.received().get(0).getPayload()));
  }
}