package fr.sigma.ca.integration.persistence;

import static org.springframework.data.querydsl.QuerydslUtils.QUERY_DSL_PRESENT;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

public class CustomRepositoryFactory extends JpaRepositoryFactory {

    public CustomRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected JpaRepositoryImplementation<?, ?> getTargetRepository(
        RepositoryInformation information, EntityManager entityManager) {

        Class<?> repositoryInterface = information.getRepositoryInterface();
        JpaEntityInformation<?, Serializable> entityInformation =
            getEntityInformation(information.getDomainType());

        SimpleJpaRepository<?, ?> repo;

        if (isFiltreAgenceExerciceRespository(repositoryInterface)) {
            if (isQueryDslExecutorCustom(repositoryInterface)) {
                repo = new AgenceQueryDslJpaRepositoryImpl(entityInformation,
                    entityManager);
            } else {
                repo = new AgenceSimpleJpaRepositoryImpl(entityInformation, entityManager);
            }
        } else {
            if (isQueryDslExecutorCustom(repositoryInterface)) {
                repo = new QuerydslJpaRepository(entityInformation, entityManager);
            } else {
                repo = new SimpleJpaRepository(entityInformation, entityManager);
            }
        }

        return repo;
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        if (isFiltreAgenceExerciceRespository(metadata.getRepositoryInterface())) {
            if (isQueryDslExecutorCustom(metadata.getRepositoryInterface())) {
                return AgenceQueryDslJpaRepositoryImpl.class;
            } else {
                return AgenceSimpleJpaRepositoryImpl.class;
            }
        } else {
            if (isQueryDslExecutorCustom(metadata.getRepositoryInterface())) {
                return QuerydslJpaRepository.class;
            } else {
                return SimpleJpaRepository.class;
            }
        }
    }

    private boolean isQueryDslExecutorCustom(Class<?> repositoryInterface) {
        return QUERY_DSL_PRESENT
            && QuerydslPredicateExecutor.class.isAssignableFrom(repositoryInterface);
    }

    private boolean isFiltreAgenceExerciceRespository(Class<?> repositoryInterface) {
        return repositoryInterface.isAnnotationPresent(FiltreAgence.class);
    }
}
