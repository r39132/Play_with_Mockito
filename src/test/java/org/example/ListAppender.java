package org.example;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;
import java.util.List;

public class ListAppender extends AppenderBase<ILoggingEvent> {
  private final List<ILoggingEvent> logs = new ArrayList<>();

  @Override
  protected void append(ILoggingEvent eventObject) {
    logs.add(eventObject);
  }

  public List<ILoggingEvent> getLogs() {
    return new ArrayList<>(logs);
  }
}
