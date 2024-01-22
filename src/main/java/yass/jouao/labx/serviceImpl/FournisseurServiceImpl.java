package yass.jouao.labx.serviceImpl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.FournisseurDTO;
import yass.jouao.labx.entities.Fournisseur;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IFournisseurRepository;
import yass.jouao.labx.serviceImpl.Mappers.FournisseurMapper;
import yass.jouao.labx.services.IFournisseurService;

@Service
@AllArgsConstructor
public class FournisseurServiceImpl implements IFournisseurService {

	private IFournisseurRepository fournisseurRepository;
	private FournisseurMapper fournisseurMapper;

	@Override
	@Transactional
	public List<FournisseurDTO> getAllFournisseursService() {
		List<Fournisseur> fournisseurs = fournisseurRepository.findAll();
		List<FournisseurDTO> fournisseurDTOs = fournisseurs.stream()
				.map(fournisseur -> fournisseurMapper.fromFournisseurToFournisseurDTO(fournisseur))
				.collect(Collectors.toList());
		return fournisseurDTOs;
	}

	@Override
	@Transactional
	public FournisseurDTO getFournisseurByIdService(Long id) throws NotFoundException {
		Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findById(id);
		if (optionalFournisseur.isPresent()) {
			FournisseurDTO fournisseurDTO = fournisseurMapper
					.fromFournisseurToFournisseurDTO(optionalFournisseur.get());
			return fournisseurDTO;
		} else {
			throw new NotFoundException("Fournisseur not found");
		}
	}

	@Override
	@Transactional
	public FournisseurDTO addFournisseurService(FournisseurDTO f) {
		Fournisseur fournisseur = fournisseurMapper.fromFournisseurDTOToFournisseur(f);
		FournisseurDTO fournisseurDTO = fournisseurMapper
				.fromFournisseurToFournisseurDTO(fournisseurRepository.save(fournisseur));
		return fournisseurDTO;

	}

	@Override
	@Transactional
	public FournisseurDTO updateFournisseurService(Long id, FournisseurDTO f)
			throws NotFoundException, IllegalAccessException {
		Fournisseur fournisseurToUpdate = fournisseurMapper
				.fromFournisseurDTOToFournisseur(getFournisseurByIdService(id));
		f.setId(id);
		Fournisseur fournisseurNewData = fournisseurMapper.fromFournisseurDTOToFournisseur(f);
		updateNonNullFields(fournisseurToUpdate, fournisseurNewData);
		FournisseurDTO fournisseurDTO = fournisseurMapper
				.fromFournisseurToFournisseurDTO(fournisseurRepository.save(fournisseurToUpdate));
		return fournisseurDTO;
	}

	private void updateNonNullFields(Fournisseur existingEntity, Fournisseur updatedEntity)
			throws IllegalAccessException {
		Field[] fields = Fournisseur.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object updatedValue = field.get(updatedEntity);
			if (updatedValue != null) {
				field.set(existingEntity, updatedValue);
			}
		}
	}

	@Override
	@Transactional
	public void deleteFournisseurService(Long id) throws NotFoundException {
		if (fournisseurRepository.existsById(id)) {
			fournisseurRepository.deleteById(id);
		} else {
			throw new NotFoundException("fournisseur not found");
		}

	}

}
