package yass.jouao.labx.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.entities.Fournisseur;
import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IFournisseurRepository;
import yass.jouao.labx.services.IFournisseurService;

@Service
public class FournisseurServiceImpl implements IFournisseurService {

	@Autowired
	private IFournisseurRepository fournisseurRepository;

	@Override
	@Transactional
	public List<Fournisseur> getAllFournisseursService() {
		return fournisseurRepository.findAll();
	}

	@Override
	@Transactional
	public Fournisseur getFournisseurByIdService(Long id) throws NotFoundException {
		Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findById(id);
		if (optionalFournisseur.isPresent()) {
			return optionalFournisseur.get();
		} else {
			System.out.println("test ex");
			throw new NotFoundException("Fournisseur not found");
		}
	}

	@Override
	@Transactional
	public Fournisseur addFournisseurService(Fournisseur f) {
		return fournisseurRepository.save(f);

	}

	@Override
	@Transactional
	public Fournisseur updateFournisseurService(Fournisseur f) throws NotFoundException {
		if (fournisseurRepository.existsById(f.getId())) {
			return fournisseurRepository.save(f);
		} else {
			throw new NotFoundException("you can't update unexist fournisseur");
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
