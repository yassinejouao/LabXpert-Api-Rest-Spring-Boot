package yass.jouao.labx.serviceImpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.entities.Sample;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.ISampleRepository;
import yass.jouao.labx.services.ISampleService;

@Service
public class SampleServiceImpl implements ISampleService {

	@Autowired
	private ISampleRepository sampleRepository;

	@Override
	@Transactional
	public Optional<Sample> getSampleByIdService(Long id) {
		return sampleRepository.findById(id);
	}

	@Override
	@Transactional
	public Sample addSampleService(Sample s) {
		return sampleRepository.save(s);
	}

	@Override
	@Transactional
	public Sample updateSampleService(Sample s) throws NotFoundException {
		if (sampleRepository.existsById(s.getId())) {
			return sampleRepository.save(s);
		} else {
			throw new NotFoundException("you can't update unexist Sample");
		}
	}

}
