package yass.jouao.labx.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import yass.jouao.labx.entities.Fournisseur;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class IFournisseurRepositoryTest {
    @Autowired
    private IFournisseurRepository fournisseurRepository;

    @Test
    @DisplayName("Test of save")
    public void testSave(){
        //Arange
        Fournisseur fournisseur = Fournisseur.builder().id(1L).name("FornisseurName").build();
        //Act
        Fournisseur savedFournisseur = fournisseurRepository.save(fournisseur);
        //Assert
        assertNotNull(savedFournisseur);
        assertEquals(savedFournisseur.getId(), 1L);
    }
    @Test
    @DisplayName("Test of findAll")
    public void TestFindAll(){

        Fournisseur fournisseur1 = Fournisseur.builder().id(1L).name("Fornisseur1").build();
        Fournisseur fournisseur2 = Fournisseur.builder().id(2L).name("Fornisseur2").build();
        fournisseurRepository.save(fournisseur1);
        fournisseurRepository.save(fournisseur2);

        // Retrieve all entities using the findAll() method
        List<Fournisseur> result = fournisseurRepository.findAll();

        // Verify the results
      assertEquals(2, result.size());
        assertEquals("Fornisseur1", result.get(0).getName());
        assertEquals("Fornisseur2", result.get(1).getName());
//        //Arange
//        Fournisseur fournisseur1 = Fournisseur.builder().id(1L).name("Fornisseur1").build();
//        Fournisseur fournisseur2 = Fournisseur.builder().id(2L).name("Fornisseur2").build();
//        fournisseurRepository.save(fournisseur1);
//        fournisseurRepository.save(fournisseur2);
//        //Act
//        List<Fournisseur> fournisseurs = fournisseurRepository.findAll();
//        //Assert
//        assertNotNull(fournisseurs);
//        assertEquals(2,fournisseurs.size());
    }
//    @Test
//    @DisplayName("Test of findById")
//    public void TestFindById(){
//        //Arange
//        Fournisseur fournisseur = Fournisseur.builder().id(1L).name("Fornisseur").build();
//        fournisseurRepository.save(fournisseur);
//        //Act
//        Fournisseur fournisseur1 = fournisseurRepository.findById(fournisseur.getId()).get();
//        //Assert
//        assertNotNull(fournisseur1);
//        assertEquals(fournisseur1.getName(),fournisseur.getName());
//    }
}