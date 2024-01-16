package yass.jouao.labx.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import yass.jouao.labx.entities.UserLab;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IUserLabRepository;
import yass.jouao.labx.services.IUserLabService;

public class UserLabServiceImpl implements IUserLabService {
	@Autowired
	private IUserLabRepository userLabRepository;

	@Override
	public Optional<UserLab> getUserLabByIdService(Long id) {
		return userLabRepository.findById(id);
	}

	@Override
	public UserLab updateUserLabService(UserLab u) {
		if (userLabRepository.existsById(u.getId())) {
			return userLabRepository.save(u);
		} else {
			throw new NotFoundException("you can't update unexist User");
		}
	}

}
