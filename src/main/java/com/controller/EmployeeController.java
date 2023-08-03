package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.FacultyEntity;
import com.entity.ManagementEntity;
import com.entity.ResponseBean;
import com.repository.FacultyRepository;
import com.repository.ManagementRepository;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
	
	@Autowired
	FacultyRepository objFac;
	@Autowired
	ManagementRepository objMan;
	

	@PostMapping("/Faculty")
	public ResponseEntity<ResponseBean<FacultyEntity>> addFaculty(@RequestBody FacultyEntity f){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseBean<FacultyEntity>("added successfully!!!", objFac.save(f)));
		}catch(Exception e) {
			
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseBean<FacultyEntity>("not added!", null));
		}
	}
	
	@PostMapping("/Managment")
	public ResponseEntity<ResponseBean<ManagementEntity>> addManagment(@RequestBody ManagementEntity m){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseBean<ManagementEntity>("added successfully!!!", objMan.save(m)));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseBean<ManagementEntity>("not added!", null));
		}
	}

	
	/*
	 * image En  -> IId , url
	 * 
	 * userImage extends image
	 * userId
	 * 
	 * categoryImage extends image
	 * categoryId
	 * 
	 * 
	 * 
	*/

}
