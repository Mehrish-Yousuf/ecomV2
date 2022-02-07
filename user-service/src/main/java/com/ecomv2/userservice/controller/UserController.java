package com.ecomv2.userservice.controller;

import com.ecomv2.userservice.DTO.UserDTO;
import com.ecomv2.userservice.entity.User;
import com.ecomv2.userservice.header.HeaderGenerator;
import com.ecomv2.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private HeaderGenerator headerGenerator;
    
    @GetMapping (value = "/getAll")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users =  userService.getAllUsers();
        if(!users.isEmpty()) {
        	return new ResponseEntity<List<User>>(
        		users,
        		headerGenerator.getHeadersForSuccessGetMethod(),
        		HttpStatus.OK);
        }
        return new ResponseEntity<List<User>>(
        		headerGenerator.getHeadersForError(),
        		HttpStatus.NOT_FOUND);
    }

    @GetMapping (value = "/getByName", params = "name")
    public ResponseEntity<User> getUserByName(@RequestParam("name") String userName){
    	User user = userService.getUserByName(userName);
    	if(user != null) {
    		return new ResponseEntity<User>(
    				user,
    				headerGenerator.
    				getHeadersForSuccessGetMethod(),
    				HttpStatus.OK);
    	}
        return new ResponseEntity<User>(
        		headerGenerator.getHeadersForError(),
        		HttpStatus.NOT_FOUND);
    }

    @GetMapping (value = "/getById/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id){
        UserDTO user = userService.getUserById(id);
        if(user != null) {
    		return new ResponseEntity<UserDTO>(
    				user,
    				headerGenerator.
    				getHeadersForSuccessGetMethod(),
    				HttpStatus.OK);
    	}
        return new ResponseEntity<UserDTO>(
        		headerGenerator.getHeadersForError(),
        		HttpStatus.NOT_FOUND);
    }

    @PostMapping (value = "/add")
    public ResponseEntity<User> addUser(@RequestBody User user, HttpServletRequest request){
    	if(user != null)
    		try {
    			userService.saveUser(user);
    			return new ResponseEntity<User>(
    					user,
    					headerGenerator.getHeadersForSuccessPostMethod(request, user.getId()),
    					HttpStatus.CREATED);
    		}catch (Exception e) {
    			e.printStackTrace();
    			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

	@PostMapping (value = "/update")
	public ResponseEntity<User> updateUser(@RequestBody User user, HttpServletRequest request){
		if(user != null)
			try {
				userService.updateUser(user);
				return new ResponseEntity<User>(
						user,
						headerGenerator.getHeadersForSuccessPostMethod(request, user.getId()),
						HttpStatus.CREATED);
			}catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping (value = "/getByActiveStatus/{active}")
	public ResponseEntity<List<User>> getUserByActiveStatus(@PathVariable("active") int active){
		List<User> users = userService.getUserByActiveStatus(active);
		if(users != null) {
			return new ResponseEntity<List<User>>(
					users,
					headerGenerator.
							getHeadersForSuccessGetMethod(),
					HttpStatus.OK);
		}
		return new ResponseEntity<List<User>>(
				headerGenerator.getHeadersForError(),
				HttpStatus.NOT_FOUND);
	}

}
