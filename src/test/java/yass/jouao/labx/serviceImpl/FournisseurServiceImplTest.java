package yass.jouao.labx.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import yass.jouao.labx.DTOs.FournisseurDTO;
import yass.jouao.labx.entities.Fournisseur;

import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IFournisseurRepository;
import yass.jouao.labx.serviceImpl.Mappers.FournisseurMapper;

@ExtendWith(MockitoExtension.class)
class FournisseurServiceImplTest {

	private FournisseurMapper fournisseurMapper;
	@Mock
	private IFournisseurRepository fournisseurRepository;
	@InjectMocks
	private FournisseurServiceImpl fournisseurService;

	@BeforeEach
	public void setup() {
		fournisseurMapper = new FournisseurMapper();
		fournisseurRepository = Mockito.mock(IFournisseurRepository.class);
		fournisseurService = new FournisseurServiceImpl(fournisseurRepository, fournisseurMapper);
	}
	@Test
	@DisplayName("test of getFournisseurByIdService ")
	void testGetFournisseurByIdService() throws NotFoundException {
		Fournisseur fournisseur = Fournisseur.builder().id(1L).name("FournisseurName").build();
		FournisseurDTO fournisseurDTO = FournisseurDTO.builder().id(1L).name("FournisseurName").build();
		Optional<Fournisseur> optionalFournisseur = Optional.of(fournisseur);

		when(fournisseurRepository.findById(1L)).thenReturn(optionalFournisseur);

		FournisseurDTO result = fournisseurService.getFournisseurByIdService(1L);

		assertEquals(fournisseurDTO, result);
		verify(fournisseurRepository, times(1)).findById(1L);
	}
	@Test
	@DisplayName("test of addFournisseurService ")
	void testAddFournisseurService() {
		Fournisseur fournisseur = Fournisseur.builder().id(1L).name("FournisseurName").build();
		FournisseurDTO fournisseurDTO = FournisseurDTO.builder().id(1L).name("FournisseurName").build();
		when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);
		FournisseurDTO result = fournisseurService.addFournisseurService(fournisseurDTO);
		assertEquals(fournisseurDTO, result);
		verify(fournisseurRepository, times(1)).save(fournisseur);
	}
	@Test
	@DisplayName("test of updateFournisseurService ")
	public void testUpdateFournisseurService() throws IllegalAccessException, NotFoundException {
		Long fournisseurId = 1L;
		FournisseurDTO newFournisseurDTO = FournisseurDTO.builder().id(fournisseurId).name("newF").build();
		Fournisseur newFournisseur = Fournisseur.builder().id(fournisseurId).name("newF").build();
		FournisseurDTO oldFournisseurDTO = FournisseurDTO.builder().id(fournisseurId).name("old").build();
		Fournisseur oldFournisseur = Fournisseur.builder().id(1L).name("old").build();
		Optional<Fournisseur> optionalFournisseur = Optional.of(oldFournisseur);

		when(fournisseurRepository.findById(1L)).thenReturn(optionalFournisseur);
		when(fournisseurRepository.save(any())).thenReturn(newFournisseur);

		FournisseurDTO result = fournisseurService.updateFournisseurService(fournisseurId, newFournisseurDTO);
		verify(fournisseurRepository, times(1)).save(any());
		assertEquals(fournisseurId, result.getId());
		assertNotEquals(oldFournisseurDTO.getName(), result.getName());
	}

	@Test
	@DisplayName("test of deleteFournisseurService ")
	public void deleteFournisseurServiceTest() throws NotFoundException {
		Long fournisseurId = 1L;
		when(fournisseurRepository.existsById(fournisseurId)).thenReturn(true);
		fournisseurService.deleteFournisseurService(fournisseurId);
		verify(fournisseurRepository, times(1)).deleteById(fournisseurId);
	}
}
