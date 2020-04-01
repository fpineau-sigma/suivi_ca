package fr.sigma.ca.integration.persistence;

import fr.sigma.ca.integration.securite.ContexteCourant;
import fr.sigma.ca.integration.utilitaire.BeanUtil;
import java.io.Serializable;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@Slf4j
public class AgenceSimpleJpaRepositoryImpl<T, ID extends Serializable>
    extends SimpleJpaRepository<T, ID>
    implements AgenceJpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    private final EntityManager em;

    public AgenceSimpleJpaRepositoryImpl(
        JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
        super(entityInformation, em);
        this.em = em;
    }

    public AgenceSimpleJpaRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
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
