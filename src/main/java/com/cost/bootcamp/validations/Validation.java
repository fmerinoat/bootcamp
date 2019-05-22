package com.cost.bootcamp.validations;

import java.util.function.Supplier;


@FunctionalInterface
public interface Validation {
	boolean matches();
	
	default void requireMatching() {
		if (!this.matches()) {
			throw this.exceptionSupplier().get();
		}
	}
	
	default Supplier<? extends RuntimeException> exceptionSupplier(){
		return () -> new RuntimeException("Not valid");
	}
}
