package com.cost.bootcamp.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cost.bootcamp.dto.NoteDto;
import com.cost.bootcamp.services.NotesService;

@RestController
@RequestMapping("/notes")
public class NotesController {
	
	private NotesService notesService;
	
	public NotesController(@Autowired NotesService notesService) {
		this.notesService = notesService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NoteDto> getNote(@PathVariable("id") Integer id) {
		//return this.getAll().stream().filter(note -> note.getId().equals(id)).findFirst().get();
		//return this.getAll().get(id);
		return this.notesService.getNote(id);
	}
	
	/*
	@GetMapping("")
	public ResponseEntity<List<NoteDto>> getAll() {
		return this.notesService.getAll();
	}*/
	
	@GetMapping("")
	public ResponseEntity<List<NoteDto>> getAllFiltered(@RequestParam(value="filtro", required= false, defaultValue="") String filtro ) {
		return this.notesService.filter(filtro);
	}
	
	@PostMapping("")
	public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto note) {
		return this.notesService.saveNote(note);
	}
	
	@PutMapping("/{id}/replace")
	public ResponseEntity<NoteDto> replaceNote(@PathVariable("id") Integer id, @RequestBody NoteDto note) {
		return this.notesService.replaceNote(id, note);
	}
	
	@PatchMapping("/{id}/update")
	public ResponseEntity<NoteDto> patchNote(@PathVariable("id") Integer id, @RequestBody NoteDto note) {
		return this.notesService.patchNote(id, note);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<NoteDto> deleteNote(@PathVariable("id") Integer id) {
		return this.notesService.delete(id);
	}
	
}
