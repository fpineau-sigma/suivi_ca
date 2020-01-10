package fr.sigma.ca.repository;

        import fr.sigma.ca.entite.Commission;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;

        import java.util.Date;
     import java.util.List;
import java.util.UUID;

@Repository("CommissionRepository")
public interface CommissionRepository extends JpaRepository<Commission, UUID> {

    List<Commission> findAllByOrderByNegociateurAsc();

    List<Commission> findByNegociateur_nomCourt(@Param("negociateur") String nomCourt);

    List<Commission> findByDateVente(@Param("dateVente") Date dateVente);

    List<Commission> findByNegociateur_nomCourtAndDateVente(@Param("negociateur") String nomCourt, @Param("dateVente") Date dateVente);

    @Query("select concat(a.numero, ' , ' , a.nomVoie), n.nomCourt, c.montantHT from Vente v " +
            "inner join v.adresse as a inner join v.commissionsEntree  as c inner join c.negociateur as n")
    List<Object> findCommissionEntreeWithAdresse();


    @Query( "select concat(a.numero, ' , ' , a.nomVoie), n.nomCourt, c.montantHT from Vente v " +
            "inner join v.adresse as a inner join v.commissionsSortie  as c inner join c.negociateur as n")
    List<Object> findCommissionSortieWithAdresse();
}
