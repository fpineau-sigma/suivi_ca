package fr.sigma.ca.integration.utilitaire;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ObjetUtilitaire {

  /**
   * Modifie un objet de base en ajoutant toutes les modification d'un incrément
   *
   * @param base      l'objet de base, valide
   * @param increment l'incrément portant uniqment les modifications
   * @param clazz     La class des objets base et increment
   * @param <T>       Le type générique pour les objets base et increment
   */
  public static <T> void merge(T base, final T increment, final Class<T> clazz) {

    if (null == increment || null == base) {
      return;
    }

    Collection<Triplet> properties = extraireAccesseurPublic(clazz);

    properties.forEach(
        prop -> {
          if (null != prop.getSetter() && !"id".equalsIgnoreCase(prop.propertyName)) {
            try {
              Object newValue = prop.getGetter().invoke(increment, (Object[]) null);
              if (null != newValue) {
                prop.getSetter().invoke(base, newValue);
              }
            } catch (IllegalAccessException | InvocationTargetException e) {
              log.debug("impossible de traiter la propriété {}", prop.getPropertyName(), e);
            }
          }
        });
  }

  private static <T> Collection<Triplet> extraireAccesseurPublic(Class<T> clazz) {
    Map<String, Triplet> properties = new HashMap<>();
    Stream.of(clazz.getMethods())
        .forEach(
            method -> {
              if (Modifier.isPublic(method.getModifiers())) {
                String methodName;

                Triplet prop;

                if (method.getName().startsWith("get") && 0 == method.getParameters().length) {
                  methodName = method.getName().substring(3);

                  prop =
                      properties.getOrDefault(
                          methodName, Triplet.builder().propertyName(methodName).build());

                  prop.setGetter(method);
                } else if (method.getName().startsWith("set")) {
                  methodName = method.getName().substring(3);

                  prop =
                      properties.getOrDefault(
                          methodName, Triplet.builder().propertyName(methodName).build());
                  prop.setSetter(method);
                } else if (method.getName().startsWith("is")
                    && (method.getReturnType().isInstance(Boolean.class)
                    || boolean.class.equals(method.getReturnType()))
                    && 0 == method.getParameters().length) {
                  methodName = method.getName().substring(2);

                  prop =
                      properties.getOrDefault(
                          methodName, Triplet.builder().propertyName(methodName).build());
                  prop.setGetter(method);
                } else {
                  return;
                }
                properties.put(methodName, prop);
              }
            });
    return properties.values();
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @Getter
  @Setter
  @EqualsAndHashCode
  private static class Triplet {

    String propertyName;
    Method getter;
    Method setter;
  }
}
