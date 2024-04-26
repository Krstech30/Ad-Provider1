package com.java.adProvider.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.adProvider.model.Login;
import com.java.adProvider.model.Role;
import com.java.adProvider.model.User;
import com.java.adProvider.model.UserRole;
import com.java.adProvider.repo.RoleRepository;
import com.java.adProvider.repo.UserRepository;
import com.java.adProvider.response.ResponseHandler;
import com.java.adProvider.service.UserService;
import com.java.adProvider.utility.Constant;



@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private RoleRepository roleRepository;


	@GetMapping
	public ResponseEntity<?> getUserList() {
		List<User> user = userService.getUser();
		if (user == null) {
			return null;
		}
		return ResponseHandler.responseBuilder(" Successfully Fetched List Of AdProviderDeatils", HttpStatus.OK.value(),
				user, HttpStatus.OK);

	}

	/*@GetMapping("/list")
	public ResponseEntity<?> getEntryDetails() {
		List<EntryDetails> ed = entryDetailsService.findAllWithProductAndPlan();
		return ResponseHandler.responseBuilder(" Successfully Fetched List Of EntryDeatils", HttpStatus.OK.value(), ed,
				HttpStatus.OK);
	}*/

	@PostMapping("/{register}")
	public ResponseEntity<?> AddUser(@RequestBody User user) throws Exception {
		// generatd uuid
		String user_id = UUID.randomUUID().toString();
		user.setUser_id(user_id);
		// date
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		user.setCreated_date(formatter.format(date));
		user.setCreated_by(user.getUsername());

		Role roles = new Role();
		String s = user.getRole();

		if (s.equals(Constant.User_Role_AdProvider)) {
			roles.setDescription(user.getRole());
			roles.setRoleId(44L);
		}
		if (s.equals(Constant.User_Role_AdViwer)) {
			roles.setDescription(user.getRole());
			roles.setRoleId(45L);
		}
		if (s.equals(Constant.User_Role_Admin)) {
			roles.setDescription(user.getRole());
			roles.setRoleId(46L);
		}
		roleRepository.save(roles);
//			roles.setCreated_by(user.getEmail());
//			roles.setCreated_date(formatter.format(date));
		UserRole userRole = new UserRole();

		userRole.setRoles(roles);
		userRole.setUser(user);
		userRole.setCreated_by(user.getEmail());
		userRole.setCreated_date(formatter.format(date));
		user.getUserRoles().add(userRole);
		// validation
		ResponseEntity<?> validationResult = validateUser(user);
		if (validationResult != null) {
			return validationResult;
		}
		// use isExist function to check record already exist or not
		User localUser = userRepository.findByUsername(user.getUsername());
		if (localUser != null) {
			return ResponseHandler.responseBuilder("User Already Exist", HttpStatus.CONFLICT.value(), null,
					HttpStatus.CONFLICT);
		}
		// confrom password

		User createuse = userService.createUser(user);
		return ResponseHandler.responseBuilder("You Are Registered Successfully", HttpStatus.OK.value(), createuse,
				HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> verifyUser(@RequestBody Login loginRequest) {
		try {
//				ResponseEntity<?> user1= userpasswordValidator(loginRequest);
			User user = userService.verifyUser(loginRequest);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "user verified successfully");
			response.put("username", loginRequest.getUsername());
			response.put("id", user.getId());
//	           response.put("password", loginRequest.getPassword());

			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", "You are not register..Please register first");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	// validation function validateUser
	public ResponseEntity<?> validateUser(User user) {
		if (user.getUsername() == null || user.getUsername().isEmpty() || user.getUsername() == "") {
			return ResponseHandler.responseBuilder("Username Must Be Required", HttpStatus.NOT_ACCEPTABLE.value(), null,
					HttpStatus.NOT_ACCEPTABLE);

		}
		if (user.getEmail() == null || user.getEmail() == "" || user.getEmail().isEmpty()) {

			return ResponseHandler.responseBuilder("Email Must Be Required", HttpStatus.NOT_ACCEPTABLE.value(), null,
					HttpStatus.NOT_ACCEPTABLE);
		}
		if (user.getPassword() == null || user.getPassword() == "" || user.getPassword().isEmpty()) {
			return ResponseHandler.responseBuilder("Password Must Be Required", HttpStatus.NOT_ACCEPTABLE.value(), null,
					HttpStatus.NOT_ACCEPTABLE);
		}
		return null;

	}

}