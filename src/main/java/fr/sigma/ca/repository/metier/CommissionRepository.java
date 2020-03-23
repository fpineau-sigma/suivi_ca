package fr.sigma.ca.repository.metier;

import fr.sigma.ca.domain.metier.Commission;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("CommissionRepository")
public interface CommissionRepository extends JpaRepository<Commission, Long> {

  Collection<Commission> findByNegociateur_nomCourt(@Param("negociateur") String nomCourt);

  @Query("select concat(a.numeroVoie, ' , ' , a.nomVoie), n.nomCourt, c.montantHT from Vente v " +
      "inner join v.adresse as a inner join v.commissionsEntree  as c inner join c.negociateur as n")
  List<Object> findCommissionEntreeWithAdresse();


  @Query("select concat(a.numeroVoie, ' , ' , a.nomVoie), n.nomCourt, c.montantHT from Vente v " +
      "inner join v.adresse as a inner join v.commissionsSortie  as c inner join c.negociateur as n")
  List<Object> findCommissionSortieWithAdresse();
}
