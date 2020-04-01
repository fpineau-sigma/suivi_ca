package fr.sigma.ca.integration.persistence;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class CustomRepositoryFactoryBean<T extends JpaRepository<S, I>, S, I extends Serializable>
    extends JpaRepositoryFactoryBean<T, S, I> {

    public CustomRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        RepositoryFactorySupport repositoryFactory = new CustomRepositoryFactory(entityManager);
        repositoryFactory.addRepositoryProxyPostProcessor(
            (ProxyFactory factory, RepositoryInformation repositoryInformation) -> {
                if (repositoryInformation
                    .getRepositoryInterface()
                    .isAnnotationPresent(FiltreAgence.class)) {
                    factory.addAdvice(new AgenceInterceptor());
                }
            });
        return repositoryFactory;
    }
}
