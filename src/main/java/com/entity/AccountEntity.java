package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="accounts")
@Getter
@Setter
public class AccountEntity {
	
	@Id
	@Column(name="accountId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer accountId;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty(access=Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name="userId")
	UserEntity user;
	
	String userName;

}
