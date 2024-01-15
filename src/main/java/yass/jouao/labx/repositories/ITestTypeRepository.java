package yass.jouao.labx.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yass.jouao.labx.entities.AnalysisType;
import yass.jouao.labx.entities.TestType;

@Repository
public interface ITestTypeRepository extends JpaRepository<TestType, Long> {
	List<TestType> findByAnalysisType(AnalysisType analysisType);
}
