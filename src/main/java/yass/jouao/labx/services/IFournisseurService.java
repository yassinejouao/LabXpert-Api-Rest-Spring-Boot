package yass.jouao.labx.services;

import java.util.List;
import java.util.Optional;

import yass.jouao.labx.DTOs.FournisseurDTO;
import yass.jouao.labx.entities.Fournisseur;
import yass.jouao.labx.exeptions.NotFoundException;

public interface IFournisseurService {
	List<FournisseurDTO> getAllFournisseursService();

	FournisseurDTO getFournisseurByIdService(Long id) throws NotFoundException;

	FournisseurDTO addFournisseurService(FournisseurDTO f);

	FournisseurDTO updateFournisseurService(Long id, FournisseurDTO f) throws NotFoundException, IllegalAccessException ;

	void deleteFournisseurService(Long id) throws NotFoundException;
}
