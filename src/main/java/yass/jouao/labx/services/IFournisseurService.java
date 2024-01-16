package yass.jouao.labx.services;

import yass.jouao.labx.entities.Fournisseur;

import java.util.List;
import java.util.Optional;

public interface IFournisseurService {
    List<Fournisseur> getAllFournisseursService();
    Optional<Fournisseur> getFournisseurByIdService(Long id);
    Fournisseur saveFournisseurService(Fournisseur fournisseur);
    Fournisseur updateFournisseurService(long id, Fournisseur fournisseurRequest) ;
    void deleteFournisseurService(Long id);
}
