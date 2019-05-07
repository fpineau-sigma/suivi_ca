package fr.sigma.ca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Launcher {
    /**
     * @author f2pineau Avant de lancer ce programme il faut :
     *
     *         1 - D�poser le fichier "dsn_cahier_technique_pV_20XX.Y.Z.xlsx dans le
     *         repo /fichierDSN de ce projet (V pour la Phase, XX pour l'ann�e, Y
     *         pour la version, Z pour la sous version) Ce fichier est trouvable sur
     *         le site officiel de la DSN (ne pas confondre avec les pdf) "DSN info"
     *         sur google -> Vous etes un editeur/concentrateur -> Consultez
     *         l�ensemble de la documentation relative � la phase 3 -> DSN P3 20XX
     *         -> Tableaux des cat�gories, contr�les, datatypes et usages
     *         correspondant au Cahier Technique ...
     *
     *         2 - Aller dans application.properties de ce projet et modifier les
     *         valeurs file.name(nom du fichier avec son extension [.xlsx
     *         obligatoire] et version.id ex : P19V01 (le contenu de la colonne
     *         DSNVE_CODE) en cons�quence
     *
     *         3 - V�rifier les colonnes du nouveau fichier par rapport � l'ancien
     *         du repo. Car une colonne en + et �a p�te !!!
     *
     *         Une fois ce programme lanc� il r�digera un rapport dans le r�pertoire
     *         /rapport + un fichier de requetes SQL au m�me endroit [dont certaines
     *         a compl�ter ;) ]
     *
     *         Have Fun !
     *
     */

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Launcher.class, args);
    }
}
