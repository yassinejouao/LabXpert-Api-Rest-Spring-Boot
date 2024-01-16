package yass.jouao.labx.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import yass.jouao.labx.entities.Sample;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.ISampleRepository;
import yass.jouao.labx.services.ISampleService;

public class SampleServiceImpl implements ISampleService {

	@Autowired
	private ISampleRepository sampleRepository;

	@Override
	public Optional<Sample> getSampleByIdService(Long id) {
		return sampleRepository.findById(id);
	}

	@Override
	public Sample addSampleService(Sample s) {
		return sampleRepository.save(s);
	}

	@Override
	public Sample updateSampleService(Sample s) {
		if (sampleRepository.existsById(s.getId())) {
			return sampleRepository.save(s);
		} else {
			throw new NotFoundException("you can't update unexist Sample");
		}
	}

}
