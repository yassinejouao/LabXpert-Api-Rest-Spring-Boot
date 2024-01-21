package yass.jouao.labx.serviceImpl.Mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.FournisseurDTO;
import yass.jouao.labx.entities.Fournisseur;

@Service
public class FournisseurMapper {

	public FournisseurDTO fromFournisseurToFournisseurDTO(Fournisseur fournisseur) {
		FournisseurDTO fournisseurDTO = new FournisseurDTO();
		BeanUtils.copyProperties(fournisseur, fournisseurDTO);
		return fournisseurDTO;
	}

	public Fournisseur fromFournisseurDTOToFournisseur(FournisseurDTO fournisseurDTO) {
		Fournisseur fournisseur = new Fournisseur();
		BeanUtils.copyProperties(fournisseurDTO, fournisseur);
		return fournisseur;
	}
}
