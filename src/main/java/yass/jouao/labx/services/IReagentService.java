package yass.jouao.labx.services;

import java.util.List;
import java.util.Optional;

import yass.jouao.labx.entities.Reagent;

public interface IReagentService {
	List<Reagent> getAllReagentsService();

	Optional<Reagent> getReagentByIdService(Long id);

	Reagent addReagentService(Reagent r);

	Reagent updateReagentService(Reagent r);

	void deleteReagentService(Long id);
}
