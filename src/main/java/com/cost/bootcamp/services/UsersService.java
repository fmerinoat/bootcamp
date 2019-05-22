package com.cost.bootcamp.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cost.bootcamp.converters.UserConverter;
import com.cost.bootcamp.domain.User;
import com.cost.bootcamp.dto.UserDto;
import com.cost.bootcamp.repositories.UserRepository;

@Service
public class UsersService {
	private final UserConverter CONVERTER = UserConverter.INSTANCE;
	private UserRepository userRepository;
	
	public UsersService(@Autowired UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public ResponseEntity saveUser(UserDto userDto) {
		User user = this.CONVERTER.toDomain(userDto);
		user =this.userRepository.save(user);
		
		userDto.setId(user.getId());
		
		return ResponseEntity.created(URI.create("/"+userDto.getId())).body(userDto);
	}
	
	public ResponseEntity replaceUser(Integer id, UserDto userDto) {
		
			User user =this.CONVERTER.toDomain(userDto);
			this.userRepository.saveAndFlush(user);
			
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(userDto);
			
	}
	
	
	public ResponseEntity<UserDto> getUser(Integer id) {
		ResponseEntity<UserDto> result;
		try {
			User user= this.userRepository.getOne(id);
			
			UserDto dto = this.CONVERTER.toDto(user);
			result=ResponseEntity.ok(dto);
		}catch(EntityNotFoundException ex) {
			result =ResponseEntity.notFound().build();
		}
		return result;
		
		
		//return this.getAll().stream().filter(user -> user.getId().equals(id)).findFirst().get();
		//return this.getAll().get(id);
	}
	
	

	public ResponseEntity<List<UserDto>> getAll() {
		List<UserDto> result = new ArrayList<UserDto>();
		List<User> users = this.userRepository.findAll();
		
		
		result = users.stream()
				.map(this.CONVERTER::toDto)
				.collect(Collectors.toList());
		
		
		
		return ResponseEntity.ok(result);
	}
	
	public ResponseEntity<UserDto> delete(Integer id) {
		User user= this.userRepository.getOne(id);
		user.setActive(false);
		this.userRepository.save(user);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<List<UserDto>> filter(String pattern){
		List<User> users = this.userRepository.findAll();
		List<UserDto> usersResult = new ArrayList<UserDto>();
		for (User user : users) {
			if(user.getUsername().contains(pattern) || user.getNombre().contains(pattern) || user.getEmail().contains(pattern)) {
				UserDto dto = this.CONVERTER.toDto(user);
				usersResult.add(dto);
			}
		}
		
		return ResponseEntity.ok(usersResult);
	}
}
