package fr.sigma.ca.config.interceptor;

import fr.sigma.ca.integration.securite.ContexteCourant;
import fr.sigma.ca.integration.securite.HeaderApplicatif;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Slf4j
@Component
@RequiredArgsConstructor
public class HeaderExerciceContextInterceptor extends HandlerInterceptorAdapter {

    private final ContexteCourant contexteCourant;

    @Override
    public boolean preHandle(
        HttpServletRequest request, HttpServletResponse response, Object handler) {

        String exercice = request.getHeader(HeaderApplicatif.EXERCICE.getCle());

        if (StringUtils.isNotBlank(exercice)) {
            contexteCourant.setExerciceId(Long.valueOf(exercice));
        }
        return true;
    }

    @Override
    public void postHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler,
        ModelAndView modelAndView) {
        log.debug("{}.postHandle()", getClass().getSimpleName());
    }
}
