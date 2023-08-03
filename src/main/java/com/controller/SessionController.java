package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.entity.ResponseBean;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.repository.RoleRepository;
import com.repository.Users;
import com.vo.UserDepartmentVo;

@RestController
@RequestMapping("/api/v1/users")
public class SessionController {

	@Autowired
	Users udao;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserEntity user) {
//		RoleEntity role=roleRepo.findById(1).get();
//		this is simple and direct way to add role
//		now in the frontend side he wiil chhose through dropdown and pass directly the role id
//		so we will pass by role.roleId in frontend
//		and here get it by...
//		System.out.println(role);
		Optional<RoleEntity> role=roleRepo.findById(user.getRole().getRoleId());
		if(role.isPresent())
		{			
			user.setRole(role.get());
			udao.save(user);
		}
		else {
			return ResponseEntity.notFound().build();
		}
//		user=udao.findByEmail(user.getEmail());
//		System.out.println(user.getFirstName());
		return ResponseEntity.ok(user);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		List<UserEntity> user = udao.findAll();
		return ResponseEntity.ok().body(user);
	}

	@GetMapping("/findbyemail/{email}")
	public ResponseEntity<?> getByEmail(@PathVariable("email") String email) {
		Optional<UserEntity> user = udao.findByEmail(email);
		System.out.println(user);
		if (user.isPresent() == false) {
//		 invalid email
			Map<String, Object> resp = new HashMap<String, Object>();
			resp.put("data", email);
			resp.put("msg", "Email Not Present");
			return ResponseEntity.accepted().body(resp);
		} else {
			return ResponseEntity.ok(user.get());
		}
	}
	
	@GetMapping("/byid/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable("userId") Integer userId) {

		Optional<UserEntity> userOptional = udao.findById(userId);

		if (userOptional.isPresent() == false) {
			// invalid id
			Map<String, Object> resp = new HashMap<String, Object>();
			resp.put("data", userId);
			resp.put("msg", "Invalid UserId");
			return ResponseEntity.accepted().body(resp);
		} else {
			return ResponseEntity.ok(userOptional.get());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id)
	{
		Map<String, Object> res= new HashMap<String,Object>();
		try {
			udao.findByAgeBetween(1,12);
			udao.deleteById(id);
			res.put("msg", "User Deleted");
			res.put("Data", id);
			return ResponseEntity.ok().body(res);
		}
		catch(Exception e)
		{
			res.put("msg", "SMW");
			res.put("userId", id);
			res.put("errorMsg",e.getMessage());
			return ResponseEntity.ok(res);
		}
	}
	@GetMapping("/age")
	public ResponseEntity<?> getAge(@RequestParam("minAge") Integer minAge,@RequestParam("maxAge") Integer maxAge)
	{
		List<UserEntity> user=udao.findByAgeBetween(minAge, maxAge);
		Map<String, Object> resp = new HashMap<String, Object>();
		if(user.size()==0) {
			resp.put("msg", "No Data Found");
			resp.put("data",user);
		}
		else {
			resp.put("msg", "Data Found");
			resp.put("data",user);
		}
		return ResponseEntity.ok().body(resp);
	}
	
	@PutMapping
	public ResponseEntity<ResponseBean<UserEntity>> updateUser(@RequestBody UserEntity users)
	{
		ResponseBean<UserEntity> res=new ResponseBean<UserEntity>("User Updated Successfully",udao.save(users));
//		res.setData(udao.save(users));
//		res.setMessage("User Updated Successfully");
		return ResponseEntity.ok(res);
	}
	
	@GetMapping("/readdepartment")
	public ResponseEntity<?> readDepartmentFromDeptService()
	{
		//java class calls java api
		//call api -->url -->service -->department
		ResponseEntity<?> res=  restTemplate.getForEntity("http://DEPARTMENT-SERVICE/api/v1/department/get", List.class);
		System.out.println(res);
		List<UserDepartmentVo> departments=(List<UserDepartmentVo>)res.getBody();
		
		return ResponseEntity.ok(departments);
	}
	
	
	
	
	
	

}
