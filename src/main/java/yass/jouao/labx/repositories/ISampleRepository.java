package yass.jouao.labx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yass.jouao.labx.entities.Sample;

@Repository
public interface ISampleRepository extends JpaRepository<Sample, Long> {
}
