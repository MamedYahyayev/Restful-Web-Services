package az.maqa.project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import az.maqa.project.model.User;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();

	

	public List<User> findAll() {
		if(users.size() == 0)
			return null;
		return users;
	}

	public User findById(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User saveUser(User user) {
		int userId = (users.size() + 1);
		user.setId(Long.valueOf(userId));
		users.add(user);
		
		return user;
	}
	
	public String deleteUser(int id) {
		for (User user : users) {
			if(user.getId() == id-1) {
				users.remove(id-1);
				return "success";
			}
		}
		
		return null;
	}

}
