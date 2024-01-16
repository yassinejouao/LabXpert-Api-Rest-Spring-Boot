package yass.jouao.labx.services;

import yass.jouao.labx.entities.Fournisseur;

import java.util.List;
import java.util.Optional;

public interface IFournisseurService {
    List<Fournisseur> getAllFournisseurs();
    Optional<Fournisseur> getFournisseurById(Long id);
    Fournisseur saveFournisseur(Fournisseur client);
    //Fournisseur updateFournisseur(long id, Fournisseur fournisseurRequest) ;
    void deleteFournisseur(Long id);
}
