package fr.sigma.ca.integration.persistence;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class AgenceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        AgenceJpaRepository instance = (AgenceJpaRepository) invocation.getThis();

        try {
            instance.setAgenceCourant();
            return invocation.proceed();
        } finally {
            instance.unsetAgenceCourant();
        }
    }
}
