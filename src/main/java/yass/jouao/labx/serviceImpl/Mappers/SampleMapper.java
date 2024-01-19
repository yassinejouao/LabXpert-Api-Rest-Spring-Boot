package yass.jouao.labx.serviceImpl.Mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.SampleDTO;
import yass.jouao.labx.entities.Sample;

@Service
public class SampleMapper {

	public SampleDTO fromSampleToSampleDTO(Sample sample) {
		SampleDTO sampleDTO = new SampleDTO();
		BeanUtils.copyProperties(sample, sampleDTO);
		return sampleDTO;
	}

	public Sample fromSampleDTOToSample(SampleDTO sampleDTO) {
		Sample sample = new Sample();
		BeanUtils.copyProperties(sampleDTO, sample);
		return sample;
	}
}
