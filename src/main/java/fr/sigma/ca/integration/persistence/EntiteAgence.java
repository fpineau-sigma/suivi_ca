package fr.sigma.ca.integration.persistence;

import fr.sigma.ca.integration.securite.ContexteCourant;
import fr.sigma.ca.integration.utilitaire.BeanUtil;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@MappedSuperclass
@FilterDefs({
    @FilterDef(
        name = EntiteAgence.FILTER_AGENCE_NAME,
        parameters = @ParamDef(name = EntiteAgence.PARAM_AGENCE_NAME, type = "long")),
})
@Filters({
    @Filter(name = EntiteAgence.FILTER_AGENCE_NAME, condition = "agence_id = :agenceId"),
})
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class EntiteAgence extends Entite {

    public static final String FILTER_AGENCE_NAME = "agenceFilter";
    public static final String PARAM_AGENCE_NAME = "agenceId";

    @Basic
    @Column(name = "agence_id", nullable = false)
    protected Long agenceId;

    private void setCurrentAgenceId() {
        ContexteCourant contexteCourant = BeanUtil.getBean(ContexteCourant.class);
        this.agenceId = contexteCourant.getAgenceId();
    }

    @PreUpdate
    @PrePersist
    protected void onChange() {
        if (null == this.agenceId) {
            setCurrentAgenceId();
        }
    }
}
