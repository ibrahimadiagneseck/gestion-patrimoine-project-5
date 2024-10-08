package sn.douanes.gestionPatrimoineVehiculeSpringBoot.services;

import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.TypeMateriel;

import java.util.List;


public interface TypeMaterielService {

    TypeMateriel saveTypeMateriel(TypeMateriel t);
    TypeMateriel updateTypeMateriel(TypeMateriel t);
    void deleteTypeMateriel(TypeMateriel t);
    void deleteTypeMaterielById(String id);
    TypeMateriel getTypeMateriel(String id);
    List<TypeMateriel> getAllTypeMateriels();


}
