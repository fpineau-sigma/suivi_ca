package fr.sigma.ca.integration.utilitaire;

import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.exception.TechnicalException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationAssistant {

  private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
  private static final Validator VALIDATOR = FACTORY.getValidator();

  /**
   * Validation d'un object. Verification des annotations tel que @{@link
   * javax.validation.constraints.NotNull}
   *
   * @param aValider instance de l'objet a valider
   * @param <T>      class de l'objet a valider
   */
  public static <T> void valider(T aValider) {
    Set<ConstraintViolation<T>> violations = VALIDATOR.validate(aValider);
    StringBuilder sb = new StringBuilder();
    violations.forEach(
        violation ->
            sb.append(violation.getPropertyPath())
                .append(" ")
                .append(violation.getMessage())
                .append(" "));

    if (sb.length() != 0) {
      throw new BusinessException(
          MessageSourceAssistant.getMessage(
              "msg.exception.invalid.bean", aValider.getClass().getSimpleName(), sb.toString()));
    }
  }


  /**
   * Vérifie si un champ donnée est null. Si le champ est de type text, verifie aussi si le champs
   * est vide ou blanc.
   *
   * @param champ    le champ a controler
   * @param nomChamp le nom du champ a controler
   * @param <T>      le type du champ a controler
   * @throws TechnicalException si le champ est vide
   */
  public static <T> void pasVide(T champ, String nomChamp) {
    if (!pasVide(champ)) {
      throw new TechnicalException(
          MessageSourceAssistant.getMessage("msg.exception.parametre.manquant", nomChamp));
    }
  }

  /**
   * Vérifie si un champ donnée est null. Si le champ est de type text, verifie aussi si le champs
   * est vide ou blanc.
   *
   * @param champ le champ a controler
   * @param <T>   le type du champ a controler
   * @return true si le champ n'est pas vide
   */
  public static <T> boolean pasVide(T champ) {
    if (champ instanceof String) {
      return !StringUtils.isEmpty(champ);
    } else {
      return null != champ;
    }
  }
}
