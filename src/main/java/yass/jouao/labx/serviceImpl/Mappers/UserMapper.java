package yass.jouao.labx.serviceImpl.Mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.UserLabDTO;
import yass.jouao.labx.entities.UserLab;

@Service
public class UserMapper {
	public UserLabDTO fromUserToUserDTO(UserLab userLab) {
		UserLabDTO userLabDTO = new UserLabDTO();
		BeanUtils.copyProperties(userLab, userLabDTO);
		return userLabDTO;
	}

	public UserLab fromUserDTOToUser(UserLabDTO userLabDTO) {
		UserLab userLab = new UserLab();
		BeanUtils.copyProperties(userLabDTO, userLab);
		return userLab;
	}
}
