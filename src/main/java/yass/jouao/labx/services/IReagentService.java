package yass.jouao.labx.services;

import yass.jouao.labx.entities.Reagent;

import java.util.List;
import java.util.Optional;

public interface IReagentService {
    List<Reagent> getAllReagentsService();
    Optional<Reagent> getReagentByIdService(Long id);
    Reagent saveReagentService(Reagent reagent);
    Reagent updateReagentService(long id, Reagent reagentRequest) ;
    void deleteReagentService(Long id);
}
