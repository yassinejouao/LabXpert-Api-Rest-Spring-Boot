package yass.jouao.labx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yass.jouao.labx.entities.Analysis;

@Repository
public interface IAnalysisRepository extends JpaRepository<Analysis, Long> {

}
