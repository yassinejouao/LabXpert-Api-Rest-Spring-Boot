package yass.jouao.labx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yass.jouao.labx.entities.TestReagent;

@Repository
public interface ITestReagentRepository extends JpaRepository<TestReagent, Long> {

}
