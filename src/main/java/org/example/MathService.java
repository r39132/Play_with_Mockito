package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MathService {

  private static final Logger logger = LoggerFactory.getLogger(MathService.class);

  public double add(double firstOperand, double secondOperand) {
    logger.info("Adding {} and {}", firstOperand, secondOperand);
    return firstOperand + secondOperand;
  }

  public double divide(double firstOperand, double secondOperand) {
    if (secondOperand == 0.0) {
      logger.info("Second argument must not be zero!");
      throw new IllegalArgumentException("Second argument must not be zero!");
    }
    return firstOperand / secondOperand;
  }

  public double multiply(double firstOperator, double secondOperand) {
    logger.info("Multiplying {} and {}", firstOperator, secondOperand);
    return firstOperator * secondOperand;
  }

  public double subtract(double firstOperand, double secondOperand) {
    logger.info("Subtracting {} and {}", firstOperand, secondOperand);
    return firstOperand - secondOperand;
  }

}