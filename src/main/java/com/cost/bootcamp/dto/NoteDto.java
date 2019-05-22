package com.cost.bootcamp.dto;

import java.time.ZonedDateTime;

import com.cost.bootcamp.domain.User;

public class NoteDto {
	private Integer id;
	private String text;
	private User creator;
	private ZonedDateTime date;
	private boolean active;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ZonedDateTime getDate() {
		return date;
	}
	public void setDate(ZonedDateTime date) {
		this.date = date;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	@Override
	public String toString() {
		return "NoteDto [id=" + id + ", text=" + text + ", date=" + date + "]+<br/>";
	}
	
}
