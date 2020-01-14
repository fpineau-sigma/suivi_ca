package fr.sigma.ca.integration.exception;

/**
 * La class pour les recheche retourne des elements (alors que l'on n'en attends pas)
 */
public class UnexpectedFoundException extends TechnicalException {

  public UnexpectedFoundException(String message) {
    super(message);
  }

  public UnexpectedFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
