package com.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="course")
public class CourseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer courseId;
	
	String courseName;
	
	
	@ManyToMany(mappedBy = "course")
	List<UserEntity> user;
	
	
	
//	jugaad
//	@ManyToMany
//	@JoinColumn(name="userId")
//	List<UserEntity> user1;

}
