package com.cost.bootcamp.converters;

public interface Converter<Domain, Dto> {
	Domain toDomain(Dto dto);
	
	Dto toDto(Domain domain);
}
