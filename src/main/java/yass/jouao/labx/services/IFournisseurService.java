package yass.jouao.labx.services;

import java.util.List;
import java.util.Optional;

import yass.jouao.labx.entities.Fournisseur;
import yass.jouao.labx.exeptions.NotFoundException;

public interface IFournisseurService {
	List<Fournisseur> getAllFournisseursService();

	Fournisseur getFournisseurByIdService(Long id) throws NotFoundException;

	Fournisseur addFournisseurService(Fournisseur f);

	Fournisseur updateFournisseurService(Fournisseur f) throws NotFoundException;

	void deleteFournisseurService(Long id) throws NotFoundException;
}
