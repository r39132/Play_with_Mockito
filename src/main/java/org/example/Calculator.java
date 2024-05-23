package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {
  private static final Logger logger = LoggerFactory.getLogger(Calculator.class);

  private MathService mathService;

  public Calculator(MathService mathService) {
    this.mathService = mathService;
  }

  //Simple calculator to perform basic mathematical operations
  public double calculate(double firstOperand, double secondOperand, char operator) {
    logger.info("Calculating {} {} {}", firstOperand, operator, secondOperand);
    switch (operator) {
      case '/': {
        return mathService.divide(firstOperand, secondOperand);
      }
      case '+': {
        return mathService.add(firstOperand, secondOperand);
      }
      case '-': {
        return mathService.subtract(firstOperand, secondOperand);
      }
      case '*': {
        return mathService.multiply(firstOperand, secondOperand);
      }
      default:
        logger.info("Unsupported operation : {}", operator);
        throw new IllegalArgumentException("Unsupported operation :" + operator);
    }
  }

}
