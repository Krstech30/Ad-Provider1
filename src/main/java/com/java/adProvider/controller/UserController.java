package com.java.adProvider.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.adProvider.model.ImageModel;
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
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
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

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable(name = "id") Long id) {
		User user = userService.findByUserId(id);
		return ResponseHandler.responseBuilder(" Successfully Fetched List Of AdProviderDeatils", HttpStatus.OK.value(),
				user, HttpStatus.OK);

	}

	@GetMapping("/phone/{phone}")
	public ResponseEntity<?> getUserByPhone(@PathVariable(name = "phone") String phone) {
		User user = userService.findByPhone(phone);
		return ResponseHandler.responseBuilder(" Successfully Fetched List Of AdProviderDeatils", HttpStatus.OK.value(),
				user, HttpStatus.OK);

	}

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
//		roles.setCreated_by(user.getEmail());
//		roles.setCreated_date(formatter.format(date));
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
//			ResponseEntity<?> user1= userpasswordValidator(loginRequest);
			User user = userService.verifyUser(loginRequest);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "user verified successfully");
			if(loginRequest.getUsername()!=null) {
			response.put("username", loginRequest.getUsername());
			response.put("id", user.getId());
			}
			if(loginRequest.getEmail()!=null) {
			response.put("email", loginRequest.getEmail());
			response.put("id", user.getId());
			}

			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", "You are not register..Please register first");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUserImage(@PathVariable("id") Long id,
			@RequestPart(value = "imageFile") MultipartFile[] imageFile, @RequestPart("user") String user) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		try {
			User existingUser = userRepository.findById(id).orElseThrow();
			if (existingUser == null) {
				return ResponseEntity.notFound().build();
			}
			User user3 = mapper.readValue(user, User.class);
			existingUser.setName(user3.getName());
			existingUser.setUsername(user3.getUsername());
			existingUser.setPhone(user3.getPhone());
			existingUser.setEmail(user3.getEmail());
			existingUser.setPassword(user3.getPassword());
			existingUser.setConformPassword(user3.getConformPassword());
			existingUser.setModified_by(user3.getModified_by());
			existingUser.setModified_date(formatter.format(date));
			if (imageFile != null && imageFile.length > 0) {
	            Set<ImageModel> existingImages = existingUser.getImages();
	            if (existingImages != null) {
	                existingImages.clear(); // Clear existing images
	            }
	            Set<ImageModel> newImages = uploadImage(imageFile);
	            existingUser.setImages(newImages);
	        }

			User updatedUser = userService.updateImage(id, existingUser, imageFile);

			if (updatedUser != null) {
				return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);

			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed to update Image: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("failed to update property: " + e.getMessage());
		}

	}

	// confirm password function
	public ResponseEntity<?> confromPassword(User user) {
		String password = user.getPassword();
		String confpass = user.getConformPassword();
		if (password != null || !confpass.equals(confpass)) {
			return ResponseHandler.responseBuilder("password does not macth", HttpStatus.NOT_ACCEPTABLE.value(), null,
					HttpStatus.NOT_ACCEPTABLE);

		} else {
			return ResponseHandler.responseBuilder("password  macth", HttpStatus.NOT_ACCEPTABLE.value(), null,
					HttpStatus.NOT_ACCEPTABLE);

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

	// image function
	public Set<ImageModel> uploadImage(MultipartFile[] multipartFile) throws IOException {
		Set<ImageModel> images = new HashSet<>();
		for (MultipartFile file : multipartFile) {
			ImageModel imageModel = new ImageModel(file.getOriginalFilename(), file.getBytes(), file.getContentType());
			images.add(imageModel);
		}
		return images;
	}

}
