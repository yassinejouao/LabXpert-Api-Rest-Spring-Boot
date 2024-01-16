package yass.jouao.labx.services;

import java.util.Optional;

import yass.jouao.labx.entities.Sample;

public interface SampleService {
	Optional<Sample> getSampleByIdService(Long id);

	Sample addSampleService(Sample s);

	Sample updateSampleService(Sample s);

}
