package com.cost.bootcamp.converters;

import com.cost.bootcamp.domain.User;
import com.cost.bootcamp.dto.UserDto;

public class UserConverter implements Converter <User, UserDto>{

	public static final UserConverter  INSTANCE = new UserConverter();
	
	private UserConverter() {}
	
	@Override
	public User toDomain(UserDto dto) {
		User user= new User();
		user.setId(dto.getId());
		user.setNombre(dto.getNombre());
		user.setEmail(dto.getEmail());
		user.setFechaNacimiento(dto.getFechaNacimiento());
		user.setNotes(dto.getNotes());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		return user;
	}

	@Override
	public UserDto toDto(User user) {
		UserDto dto= new UserDto();
		dto.setId(user.getId());
		dto.setNombre(user.getNombre());
		dto.setEmail(user.getEmail());
		dto.setFechaNacimiento(user.getFechaNacimiento());
		dto.setNotes(user.getNotes());
		dto.setUsername(user.getUsername());
		dto.setPassword(user.getPassword());
		return dto;
	}

}
