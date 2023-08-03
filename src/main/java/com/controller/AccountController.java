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

import com.entity.AccountEntity;
import com.repository.AccountRepository;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
	
	@Autowired
	AccountRepository accRepo;
	
	@PostMapping("/addaccount")
	public ResponseEntity<?> addAcount(@RequestBody AccountEntity account)
	{
		accRepo.save(account);
		return ResponseEntity.ok().body("Accounts Added");
	}
	
	@GetMapping("/getaccounts")
	public ResponseEntity<?> getAccounts()
	{
		Map<String, Object> res=new HashMap<>();
		res.put("Data", accRepo.findAll());
		res.put("Message", "All the Accounts");
		return ResponseEntity.ok().body(res);
	}
	
}
