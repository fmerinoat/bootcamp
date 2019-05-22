package com.cost.bootcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cost.bootcamp.domain.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{
	
}
