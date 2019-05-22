package com.cost.bootcamp.services;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cost.bootcamp.converters.NoteConverter;
import com.cost.bootcamp.domain.Note;
import com.cost.bootcamp.dto.NoteDto;
import com.cost.bootcamp.repositories.NoteRepository;
import com.cost.bootcamp.validations.CompositeValidation;
import com.cost.bootcamp.validations.note.*;

@Service
public class NotesService {
	
	private final NoteConverter CONVERTER = NoteConverter.INSTANCE;
	private NoteRepository noteRepository;
	
	public NotesService(@Autowired NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}
	
	public ResponseEntity saveNote(NoteDto noteDto) {
		CompositeValidation validations = new CompositeValidation();
		validations.add(new IsDateSet(noteDto));
		validations.add(new IsTextSet(noteDto));
		//validations.add(new IsCreatorSet(noteDto));
		validations.add(() -> noteDto.getDate().isBefore(ZonedDateTime.now ()));
		validations.requireMatching();
		
		
		Note note = this.CONVERTER.toDomain(noteDto);
		note =this.noteRepository.save(note);
		
		noteDto.setId(note.getId());
		
		return ResponseEntity.created(URI.create("/"+noteDto.getId())).body(noteDto);
		
		
	}
	
	public ResponseEntity replaceNote(Integer id, NoteDto noteDto) {
		ZonedDateTime zdt = ZonedDateTime.now ( );
		if(noteDto.getDate()!=null && noteDto.getDate().isAfter(zdt)) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("No se ha podido modificar porque la fecha es posterior a la actual");
		}else if(noteDto.getText()==null || noteDto.getText().isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("No se ha podido modificar porque el text está vacío o es nulo.");
		}else {
			Note note =this.CONVERTER.toDomain(noteDto);
			this.noteRepository.saveAndFlush(note);
			
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(noteDto);
			
		}
	}
	
	public ResponseEntity patchNote(Integer id, NoteDto noteDto) {
		ZonedDateTime zdt = ZonedDateTime.now ( );
		if(noteDto.getDate()!=null && noteDto.getDate().isAfter(zdt)) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("No se ha podido modificar porque la fecha es posterior a la actual");
		}else if(noteDto.getText()!=null && noteDto.getText().isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("No se ha podido modificar porque el text está vacío.");
		}else {
			Note note =this.noteRepository.getOne(id);
			if(noteDto.getText()!=null) {
			note.setText(noteDto.getText());
			}else {
				noteDto.setText(note.getText());
			}
			if(noteDto.getDate()!=null) {
				note.setDate(noteDto.getDate());
			}else {
				noteDto.setDate(note.getDate());
			}
			noteDto.setId(id);
			note = this.noteRepository.saveAndFlush(note);
			
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(noteDto);
			
		}
	}
	
	public ResponseEntity<NoteDto> getNote(Integer id) {
		ResponseEntity<NoteDto> result;
		try {
			Note note= this.noteRepository.getOne(id);
			
			NoteDto dto = this.CONVERTER.toDto(note);
			result=ResponseEntity.ok(dto);
		}catch(EntityNotFoundException ex) {
			result =ResponseEntity.notFound().build();
		}
		return result;
		
		
		//return this.getAll().stream().filter(note -> note.getId().equals(id)).findFirst().get();
		//return this.getAll().get(id);
	}
	
	

	public ResponseEntity<List<NoteDto>> getAll() {
		List<NoteDto> result = new ArrayList<NoteDto>();
		List<Note> notes = this.noteRepository.findAll();
		
		
		result = notes.stream()
				.map(this.CONVERTER::toDto)
				.collect(Collectors.toList());
		
		
		
		return ResponseEntity.ok(result);
	}
	
	public ResponseEntity<NoteDto> delete(Integer id) {
		Note note= this.noteRepository.getOne(id);
		note.setActive(false);
		this.noteRepository.save(note);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<List<NoteDto>> filter(String pattern){
		List<Note> notes = this.noteRepository.findAll();
		List<NoteDto> notesResult = new ArrayList<NoteDto>();
		for (Note note : notes) {
			if(note.getText().contains(pattern) ) {
				NoteDto dto = this.CONVERTER.toDto(note);
				notesResult.add(dto);
			}
		}
		
		return ResponseEntity.ok(notesResult);
	}
}
