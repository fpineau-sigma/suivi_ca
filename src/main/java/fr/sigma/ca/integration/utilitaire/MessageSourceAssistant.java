package fr.sigma.ca.integration.utilitaire;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageSourceAssistant {

  private static MessageSource staticMessageSource;

  static {
    ReloadableResourceBundleMessageSource messageSource =
        new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultEncoding("UTF-8");
    staticMessageSource = messageSource;
  }

  public static String getMessage(String key, Object... params) {
    return staticMessageSource.getMessage(key, params, LocaleContextHolder.getLocale());
  }

  public static String getMessage(String key) {
    return staticMessageSource.getMessage(key, new Object[]{}, LocaleContextHolder.getLocale());
  }
}
