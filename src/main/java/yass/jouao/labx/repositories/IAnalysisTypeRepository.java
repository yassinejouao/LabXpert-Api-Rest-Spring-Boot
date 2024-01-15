package yass.jouao.labx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yass.jouao.labx.entities.AnalysisType;

@Repository
public interface IAnalysisTypeRepository extends JpaRepository<AnalysisType, Long> {

}
