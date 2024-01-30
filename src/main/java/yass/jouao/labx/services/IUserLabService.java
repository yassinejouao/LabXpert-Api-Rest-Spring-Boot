package yass.jouao.labx.services;

import java.util.List;

import yass.jouao.labx.DTOs.UserLabDTO;
import yass.jouao.labx.exeptions.NotFoundException;

public interface IUserLabService {
	List<UserLabDTO> getAllUserLabService();

	UserLabDTO getUserLabByIdService(Long id) throws NotFoundException;

	UserLabDTO updateUserLabService(Long userId, UserLabDTO u) throws NotFoundException, IllegalAccessException;

	UserLabDTO addUserLabService(UserLabDTO u) throws NotFoundException;
}
