package com.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
	
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer userId;
	
	@Column(name="firstName",length = 200,unique = true) //default 255
	String firstName; // firstName varchar(255)
	String lastName;
	String email;
	String password;
	Integer age;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	Boolean flag;
	
	@OneToOne
	@JoinColumn(name="roleId",unique = false)
	RoleEntity role;
	
	@OneToMany(mappedBy = "user")
	List<AccountEntity> accounts;
//	here because one user has many accounts so thus onetomany and therefore the userId appears to be in account entity
//	here only the mapping of accounts because it comes as a list of accounts 
//	and here we can't place joinColumn becuse to join it creates 3rd table which is inefficient
//	here we want accounts details with the user by user.accounts ao thus we need to do this mapping
	
	
	@ManyToMany
	@JoinTable(name="user_course",joinColumns = @JoinColumn(name="userId"),inverseJoinColumns = @JoinColumn(name="courseId"))
	List<CourseEntity> course;
	
	
//	juggad
//	@ManyToMany(mappedBy="user1")
//	List<CourseEntity> course;

}
