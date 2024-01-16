package yass.jouao.labx.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.entities.Patient;

@Repository
public interface IAnalysisRepository extends JpaRepository<Analysis, Long> {

	List<Analysis> findAllByPatient(Patient patient);
}
