package fr.sigma.ca.integration.exception;

/**
 * La super classe pour les exceptions métier, à sous classer selon le besoin
 */
public class BusinessException extends RuntimeException {

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }
}
