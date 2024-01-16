package yass.jouao.labx.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.entities.Fournisseur;
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
	public Optional<Fournisseur> getFournisseurByIdService(Long id) {
		return fournisseurRepository.findById(id);
	}

	@Override
	@Transactional
	public Fournisseur addFournisseurService(Fournisseur f) {
		return fournisseurRepository.save(f);

	}

	@Override
	@Transactional
	public Fournisseur updateFournisseurService(Fournisseur f) {
		if (fournisseurRepository.existsById(f.getId())) {
			return fournisseurRepository.save(f);
		} else {
			throw new NotFoundException("you can't update unexist fournisseur");
		}
	}

	@Override
	@Transactional
	public void deleteFournisseurService(Long id) {
		if (fournisseurRepository.existsById(id)) {
			fournisseurRepository.deleteById(id);
		} else {
			throw new NotFoundException("fournisseur not found");
		}

	}

}
