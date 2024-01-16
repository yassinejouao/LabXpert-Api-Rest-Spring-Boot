package yass.jouao.labx.services;

import yass.jouao.labx.entities.TestType;

import java.util.List;
import java.util.Optional;

public interface ITestTypeService {
    List<TestType> getAllTestTypesService();
    Optional<TestType> getTestTypeByIdService(Long id);
    TestType saveTestTypeService(TestType testType);
    TestType updateTestTypeService(long id, TestType testTypeRequest) ;
    void deleteTestTypeService(Long id);
}
