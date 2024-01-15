package yass.jouao.labx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yass.jouao.labx.entities.UserLab;

@Repository
public interface IUserLabRepository extends JpaRepository<UserLab, Long> {

}
