package yass.jouao.labx.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import yass.jouao.labx.entities.UserLab;
import yass.jouao.labx.entities.UserLab;
import yass.jouao.labx.enums.RoleUser;
import yass.jouao.labx.enums.StatusUser;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class IUserLabRepositoryTest {
    @Autowired
    private IUserLabRepository userLabRepository;

    @Test
    @DisplayName("Test of save")
    public void testSave(){
        //Arange
        UserLab user = UserLab.builder().id(1L).name("UserName").userRole(RoleUser.MANAGER).password("password").status(StatusUser.ACTIVE).build();
        //Act
        UserLab savedUserLab = userLabRepository.save(user);
        //Assert
        assertNotNull(savedUserLab);
        assertEquals(savedUserLab.getId(), 1L);
    }
    @Test
    @DisplayName("Test of findAll")
    public void TestFindAll(){
        //Arange
        UserLab user1 = UserLab.builder().name("User1").userRole(RoleUser.MANAGER).password("password1").status(StatusUser.ACTIVE).build();
        UserLab user2 = UserLab.builder().name("User2").userRole(RoleUser.ADMIN).password("password2").status(StatusUser.ACTIVE).build();
        userLabRepository.save(user1);
        userLabRepository.save(user2);
        //Act
        List<UserLab> users = userLabRepository.findAll();
        //Assert
        assertNotNull(users);
        assertEquals(2,users.size());
        assertEquals("User1",users.get(0).getName());
        assertEquals("User2",users.get(1).getName());
    }
    @Test
    @DisplayName("Test of findById")
    public void TestFindById(){
        //Arange
        UserLab user = UserLab.builder().name("User").userRole(RoleUser.MANAGER).password("password").status(StatusUser.ACTIVE).build();
        userLabRepository.save(user);
        //Act
        UserLab user1 = userLabRepository.findById(user.getId()).get();
        //Assert
        assertNotNull(user1);
        assertEquals(user1.getName(),user.getName());
        assertEquals(user1.getUserRole(),user.getUserRole());
        assertEquals(user1.getPassword(),user.getPassword());
    }
    @Test
    @DisplayName("Test of Update")
    public void updateTest(){
        UserLab user = UserLab.builder().name("User").userRole(RoleUser.TECHNICIAN).password("password").status(StatusUser.ACTIVE).build();
        userLabRepository.save(user);
        UserLab userSave = userLabRepository.findById(user.getId()).get();
        userSave.setName("UserUp");
        userSave.setUserRole(RoleUser.MANAGER);
        UserLab updatedUserLab = userLabRepository.save(userSave);
        assertNotNull(updatedUserLab.getName());
        assertEquals(updatedUserLab.getName(),"UserUp");
        assertNotEquals(updatedUserLab.getUserRole(),"TECHNICIAN");
    }
    @Test
    @DisplayName("Test of Delete")
    public void deleteTest(){
        UserLab user = UserLab.builder().name("User").userRole(RoleUser.MANAGER).password("password").status(StatusUser.ACTIVE).build();
        userLabRepository.save(user);
        userLabRepository.deleteById(user.getId());
        Optional<UserLab> userReturn = userLabRepository.findById(user.getId());
        assertFalse(userReturn.isPresent());
    }
}