package yass.jouao.labx.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.entities.AnalysisType;
import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.enums.AnalysisStatus;

@Repository
public interface IAnalysisRepository extends JpaRepository<Analysis, Long> {

	List<Analysis> findAllByPatient(Patient patient);

	List<Analysis> findByStatusIn(AnalysisStatus... status);

	List<Analysis> findByAnalysisTypeAndStartDateBetween(AnalysisType analysisType, LocalDateTime startDate,
			LocalDateTime endDate);
}
