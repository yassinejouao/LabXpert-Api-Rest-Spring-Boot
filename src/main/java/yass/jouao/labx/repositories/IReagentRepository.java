package yass.jouao.labx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yass.jouao.labx.entities.Reagent;

@Repository
public interface IReagentRepository extends JpaRepository<Reagent, Long> {

}
