package org.example;

import org.junit.jupiter.api.Test;

/**
 * This class should test the MathService class
 */
public class MathServiceTest {

  @Test
  public void testAdd() {
    // Arrange
    MathService mathService = new MathService();
    // Act
    double result = mathService.add(10, 20);
    // Assert
    assert result == 30;
  }

  @Test
  public void testSubtract() {
    // Arrange
    MathService mathService = new MathService();
    // Act
    double result = mathService.subtract(20, 10);
    // Assert
    assert result == 10;
  }

  @Test
  public void testMultiply() {
    // Arrange
    MathService mathService = new MathService();
    // Act
    double result = mathService.multiply(10, 20);
    // Assert
    assert result == 200;
  }

  @Test
  public void testDivide() {
    // Arrange
    MathService mathService = new MathService();
    // Act
    double result = mathService.divide(20, 10);
    // Assert
    assert result == 2;
  }

  @Test
  public void testDivideByZero() {
    // Arrange
    MathService mathService = new MathService();
    // Act
    try {
      mathService.divide(20, 0);
    } catch (IllegalArgumentException e) {
      // Assert
      assert e.getMessage().equals("Second argument must not be zero!");
    }
  }
}
