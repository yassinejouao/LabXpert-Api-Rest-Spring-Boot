package yass.jouao.labx.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yass.jouao.labx.entities.Reagent;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IReagentRepository;
import yass.jouao.labx.services.IReagentService;

import java.util.List;
import java.util.Optional;

@Service
public class ReagentServiceImpl implements IReagentService {
    @Autowired
    private IReagentRepository reagentRepository;
    @Override
    public List<Reagent> getAllReagentsService() {
        return reagentRepository.findAll();
    }
    @Override
    public Optional<Reagent> getReagentByIdService(Long id) {
        if(reagentRepository.findById(id).isPresent()){
            return reagentRepository.findById(id);
        }
        else {
            throw new NotFoundException("Reagent not exist");
        }
    }
    @Override
    public Reagent saveReagentService(Reagent r){
        return reagentRepository.save(r);
    };
    @Override
    public Reagent updateReagentService(long id, Reagent r) {
        if(reagentRepository.existsById(id)){
            return reagentRepository.save(r);
        }
        else {
            throw new NotFoundException("Reagent not exist");
        }
    }
    @Override
    public void deleteReagentService(Long id) {
        if(reagentRepository.findById(id).isPresent()){
            reagentRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Reagent not exist");
        }
    }
}
