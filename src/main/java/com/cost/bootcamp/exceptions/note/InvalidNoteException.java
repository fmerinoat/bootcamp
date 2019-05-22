package com.cost.bootcamp.exceptions.note;

public class InvalidNoteException extends RuntimeException {
	public InvalidNoteException(String field) {
		super("Field: " +field + " is not valid");
	}
}
