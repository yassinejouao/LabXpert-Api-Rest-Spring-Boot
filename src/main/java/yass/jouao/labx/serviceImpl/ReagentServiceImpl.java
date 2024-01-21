package yass.jouao.labx.serviceImpl;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.ReagentDTO;
import yass.jouao.labx.entities.Fournisseur;
import yass.jouao.labx.entities.Reagent;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IFournisseurRepository;
import yass.jouao.labx.repositories.IReagentRepository;
import yass.jouao.labx.serviceImpl.Mappers.ReagentMapper;
import yass.jouao.labx.services.IReagentService;

@Service
public class ReagentServiceImpl implements IReagentService {

	@Autowired
	private IReagentRepository reagentRepository;
	@Autowired
	private ReagentMapper reagentMapper;
	@Autowired
	private IFournisseurRepository fournisseurRepository;

	@Override
	@Transactional
	public List<ReagentDTO> getAllReagentsService() {
		List<Reagent> reagents = reagentRepository.findAll();
		List<ReagentDTO> reagentDTOs = reagents.stream().map(reagent -> reagentMapper.fromReagentToReagentDTO(reagent))
				.collect(Collectors.toList());
		return reagentDTOs;
	}

	@Override
	public ReagentDTO getReagentDTOByIdService(Long id) throws NotFoundException {
		Optional<Reagent> optionalReagent = reagentRepository.findById(id);
		if (optionalReagent.isPresent()) {
			ReagentDTO reagentDTO = reagentMapper.fromReagentToReagentDTO(optionalReagent.get());
			return reagentDTO;
		} else {
			throw new NotFoundException("Reagent not found");
		}
	}

	@Override
	public List<ReagentDTO> getReagentsByIdFournisseur(Long id) throws NotFoundException {
		Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findById(id);
		if (optionalFournisseur.isPresent()) {
			Collection<Reagent> reagents = optionalFournisseur.get().getReagents();
			List<ReagentDTO> reagentDTOs = reagents.stream()
					.map(reagent -> reagentMapper.fromReagentToReagentDTO(reagent)).collect(Collectors.toList());
			return reagentDTOs;
		} else {
			throw new NotFoundException("Fournisseur not found");
		}
	}

	@Override
	@Transactional
	public Reagent getReagentByIdService(Long id) throws NotFoundException {
		Optional<Reagent> optionalReagent = reagentRepository.findById(id);
		if (optionalReagent.isPresent()) {
			Reagent reagent = optionalReagent.get();
			return reagent;
		} else {
			throw new NotFoundException("Reagent not found");
		}
	}

	@Override
	@Transactional
	public ReagentDTO addReagentService(ReagentDTO r) throws NotFoundException {
		Reagent reagent = reagentMapper.fromReagentDTOToReagent(r);
		Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findById(r.getIdFournisseur());
		if (optionalFournisseur.isPresent()) {
			reagent.setFournisseur(optionalFournisseur.get());
			return reagentMapper.fromReagentToReagentDTO(reagentRepository.save(reagent));

		} else {
			throw new NotFoundException("Fournisseur not found");
		}
	}

	@Override
	public ReagentDTO updateReagentService(Long id, ReagentDTO r) throws NotFoundException, IllegalAccessException {
		Reagent reagentToUpdate = getReagentByIdService(id);
		r.setId(id);
		Reagent reagentNewData = reagentMapper.fromReagentDTOToReagent(r);
		updateNonNullFields(reagentToUpdate, reagentNewData);
		Optional<Fournisseur> optionalFournisseur = fournisseurRepository
				.findById(reagentToUpdate.getFournisseur().getId());
		reagentToUpdate.setFournisseur(optionalFournisseur.get());
		ReagentDTO reagentDTO = reagentMapper.fromReagentToReagentDTO(reagentRepository.save(reagentToUpdate));
		return reagentDTO;
	}

	private void updateNonNullFields(Reagent existingEntity, Reagent updatedEntity) throws IllegalAccessException {
		Field[] fields = Reagent.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object updatedValue = field.get(updatedEntity);
			if (updatedValue != null) {
				field.set(existingEntity, updatedValue);
			}
		}
	}

}
