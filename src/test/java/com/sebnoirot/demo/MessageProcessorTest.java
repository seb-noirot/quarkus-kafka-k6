package com.sebnoirot.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageProcessorTest {

  @DisplayName("Should return a processed message")
  @Test
  void shouldReturnAProcessedMessage() throws InterruptedException {
    MessageProcessor messageProcessor = new MessageProcessor();
    String request = "Hello";
    String expected = "Processed " + request;
    assertEquals(expected, messageProcessor.process(request));
  }
}