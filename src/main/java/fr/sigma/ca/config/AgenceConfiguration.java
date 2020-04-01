package fr.sigma.ca.config;

import fr.sigma.ca.config.interceptor.HeaderAgenceContextInterceptor;
import fr.sigma.ca.config.interceptor.HeaderExerciceContextInterceptor;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class AgenceConfiguration implements WebMvcConfigurer {

    private final HeaderAgenceContextInterceptor headerAgenceContextInterceptor;
    private final HeaderExerciceContextInterceptor headerExerciceContextInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        Stream.of(
            headerAgenceContextInterceptor,
            headerExerciceContextInterceptor)
            .forEach(registry::addInterceptor);
    }
}
