package yass.jouao.labx.services;

import yass.jouao.labx.entities.UserLab;
import yass.jouao.labx.exeptions.NotFoundException;

public interface IUserLabService {
	UserLab getUserLabByIdService(Long id) throws NotFoundException;

	UserLab updateUserLabService(UserLab u) throws NotFoundException;

	UserLab addUserLabService(UserLab u);
}
