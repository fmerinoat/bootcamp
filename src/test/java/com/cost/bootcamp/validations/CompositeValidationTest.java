package com.cost.bootcamp.validations;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.cost.bootcamp.validations.CompositeValidation;

import org.junit.Assert;

public class CompositeValidationTest {
	@Test
	public void testMatchesAll() {
		CompositeValidation compositeValidation = new CompositeValidation();
		compositeValidation.add(() -> true);
		compositeValidation.add(() -> true);
		compositeValidation.add(() -> true);
		
		boolean result = compositeValidation.matches();
		
		Assert.assertThat(result, CoreMatchers.is(true));
	}
	
	@Test
	public void testMatchesFailOne() {
		CompositeValidation compositeValidation = new CompositeValidation();
		compositeValidation.add(() -> true);
		compositeValidation.add(() -> false);
		compositeValidation.add(() -> true);
		
		boolean result = compositeValidation.matches();
		
		Assert.assertThat(result, CoreMatchers.is(false));
	}
	
	
	@Test(expected = RuntimeException.class)
	public void testRequireMatching() {
		CompositeValidation compositeValidation = new CompositeValidation();
		compositeValidation.add(() -> true);
		compositeValidation.add(() -> false);
		compositeValidation.add(() -> true);
		
		compositeValidation.requireMatching();
	}
}
