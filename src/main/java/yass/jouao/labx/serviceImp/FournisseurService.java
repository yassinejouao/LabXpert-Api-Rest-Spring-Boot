package yass.jouao.labx.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yass.jouao.labx.entities.Fournisseur;
import yass.jouao.labx.repositories.IFournisseurRepository;
import yass.jouao.labx.services.IFournisseurService;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService implements IFournisseurService {
    @Autowired
    private IFournisseurRepository fournisseurRepository;
    @Override
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }
    @Override
    public Optional<Fournisseur> getFournisseurById(Long id) {
        /*Fournisseur fournisseur = fournisseurRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Fournisseur", "id", id));
		//return fournisseur;*/
        return fournisseurRepository.findById(id);
    }
    @Override
    public Fournisseur saveFournisseur(Fournisseur client){
        return fournisseurRepository.save(client);
    };
    /*@Override
	public Fournisseur updateFournisseur(long id, Fournisseur fournisseurRequest) {
		Fournisseur fournisseur = fournisseurRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Fournisseur", "id", id));
		fournisseur.setName(postRequest.getName());
		fournisseur.setReagents(postRequest.getReagents());
		return fournisseurRepository.save(fournisseur);
	}*/
    @Override
    public void deleteFournisseur(Long id) {
        /*Fournisseur fournisseur = fournisseurRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Fournisseur", "id", id));*/
        fournisseurRepository.deleteById(id);
    }
}
