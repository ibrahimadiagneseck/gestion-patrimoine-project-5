package sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.keys;

import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.Authority;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.Utilisateur;

import java.io.Serializable;


public class UtilisateurAuthorityId implements Serializable {

    private Utilisateur utilisateurId;

    private Authority authorityId;


    public UtilisateurAuthorityId() {
    }

    public UtilisateurAuthorityId(Utilisateur utilisateurId, Authority authorityId) {
        this.utilisateurId = utilisateurId;
        this.authorityId = authorityId;
    }

}
