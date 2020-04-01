package fr.sigma.ca.integration.persistence;

import fr.sigma.ca.integration.securite.ContexteCourant;
import fr.sigma.ca.integration.utilitaire.BeanUtil;
import java.io.Serializable;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

@Slf4j
public class AgenceQueryDslJpaRepositoryImpl<T, ID extends Serializable>
    extends QuerydslJpaRepository<T, ID>
    implements AgenceJpaRepository<T, ID>, QuerydslPredicateExecutor<T> {

    private final EntityManager em;

    public AgenceQueryDslJpaRepositoryImpl(
        JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager, SimpleEntityPathResolver.INSTANCE);
        this.em = entityManager;
    }

    public AgenceQueryDslJpaRepositoryImpl(
        JpaEntityInformation<T, ID> entityInformation,
        EntityManager entityManager,
        EntityPathResolver resolver) {

        super(entityInformation, entityManager, resolver);
        this.em = entityManager;
    }

    @Override
    public void setAgenceCourant() {
        Session session = this.getSession();
        ContexteCourant contexteCourant = BeanUtil.getBean(ContexteCourant.class);
        Filter filterAgence = session.enableFilter(EntiteAgence.FILTER_AGENCE_NAME);
        filterAgence
            .setParameter(EntiteAgence.PARAM_AGENCE_NAME, contexteCourant.getAgenceId());
    }

    @Override
    public void unsetAgenceCourant() {
        Session session = this.getSession();
        if (session.isOpen()) {
            session.disableFilter(EntiteAgence.FILTER_AGENCE_NAME);
        }
    }

    @Override
    public Session getSession() {
        return (Session) em.getDelegate();
    }
}
