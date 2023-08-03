package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.RoleEntity;
import com.repository.RoleRepository;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
	
	@Autowired
	RoleRepository rolerepo;
	
	@PostMapping("/addrole")
	public ResponseEntity<?> addRole(@RequestBody RoleEntity role)
	{
		rolerepo.save(role);
		return ResponseEntity.ok().body("Role Created Succesfully");
	}
	
	@GetMapping("/allrole")
	public ResponseEntity<?> getAllRole()
	{
		Map<String, Object> res=new HashMap<>();
		res.put("Data", rolerepo.findAll());
		res.put("Message", "All the Roles");
		return ResponseEntity.ok().body(res);
	}

}
