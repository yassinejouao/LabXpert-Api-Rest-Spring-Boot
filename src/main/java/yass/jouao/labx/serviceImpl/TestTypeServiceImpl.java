package yass.jouao.labx.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yass.jouao.labx.entities.TestType;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.ITestTypeRepository;
import yass.jouao.labx.services.ITestTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class TestTypeServiceImpl implements ITestTypeService {
    @Autowired
    private ITestTypeRepository testTypeRepository;
    @Override
    public List<TestType> getAllTestTypesService() {
        return testTypeRepository.findAll();
    }
    @Override
    public Optional<TestType> getTestTypeByIdService(Long id) {
        if(testTypeRepository.findById(id).isPresent()){
            return testTypeRepository.findById(id);
        }
        else {
            throw new NotFoundException("TestType not exist");
        }
    }
    @Override
    public TestType saveTestTypeService(TestType testType){
        return testTypeRepository.save(testType);
    };
    @Override
    public TestType updateTestTypeService(long id, TestType testTypeRequest) {
        if(testTypeRepository.findById(id).isPresent()){
            return testTypeRepository.save(testTypeRequest);
        }
        else {
            throw new NotFoundException("TestType not exist");
        }
    }
    @Override
    public void deleteTestTypeService(Long id) {
        if(testTypeRepository.findById(id).isPresent()){
            testTypeRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("TestType not exist");
        }
    }
}
