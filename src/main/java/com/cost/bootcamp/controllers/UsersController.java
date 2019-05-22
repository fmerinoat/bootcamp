package com.cost.bootcamp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cost.bootcamp.dto.UserDto;
import com.cost.bootcamp.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
private UsersService usersService;
	
	public UsersController(@Autowired UsersService usersService) {
		this.usersService = usersService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer id) {
		//return this.getAll().stream().filter(user -> user.getId().equals(id)).findFirst().get();
		//return this.getAll().get(id);
		return this.usersService.getUser(id);
	}
	
	/*
	@GetMapping("")
	public ResponseEntity<List<UserDto>> getAll() {
		return this.usersService.getAll();
	}*/
	
	@GetMapping("")
	public ResponseEntity<List<UserDto>> getAllFiltered(@RequestParam(value="filtro", required= false, defaultValue="") String filtro ) {
		return this.usersService.filter(filtro);
	}
	
	@PostMapping("")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
		return this.usersService.saveUser(user);
	}
	
	@PutMapping("/{id}/replace")
	public ResponseEntity<UserDto> replaceUser(@PathVariable("id") Integer id, @RequestBody UserDto user) {
		return this.usersService.replaceUser(id, user);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Integer id) {
		return this.usersService.delete(id);
	}
}
