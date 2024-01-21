package yass.jouao.labx.serviceImpl.Mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.TestDTO;
import yass.jouao.labx.entities.Test;

@Service
public class TestMapper {
	public TestDTO fromTestToTestDTO(Test test) {
		TestDTO testDTO = new TestDTO();
		BeanUtils.copyProperties(test, testDTO);
		return testDTO;
	}

	public Test fromTestDTOToTest(TestDTO testDTO) {
		Test test = new Test();
		BeanUtils.copyProperties(testDTO, test);
		return test;
	}
}
