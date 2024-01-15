package yass.jouao.labx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yass.jouao.labx.entities.Test;

@Repository
public interface ITestRepository extends JpaRepository<Test, Long> {

}
