package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
public class CalculatorTest {

  @Mock
  private MathService mathService;

  @InjectMocks
  private Calculator calculator;

  @Captor
  private ArgumentCaptor<Double> captor;

  private ListAppender listAppender;

  @Test
  public void testCaptorForAdd() {
    // Act
    calculator.calculate(10, 20, '+');

    // Assert that the add method was called with the correct arguments
    verify(mathService).add(captor.capture(), captor.capture());
    verify(mathService, times(1)).add(anyDouble(), anyDouble());
    assertEquals(10, captor.getAllValues().get(0));
    assertEquals(20, captor.getAllValues().get(1));
  }

  @Test
  public void testCaptorForSubtract() {
    // Act
    calculator.calculate(20, 10, '-');

    // Assert that the subtract method was called with the correct arguments
    verify(mathService).subtract(captor.capture(), captor.capture());
    verify(mathService, times(1)).subtract(anyDouble(), anyDouble());
    assertEquals(20, captor.getAllValues().get(0));
    assertEquals(10, captor.getAllValues().get(1));
  }

  @Test
  public void testCaptorForMultiply() {
    // Act
    calculator.calculate(10, 20, '*');

    // Assert that the multiply method was called with the correct arguments
    verify(mathService).multiply(captor.capture(), captor.capture());
    verify(mathService, times(1)).multiply(anyDouble(), anyDouble());
    assertEquals(10, captor.getAllValues().get(0));
    assertEquals(20, captor.getAllValues().get(1));
  }

  @Test
  public void testCaptorForDivide() {
    // Act
    calculator.calculate(20, 10, '/');

    // Assert that the divide method was called with the correct arguments
    verify(mathService).divide(captor.capture(), captor.capture());
    verify(mathService, times(1)).divide(anyDouble(), anyDouble());
    assertEquals(20, captor.getAllValues().get(0));
    assertEquals(10, captor.getAllValues().get(1));
  }

  @Test
  public void testAdd() {
    // Arrange
    when(mathService.add(10, 20)).thenReturn(30.0);

    // Act
    double result = calculator.calculate(10, 20, '+');

    // Assert
    assertEquals(30, result);
  }

  @Test
  public void testSubtract() {
    // Arrange
    when(mathService.subtract(20, 10)).thenReturn(10.0);

    // Act
    double result = calculator.calculate(20, 10, '-');

    // Assert
    assertEquals(10, result);
  }

  @Test
  public void testMultiply() {
    // Arrange
    when(mathService.multiply(10, 20)).thenReturn(200.0);

    // Act
    double result = calculator.calculate(10, 20, '*');

    // Assert
    assertEquals(200, result);
  }

  @Test
  public void testDivide() {
    // Arrange
    when(mathService.divide(20, 10)).thenReturn(2.0);

    // Act
    double result = calculator.calculate(20, 10, '/');

    // Assert
    assertEquals(2, result);
  }

  //.....
  @Test
  void testDivideByZero() {
    // Arrange
    when(mathService.divide(anyDouble(), eq(0.0))).thenThrow(
        new IllegalArgumentException("Second argument must not be zero!"));

    // Act & Assert
    Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.calculate(1, 0, '/'));
  }

  @Test
  void testInvalidOperator() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.calculate(1, 1, ':'));
  }

  @Test
  void testLogsForAdd() {
    // Act
    calculator.calculate(10, 20, '+');

    // Verify log messages
    List<ILoggingEvent> logsList = listAppender.getLogs();
    assertEquals(1, logsList.size());
    assertEquals("Calculating 10.0 + 20.0", logsList.get(0).getFormattedMessage());
  }

  @Test
  void testLogsForSubtract() {
    // Act
    calculator.calculate(20, 10, '-');

    // Verify log messages
    List<ILoggingEvent> logsList = listAppender.getLogs();
    assertEquals(1, logsList.size());
    assertEquals("Calculating 20.0 - 10.0", logsList.get(0).getFormattedMessage());
  }

  @Test
  void testLogsForMultiply() {
    // Act
    calculator.calculate(10, 20, '*');

    // Verify log messages
    List<ILoggingEvent> logsList = listAppender.getLogs();
    assertEquals(1, logsList.size());
    assertEquals("Calculating 10.0 * 20.0", logsList.get(0).getFormattedMessage());
  }

  @Test
  void testLogsForDivide() {
    // Act
    calculator.calculate(20, 10, '/');

    // Verify log messages
    List<ILoggingEvent> logsList = listAppender.getLogs();
    assertEquals(1, logsList.size());
    assertEquals("Calculating 20.0 / 10.0", logsList.get(0).getFormattedMessage());
  }

  @BeforeEach
  public void setup() {
    // Initialize custom ListAppender
    Logger logger = (Logger) LoggerFactory.getLogger(Calculator.class);
    listAppender = new ListAppender();
    listAppender.start();
    logger.addAppender(listAppender);
  }

  @Test
  public void testGetComputationsRun() {
    // Act
    calculator.calculate(10, 20, '+');
    calculator.calculate(20, 10, '-');
    calculator.calculate(10, 20, '*');
    calculator.calculate(20, 10, '/');

    // Assert
    assertEquals(4, calculator.getComputationsRun());
  }

  @Test
  public void testGetComputationsRunWithSpy() {
    Calculator spyCalculator = org.mockito.Mockito.spy(calculator);
    doReturn(10).when(spyCalculator).getComputationsRun();
    spyCalculator.calculate(10, 20, '+');
    verify(spyCalculator).printComputationsRun();
    assertEquals(10, spyCalculator.getComputationsRun());
  }
}
