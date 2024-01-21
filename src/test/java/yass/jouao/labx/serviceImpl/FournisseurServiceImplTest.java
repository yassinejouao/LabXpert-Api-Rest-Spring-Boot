package yass.jouao.labx.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import yass.jouao.labx.DTOs.FournisseurDTO;
import yass.jouao.labx.entities.Fournisseur;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IFournisseurRepository;
import yass.jouao.labx.serviceImpl.Mappers.FournisseurMapper;

@ExtendWith(MockitoExtension.class)
class FournisseurServiceImplTest {

	@Mock
	private IFournisseurRepository fournisseurRepository;
	@Mock
	private FournisseurMapper fournisseurMapper;
	@InjectMocks
	private FournisseurServiceImpl fournisseurServiceImpl;

	@Test
	public void addFournisseurServiceTest() {
		// Mock data
		FournisseurDTO fournisseurDTO = FournisseurDTO.builder().name("fournisseur").build();
		Fournisseur fournisseur = Fournisseur.builder().name("fournisseur").build();
		// Mocking the mapper
		when(fournisseurMapper.fromFournisseurDTOToFournisseur(fournisseurDTO)).thenReturn(fournisseur);
		when(fournisseurMapper.fromFournisseurToFournisseurDTO(any(Fournisseur.class))).thenReturn(fournisseurDTO);
		// Mocking the repository
		when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);
		FournisseurDTO saveFournisseurDTO = fournisseurServiceImpl.addFournisseurService(fournisseurDTO);
		// Verify interactions
		verify(fournisseurMapper, times(1)).fromFournisseurDTOToFournisseur(fournisseurDTO);
		verify(fournisseurRepository, times(1)).save(fournisseur);
		verify(fournisseurMapper, times(1)).fromFournisseurToFournisseurDTO(fournisseur);
		// Assert the result
		Assertions.assertThat(saveFournisseurDTO).isNotNull();
		Assertions.assertThat(saveFournisseurDTO).isEqualTo(fournisseurDTO);

	}

	@Test
	@Transactional
	public void getAllFournisseursServiceTest() {
		List<Fournisseur> fournisseurs = Arrays.asList(Fournisseur.builder().name("fournisseur1").build(),
				Fournisseur.builder().name("fournisseur2").build());
		when(fournisseurRepository.findAll()).thenReturn(fournisseurs);
		when(fournisseurMapper.fromFournisseurToFournisseurDTO(any(Fournisseur.class))).thenAnswer(invocation -> {
			Fournisseur argumentFournisseur = invocation.getArgument(0);
			return FournisseurDTO.builder().name(argumentFournisseur.getName()).build();
		});
		List<FournisseurDTO> resultDTOs = fournisseurServiceImpl.getAllFournisseursService();
		verify(fournisseurRepository, times(1)).findAll();
		verify(fournisseurMapper, times(fournisseurs.size())).fromFournisseurToFournisseurDTO(any(Fournisseur.class));
		assertNotNull(resultDTOs);
		assertEquals(fournisseurs.size(), resultDTOs.size());
		for (int i = 0; i < fournisseurs.size(); i++) {
			FournisseurDTO expectedDTO = FournisseurDTO.builder().name(fournisseurs.get(i).getName()).build();
			assertEquals(expectedDTO, resultDTOs.get(i));
		}
	}

	@Test
	@Transactional
	public void getFournisseurByIdService_ExistingId_ReturnsDTO() throws NotFoundException {
		Long existingId = 1L;
		Fournisseur existingFournisseur = Fournisseur.builder().id(existingId).name("fournisseur1").build();
		FournisseurDTO expectedDTO = FournisseurDTO.builder().id(existingFournisseur.getId())
				.name(existingFournisseur.getName()).build();
		when(fournisseurRepository.findById(existingId)).thenReturn(Optional.of(existingFournisseur));
		when(fournisseurMapper.fromFournisseurToFournisseurDTO(existingFournisseur)).thenReturn(expectedDTO);
		FournisseurDTO resultDTO = fournisseurServiceImpl.getFournisseurByIdService(existingId);
		verify(fournisseurRepository, times(1)).findById(existingId);
		verify(fournisseurMapper, times(1)).fromFournisseurToFournisseurDTO(existingFournisseur);
		assertNotNull(resultDTO);
		assertEquals(expectedDTO, resultDTO);
	}

	@Test
	@Transactional
	public void getFournisseurByIdService_NonExistingId_ThrowsNotFoundException() {
		Long nonExistingId = 2L;
		when(fournisseurRepository.findById(nonExistingId)).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> fournisseurServiceImpl.getFournisseurByIdService(nonExistingId));
		verify(fournisseurRepository, times(1)).findById(nonExistingId);
		verifyNoMoreInteractions(fournisseurMapper);
	}

	@Test
	public void deleteFournisseurServiceTest() throws NotFoundException {
		Long fournisseurId = 1L;
		when(fournisseurRepository.existsById(fournisseurId)).thenReturn(true);
		fournisseurServiceImpl.deleteFournisseurService(fournisseurId);
		verify(fournisseurRepository, times(1)).deleteById(fournisseurId);

	}

}
