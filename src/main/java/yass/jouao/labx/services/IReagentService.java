package yass.jouao.labx.services;

import java.util.List;

import yass.jouao.labx.DTOs.ReagentDTO;
import yass.jouao.labx.entities.Reagent;
import yass.jouao.labx.exeptions.NotFoundException;

public interface IReagentService {
	List<ReagentDTO> getAllReagentsService();

	Reagent getReagentByIdService(Long id) throws NotFoundException;

	ReagentDTO getReagentDTOByIdService(Long id) throws NotFoundException;

	List<ReagentDTO> getReagentsByIdFournisseur(Long id) throws NotFoundException;

	ReagentDTO addReagentService(ReagentDTO r) throws NotFoundException;

	ReagentDTO updateReagentService(Long id, ReagentDTO r) throws NotFoundException, IllegalAccessException;
}
