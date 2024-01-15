package yass.jouao.labx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yass.jouao.labx.entities.Fournisseur;

@Repository
public interface IFournisseurRepository extends JpaRepository<Fournisseur, Long> {

}
