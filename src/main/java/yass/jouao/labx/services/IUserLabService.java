package yass.jouao.labx.services;

import java.util.Optional;

import yass.jouao.labx.entities.UserLab;
import yass.jouao.labx.exeptions.NotFoundException;

public interface IUserLabService {
	Optional<UserLab> getUserLabByIdService(Long id);

	UserLab updateUserLabService(UserLab u) throws NotFoundException;
}
