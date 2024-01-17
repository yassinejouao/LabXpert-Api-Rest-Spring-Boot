package yass.jouao.labx.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.entities.Reagent;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IReagentRepository;
import yass.jouao.labx.services.IReagentService;

@Service
public class ReagentServiceImpl implements IReagentService {

	@Autowired
	private IReagentRepository reagentRepository;

	@Override
	@Transactional
	public List<Reagent> getAllReagentsService() {
		return reagentRepository.findAll();
	}

	@Override
	@Transactional
	public Optional<Reagent> getReagentByIdService(Long id) {
		return reagentRepository.findById(id);
	}

	@Override
	@Transactional
	public Reagent addReagentService(Reagent r) {
		return reagentRepository.save(r);
	}

	@Override
	@Transactional
	public Reagent updateReagentService(Reagent r) throws NotFoundException {
		if (reagentRepository.existsById(r.getId())) {
			return reagentRepository.save(r);
		} else {
			throw new NotFoundException("you can't update unexist reagent");
		}
	}

	@Override
	@Transactional
	public void deleteReagentService(Long id) throws NotFoundException {
		if (reagentRepository.existsById(id)) {
			reagentRepository.deleteById(id);
		} else {
			throw new NotFoundException("Reagent not found");
		}

	}

}
