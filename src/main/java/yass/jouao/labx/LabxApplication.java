package yass.jouao.labx;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.entities.AnalysisType;
import yass.jouao.labx.entities.Fournisseur;
import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.entities.Reagent;
import yass.jouao.labx.entities.Sample;
import yass.jouao.labx.entities.Test;
import yass.jouao.labx.entities.TestReagent;
import yass.jouao.labx.entities.TestType;
import yass.jouao.labx.entities.UserLab;
import yass.jouao.labx.enums.AnalysisStatus;
import yass.jouao.labx.enums.RoleUser;
import yass.jouao.labx.enums.SampleStatus;
import yass.jouao.labx.enums.SampleType;
import yass.jouao.labx.enums.Sex;
import yass.jouao.labx.enums.StatusUser;
import yass.jouao.labx.enums.TestStatus;
import yass.jouao.labx.repositories.IAnalysisRepository;
import yass.jouao.labx.repositories.IAnalysisTypeRepository;
import yass.jouao.labx.repositories.IFournisseurRepository;
import yass.jouao.labx.repositories.IPatientRepository;
import yass.jouao.labx.repositories.IReagentRepository;
import yass.jouao.labx.repositories.ISampleRepository;
import yass.jouao.labx.repositories.ITestReagentRepository;
import yass.jouao.labx.repositories.ITestRepository;
import yass.jouao.labx.repositories.ITestTypeRepository;
import yass.jouao.labx.repositories.IUserLabRepository;

@SpringBootApplication
public class LabxApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabxApplication.class, args);
	}

//	Random random = new Random();
//	@Autowired
//	IFournisseurRepository fournisseurRepository;
//	@Autowired
//	IReagentRepository reagentRepository;
//	@Autowired
//	IAnalysisTypeRepository analysisTypeRepository;
//	@Autowired
//	ITestTypeRepository testTypeRepository;
//	@Autowired
//	IUserLabRepository userLabRepository;
//	@Autowired
//	IPatientRepository patientRepository;
//	@Autowired
//	ISampleRepository sampleRepository;
//	@Autowired
//	IAnalysisRepository analysisRepository;
//	@Autowired
//	ITestRepository testRepository;
//	@Autowired
//	ITestReagentRepository testReagentRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		// Create 2 fournisseurs
//		Fournisseur fournisseur1 = new Fournisseur();
//		fournisseur1.setName("fournisseur1");
//		fournisseurRepository.save(fournisseur1);
//		Fournisseur fournisseur2 = new Fournisseur();
//		fournisseur2.setName("fournisseur2");
//		fournisseurRepository.save(fournisseur2);
//
//		// Create 4 Reagents
//		// 2 reagent to fournisseur1
//		Reagent reagent1 = new Reagent();
//		reagent1.setName("reagent1");
//		reagent1.setDescription("reagent 1 description");
//		reagent1.setStock(11);
//		reagent1.setPrice(11.1);
//		reagent1.setExpirationDate(LocalDate.of(2027, 10, 26));
//		reagent1.setFournisseur(fournisseur1);
//		reagentRepository.save(reagent1);
//		Reagent reagent2 = new Reagent();
//		reagent2.setName("reagent2");
//		reagent2.setDescription("reagent 2 description");
//		reagent2.setStock(2222);
//		reagent2.setPrice(22.2);
//		reagent2.setExpirationDate(LocalDate.of(2028, 11, 12));
//		reagent2.setFournisseur(fournisseur1);
//		reagentRepository.save(reagent2);
//		// 2 reagent to fournisseur2
//		Reagent reagent3 = new Reagent();
//		reagent3.setName("reagent3");
//		reagent3.setDescription("reagent 3 description");
//		reagent3.setStock(3333);
//		reagent3.setPrice(33.3);
//		reagent3.setExpirationDate(LocalDate.of(2029, 12, 13));
//		reagent3.setFournisseur(fournisseur2);
//		reagentRepository.save(reagent3);
//		Reagent reagent4 = new Reagent();
//		reagent4.setName("reagent4");
//		reagent4.setDescription("reagent 4 description");
//		reagent4.setStock(4444);
//		reagent4.setPrice(44.4);
//		reagent4.setExpirationDate(LocalDate.of(2030, 4, 24));
//		reagent4.setFournisseur(fournisseur2);
//		reagentRepository.save(reagent4);
//
//		// Create 3 AnalysisTypes
//		AnalysisType gastroAnalysisType = new AnalysisType();
//		gastroAnalysisType.setName("gastro");
//		analysisTypeRepository.save(gastroAnalysisType);
//		AnalysisType hepatiqueAnalysisType = new AnalysisType();
//		hepatiqueAnalysisType.setName("hepatique");
//		analysisTypeRepository.save(hepatiqueAnalysisType);
//		AnalysisType diabeteAnalysisType = new AnalysisType();
//		diabeteAnalysisType.setName("diabete");
//		analysisTypeRepository.save(diabeteAnalysisType);
//
//		// Create 3 TestTypes for every analysisTypes
//		//// gastro Analysis calcium chlore magnesium
//		TestType calcium = new TestType();
//		calcium.setName("calcium");
//		calcium.setMin(4.2);
//		calcium.setMax(8.5);
//		calcium.setAnalysisType(gastroAnalysisType);
//		testTypeRepository.save(calcium);
//		TestType chlore = new TestType();
//		chlore.setName("chlore");
//		chlore.setMin(7.0);
//		chlore.setMax(18.2);
//		chlore.setAnalysisType(gastroAnalysisType);
//		testTypeRepository.save(chlore);
//		TestType magnesium = new TestType();
//		magnesium.setName("magnesium");
//		magnesium.setMin(6.6);
//		magnesium.setMax(11.2);
//		magnesium.setAnalysisType(gastroAnalysisType);
//		testTypeRepository.save(magnesium);
//		//// hepatique Analysis alat asat gammagt
//		TestType alat = new TestType();
//		alat.setName("alat");
//		alat.setMin(4.2);
//		alat.setMax(8.5);
//		alat.setAnalysisType(hepatiqueAnalysisType);
//		testTypeRepository.save(alat);
//		TestType asat = new TestType();
//		asat.setName("asat");
//		asat.setMin(7.0);
//		asat.setMax(18.2);
//		asat.setAnalysisType(hepatiqueAnalysisType);
//		testTypeRepository.save(asat);
//		TestType gammagt = new TestType();
//		gammagt.setName("gammagt");
//		gammagt.setMin(6.6);
//		gammagt.setMax(11.2);
//		gammagt.setAnalysisType(hepatiqueAnalysisType);
//		testTypeRepository.save(gammagt);
//		//// diabte Analysis glycémie HbA1c glucose
//		TestType glycemie = new TestType();
//		glycemie.setName("glycemie");
//		glycemie.setMin(3.0);
//		glycemie.setMax(6.3);
//		glycemie.setAnalysisType(diabeteAnalysisType);
//		testTypeRepository.save(glycemie);
//		TestType hba1c = new TestType();
//		hba1c.setName("hba1c");
//		hba1c.setMin(1.0);
//		hba1c.setMax(3.2);
//		hba1c.setAnalysisType(diabeteAnalysisType);
//		testTypeRepository.save(hba1c);
//		TestType glucose = new TestType();
//		glucose.setName("glucose");
//		glucose.setMin(5.2);
//		glucose.setMax(10.2);
//		glucose.setAnalysisType(diabeteAnalysisType);
//		testTypeRepository.save(glucose);
//
//		// Create Users (one admin , one technician, one manager)
//		//// create admin
//		UserLab userAdmin = new UserLab();
//		userAdmin.setName("admin1");
//		userAdmin.setPassword("passwordadmin");
//		userAdmin.setUserRole(RoleUser.ADMIN);
//		userAdmin.setInformation("information admin");
//		userAdmin.setStatus(StatusUser.ACTIVE);
//		userLabRepository.save(userAdmin);
//		//// create technician
//		UserLab technician = new UserLab();
//		technician.setName("technician1");
//		technician.setPassword("passwordtechnician");
//		technician.setUserRole(RoleUser.TECHNICIAN);
//		technician.setInformation("information technician");
//		technician.setStatus(StatusUser.ACTIVE);
//		userLabRepository.save(technician);
//		//// create manager
//		UserLab manager = new UserLab();
//		manager.setName("manager1");
//		manager.setPassword("passwordmanager");
//		manager.setUserRole(RoleUser.MANAGER);
//		manager.setInformation("information manager");
//		manager.setStatus(StatusUser.ACTIVE);
//		userLabRepository.save(manager);
//
//		// Create 2 patients
//		Patient patient1 = new Patient();
//		patient1.setFirstname("firstnamepatient1");
//		patient1.setLastname("lastnamepatient1");
//		patient1.setDateOfBirth(LocalDate.of(1999, 2, 4));
//		patient1.setSex(Sex.MAN);
//		patient1.setPhone("212123456789");
//		patientRepository.save(patient1);
//		Patient patient2 = new Patient();
//		patient2.setFirstname("firstnamepatient2");
//		patient2.setLastname("lastnamepatient2");
//		patient2.setDateOfBirth(LocalDate.of(1980, 5, 11));
//		patient2.setSex(Sex.WOMAN);
//		patient2.setPhone("212987654321");
//		patientRepository.save(patient2);
//
//		// Create 2 samples
//		Sample Sampleforpatient1 = new Sample();
//		Sampleforpatient1.setPatient(patient1);
//		Sampleforpatient1.setStatus(SampleStatus.WAITING);
//		Sampleforpatient1.setType(SampleType.BLOOD);
//		Sampleforpatient1.setDate(LocalDateTime.of(2024, 1, 12, 10, 33));
//		sampleRepository.save(Sampleforpatient1);
//		Sample Sampleforpatient2 = new Sample();
//		Sampleforpatient2.setPatient(patient2);
//		Sampleforpatient2.setStatus(SampleStatus.WAITING);
//		Sampleforpatient2.setType(SampleType.NASAL);
//		Sampleforpatient2.setDate(LocalDateTime.of(2023, 10, 12, 9, 20));
//		sampleRepository.save(Sampleforpatient2);
//
//		// Create 2 analysis and add their own tests
//		Analysis analysisforpatient1 = new Analysis();
//		analysisforpatient1.setSample(Sampleforpatient1);
//		analysisforpatient1.setUser(technician);
//		analysisforpatient1.setPatient(patient1);
//		analysisforpatient1.setAnalysisType(diabeteAnalysisType);
//		analysisforpatient1.setStartDate(LocalDateTime.now());
//		analysisforpatient1.setEndDate(LocalDateTime.now().plusDays(10));
//		analysisforpatient1.setStatus(AnalysisStatus.WAITING);
//		analysisRepository.save(analysisforpatient1);
//		List<TestType> testTypes = testTypeRepository.findByAnalysisType(diabeteAnalysisType);
//		for (TestType testType : testTypes) {
//			Test test = new Test();
//			test.setTestType(testType);
//			test.setAnalysis(analysisforpatient1);
//			test.setStatus(TestStatus.WAITING);
//			testRepository.save(test);
//		}
//
//		Analysis analysisforpatient2 = new Analysis();
//		analysisforpatient2.setSample(Sampleforpatient2);
//		analysisforpatient2.setUser(technician);
//		analysisforpatient2.setPatient(patient2);
//		analysisforpatient2.setStartDate(LocalDateTime.now());
//		analysisforpatient2.setEndDate(LocalDateTime.now().plusDays(10));
//		analysisforpatient2.setStatus(AnalysisStatus.WAITING);
//		analysisforpatient2.setAnalysisType(gastroAnalysisType);
//		analysisRepository.save(analysisforpatient2);
//		testTypes = testTypeRepository.findByAnalysisType(gastroAnalysisType);
//		for (TestType testType : testTypes) {
//			Test test = new Test();
//			test.setTestType(testType);
//			test.setAnalysis(analysisforpatient2);
//			test.setStatus(TestStatus.WAITING);
//			testRepository.save(test);
//		}
//
//		// Create 4 reagenttests and add to tests
//		List<Reagent> reagents = reagentRepository.findAll();
//		List<Test> tests = testRepository.findAll();
//		for (Test test : tests) {
//			TestReagent testReagent1 = new TestReagent();
//			testReagent1.setReagent(reagents.get(0));
//			testReagent1.setQuantity(3);
//			testReagent1.setTest(test);
//			testReagentRepository.save(testReagent1);
//			TestReagent testReagent2 = new TestReagent();
//			testReagent2.setReagent(reagents.get(1));
//			testReagent2.setQuantity(2);
//			testReagent2.setTest(test);
//			testReagentRepository.save(testReagent2);
//		}
//	}

}