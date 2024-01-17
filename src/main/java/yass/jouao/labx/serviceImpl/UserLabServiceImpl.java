package yass.jouao.labx.serviceImpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.entities.UserLab;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IUserLabRepository;
import yass.jouao.labx.services.IUserLabService;

@Service
public class UserLabServiceImpl implements IUserLabService {
	@Autowired
	private IUserLabRepository userLabRepository;

	@Override
	@Transactional
	public Optional<UserLab> getUserLabByIdService(Long id) {
		return userLabRepository.findById(id);
	}

	@Override
	@Transactional
	public UserLab updateUserLabService(UserLab u) {
		if (userLabRepository.existsById(u.getId())) {
			return userLabRepository.save(u);
		} else {
			throw new NotFoundException("you can't update unexist User");
		}
	}

}
