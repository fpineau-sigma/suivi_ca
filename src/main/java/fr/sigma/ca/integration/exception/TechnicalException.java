package fr.sigma.ca.integration.exception;

/**
 * La super classe pour les exceptions techniques, à sous classer selon le besoin
 */
public class TechnicalException extends RuntimeException {

  public TechnicalException(String message) {
    super(message);
  }

  public TechnicalException(String message, Throwable cause) {
    super(message, cause);
  }
}
