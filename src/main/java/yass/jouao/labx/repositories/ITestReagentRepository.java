package yass.jouao.labx.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yass.jouao.labx.entities.Test;
import yass.jouao.labx.entities.TestReagent;

@Repository
public interface ITestReagentRepository extends JpaRepository<TestReagent, Long> {
	List<TestReagent> findByTest(Test test);

	void deleteByTest(Test test);

	boolean existsByTest(Test test);
}
