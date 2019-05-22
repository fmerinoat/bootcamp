package com.cost.bootcamp.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CompositeValidation implements Validation {

	private List<Validation> validations = new ArrayList<>();
	
	public CompositeValidation add (Validation validation) {
		this.validations.add(validation);
		
		return this;
	}
	
	@Override
	public boolean matches() {
		
		return this.validations.stream().allMatch(Validation::matches);
	}

	@Override
	public Supplier<? extends RuntimeException> exceptionSupplier() {
		return this.validations.stream()
				.filter(validation -> !validation.matches())
				.map(Validation::exceptionSupplier)
				.findFirst()
				.get();
	}
	
	

}
