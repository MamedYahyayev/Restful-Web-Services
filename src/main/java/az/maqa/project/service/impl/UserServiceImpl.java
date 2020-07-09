package az.maqa.project.service.impl;

import java.util.ArrayList;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import az.maqa.project.exception.InvalidRequestData;
import az.maqa.project.exception.UserNotFoundException;
import az.maqa.project.model.User;
import az.maqa.project.response.ResponseMessage;
import az.maqa.project.response.ResponseUser;
import az.maqa.project.response.ResponseUserList;
import az.maqa.project.service.UserDaoService;

@RestController
public class UserServiceImpl {

	@Autowired
	private UserDaoService userService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping(value = "/users")
	public ResponseUserList getAllUsers() {
		ResponseUserList response = new ResponseUserList();
		List<ResponseUser> responseUsers = new ArrayList<>();
		List<User> users = userService.findAll();

		if (users == null)
			throw new UserNotFoundException("Users not found");

		responseUsers = setResponseToData(responseUsers, users);
		response.setUsers(responseUsers);

		return response;
	}

	@PostMapping(value = "/addUser" , consumes = "application/json")
	public ResponseMessage addUser(@Valid @RequestBody User user) {
		checkRequest(user);

		User savedUser = userService.saveUser(user);
		if (savedUser.getId() == null)
			return new ResponseMessage("User was not  added", 2);

		return new ResponseMessage("User added", 1);

	}

	@GetMapping(value = "user/{id}")
	public ResponseUser findUserById(@PathVariable int id) throws UserNotFoundException {
		ResponseUser responseUser = new ResponseUser();
		User user = userService.findById(Integer.valueOf(id));
		if (user == null)
			throw new UserNotFoundException("User not found: " + id);

		responseUser.setId(user.getId());
		responseUser.setName(user.getName());

		return responseUser;
	}

	@DeleteMapping(value = "user/{id}")
	public ResponseMessage deleteUser(@PathVariable int id) {
		if (id == 0)
			throw new InvalidRequestData("id not be zero");
		String deleteUser = userService.deleteUser(id);
		if (deleteUser == null)
			throw new UserNotFoundException("User not found");

		return new ResponseMessage("User deleted...", 1);
	}

	@GetMapping(value = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}

	private List<ResponseUser> setResponseToData(List<ResponseUser> responseUsers, List<User> users) {
		for (User user : users) {
			ResponseUser responseUser = new ResponseUser();
			responseUser.setId(user.getId());
			responseUser.setName(user.getName());
			responseUsers.add(responseUser);
		}
		return responseUsers;
	}

	private void checkRequest(User user) {
		if (user.getName() == null || user.getName().isEmpty())
			throw new InvalidRequestData("User's name  is null or empty");

	}

}
