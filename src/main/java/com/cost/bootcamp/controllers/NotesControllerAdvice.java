package com.cost.bootcamp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cost.bootcamp.exceptions.note.InvalidNoteException;

@ControllerAdvice
public class NotesControllerAdvice {
	
	@ExceptionHandler({InvalidNoteException.class})
	public ResponseEntity<String> invalidNoteException(InvalidNoteException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
