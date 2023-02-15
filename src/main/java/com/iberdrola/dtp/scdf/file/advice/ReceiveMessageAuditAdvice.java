package com.iberdrola.dtp.scdf.file.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.aop.ReceiveMessageAdvice;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReceiveMessageAuditAdvice implements ReceiveMessageAdvice {

  @Override
  public Message<?> afterReceive(final Message<?> message, final Object source) {
    if (message == null) {
      return null;
    }

    log.info("Message received. Source: {}. Message: {}", source, message);
    
    return message;
  }
}
