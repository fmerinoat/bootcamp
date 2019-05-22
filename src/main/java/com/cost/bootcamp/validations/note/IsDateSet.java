package com.cost.bootcamp.validations.note;

import java.util.function.Supplier;

import com.cost.bootcamp.dto.NoteDto;
import com.cost.bootcamp.exceptions.note.InvalidNoteException;
import com.cost.bootcamp.validations.Validation;

public class IsDateSet implements Validation{
	
	private NoteDto note;
	
	public IsDateSet(NoteDto note) {
		this.note = note;
	}
	
	@Override
	public boolean matches() {
		return this.note.getDate() != null;
	}

	@Override
	public Supplier<InvalidNoteException> exceptionSupplier() {
		return () -> new InvalidNoteException("Date");
	}
	
	
}
