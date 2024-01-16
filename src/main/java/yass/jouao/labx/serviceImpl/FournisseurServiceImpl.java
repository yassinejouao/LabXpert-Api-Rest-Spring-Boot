package yass.jouao.labx.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yass.jouao.labx.entities.Fournisseur;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IFournisseurRepository;
import yass.jouao.labx.services.IFournisseurService;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurServiceImpl implements IFournisseurService {
    @Autowired
    private IFournisseurRepository fournisseurRepository;
    @Override
    public List<Fournisseur> getAllFournisseursService() {
        return fournisseurRepository.findAll();
    }
    @Override
    public Optional<Fournisseur> getFournisseurByIdService(Long id) {
        if(fournisseurRepository.findById(id).isPresent()){
            return fournisseurRepository.findById(id);
        }
        else {
            throw new NotFoundException("fournisseur not exist");
        }
    }
    @Override
    public Fournisseur saveFournisseurService(Fournisseur fournisseur){
        return fournisseurRepository.save(fournisseur);
    };
    @Override
	public Fournisseur updateFournisseurService(long id, Fournisseur fournisseurRequest) {
        if(fournisseurRepository.findById(id).isPresent()){
            return fournisseurRepository.save(fournisseurRequest);
        }
        else {
            throw new NotFoundException("fournisseur not exist");
        }
	}
    @Override
    public void deleteFournisseurService(Long id) {
        if(fournisseurRepository.findById(id).isPresent()){
            fournisseurRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("fournisseur not exist");
        }
    }
}
