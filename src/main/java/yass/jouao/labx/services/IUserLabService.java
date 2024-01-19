package yass.jouao.labx.services;

import yass.jouao.labx.DTOs.UserLabDTO;
import yass.jouao.labx.exeptions.NotFoundException;

public interface IUserLabService {
	UserLabDTO getUserLabByIdService(Long id) throws NotFoundException;

	UserLabDTO updateUserLabService(Long userId, UserLabDTO u) throws NotFoundException, IllegalAccessException;

	UserLabDTO addUserLabService(UserLabDTO u) throws NotFoundException;
}
