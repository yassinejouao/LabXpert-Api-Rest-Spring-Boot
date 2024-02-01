package yass.jouao.labx.services;

import java.util.List;

import yass.jouao.labx.DTOs.SampleDTO;
import yass.jouao.labx.entities.Sample;
import yass.jouao.labx.exeptions.NotFoundException;

public interface ISampleService {
	SampleDTO getSampleDTOByIdService(Long id) throws NotFoundException;

	SampleDTO addSampleService(SampleDTO s) throws NotFoundException;

	List<SampleDTO> getSamplesByIdPatient(Long id) throws NotFoundException;

	Sample getSampleByIdService(Long id) throws NotFoundException;

	List<SampleDTO> getAllSamplesService();

	SampleDTO updateSampleService(Long id, SampleDTO s) throws NotFoundException, IllegalAccessException;

}
