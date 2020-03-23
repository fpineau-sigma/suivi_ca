package fr.sigma.ca.integration.exception;

/**
 * La class pour les recheche qui ne retourne pas d'elements (alors que l'on en attends au moins
 * un)
 */
public class UnexpectedNotFoundException extends TechnicalException {

  public UnexpectedNotFoundException(String message) {
    super(message);
  }

  public UnexpectedNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
