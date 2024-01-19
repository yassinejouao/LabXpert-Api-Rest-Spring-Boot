package yass.jouao.labx.serviceImpl.Mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import yass.jouao.labx.DTOs.TestTypeDTO;
import yass.jouao.labx.entities.TestType;

@Service
public class TestTypeMapper {
    public TestTypeDTO fromTestTypeToTestTypeDTO(TestType testType) {
        TestTypeDTO testTypeDTO = new TestTypeDTO();
        BeanUtils.copyProperties(testType, testTypeDTO);
        return testTypeDTO;
    }
    public TestType fromTestTypeDTOToTestType(TestTypeDTO testTypeDTO) {
        TestType testType = new TestType();
        BeanUtils.copyProperties(testTypeDTO, testType);
        return testType;
    }
}
