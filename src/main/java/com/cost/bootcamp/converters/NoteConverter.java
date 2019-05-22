package com.cost.bootcamp.converters;

import com.cost.bootcamp.domain.Note;
import com.cost.bootcamp.dto.NoteDto;

public class NoteConverter implements Converter <Note, NoteDto>{
	
	public static final NoteConverter INSTANCE = new NoteConverter();
	
	private NoteConverter () {}

	@Override
	public Note toDomain(NoteDto dto) {
		Note note = new Note();
		note.setId(dto.getId());
		note.setText(dto.getText());
		note.setDate(dto.getDate());
		note.setActive(dto.isActive());
		note.setCreator(dto.getCreator());
		return note;
	}

	@Override
	public NoteDto toDto(Note note) {
		NoteDto dto = new NoteDto();
		dto.setId(note.getId());
		dto.setText(note.getText());
		dto.setDate(note.getDate());
		dto.setActive(note.isActive());
		dto.setCreator(note.getCreator());
		return dto;
	}

}
