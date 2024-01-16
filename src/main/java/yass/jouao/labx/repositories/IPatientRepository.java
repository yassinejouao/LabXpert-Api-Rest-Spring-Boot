package yass.jouao.labx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yass.jouao.labx.entities.Patient;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {
}
