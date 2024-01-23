package yass.jouao.labx.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.enums.AnalysisStatus;
import yass.jouao.labx.enums.Sex;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class IAnalysisRepositoryTest {
    @Autowired
    private IAnalysisRepository analysisRepository;

    @Test
    @DisplayName("Test of save")
    public void testSave(){
        //Arange
        Analysis analysis = Analysis.builder().id(1L).startDate(LocalDateTime.now()).endDate(LocalDateTime.now()).resultAnalysis(true).status(AnalysisStatus.WAITING).build();
        //Act
        Analysis savedAnalysis = analysisRepository.save(analysis);
        //Assert
        assertNotNull(savedAnalysis);
        assertEquals(savedAnalysis.getId(), 1L);
    }
    @Test
    @DisplayName("Test of findAll")
    public void TestFindAll(){
        //Arange
        Analysis analysis1 = Analysis.builder().id(1L).startDate(LocalDateTime.now()).endDate(LocalDateTime.now()).resultAnalysis(true).status(AnalysisStatus.WAITING).build();
        Analysis analysis2 = Analysis.builder().id(2L).startDate(LocalDateTime.now()).endDate(LocalDateTime.now()).resultAnalysis(false).status(AnalysisStatus.ANALYZING).build();
        analysisRepository.save(analysis1);
        analysisRepository.save(analysis2);
        //Act
        List<Analysis> analysiss = analysisRepository.findAll();
        //Assert
        assertNotNull(analysiss);
        assertEquals(2,analysiss.size());
        assertEquals(AnalysisStatus.WAITING,analysiss.get(0).getStatus());
        assertEquals(AnalysisStatus.ANALYZING,analysiss.get(1).getStatus());
    }
    @Test
    @DisplayName("Test of findById")
    public void TestFindById(){
        //Arange
        Analysis analysis = Analysis.builder().startDate(LocalDateTime.now()).endDate(LocalDateTime.now()).resultAnalysis(true).status(AnalysisStatus.WAITING).build();
        analysisRepository.save(analysis);
        //Act
        Analysis analysis1 = analysisRepository.findById(analysis.getId()).get();
        //Assert
        assertNotNull(analysis1);
        assertEquals(analysis1.getStatus(),AnalysisStatus.WAITING);
        assertNotEquals(analysis1.getResultAnalysis(),false);
    }
    @Test
    @DisplayName("Test of Update")
    public void updateTest(){
        Analysis analysis = Analysis.builder().startDate(LocalDateTime.now()).endDate(LocalDateTime.now()).resultAnalysis(false).status(AnalysisStatus.WAITING).build();
        analysisRepository.save(analysis);
        Analysis analysisSave = analysisRepository.findById(analysis.getId()).get();
        analysisSave.setResultAnalysis(true);
        analysisSave.setStatus(AnalysisStatus.ANALYZING);
        Analysis updatedAnalysis = analysisRepository.save(analysisSave);
        assertEquals(updatedAnalysis.getResultAnalysis(),true);
        assertNotEquals(updatedAnalysis.getStatus(),AnalysisStatus.WAITING);
    }
    @Test
    @DisplayName("Test of Delete")
    public void deleteTest(){
        Analysis analysis = Analysis.builder().startDate(LocalDateTime.now()).endDate(LocalDateTime.now()).resultAnalysis(true).status(AnalysisStatus.WAITING).build();
        analysisRepository.save(analysis);
        analysisRepository.deleteById(analysis.getId());
        Optional<Analysis> analysisReturn = analysisRepository.findById(analysis.getId());
        assertFalse(analysisReturn.isPresent());
    }
}