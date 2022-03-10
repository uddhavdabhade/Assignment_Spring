package com.icreon.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icreon.model.User;

@Service
public class reposervice {

	@Autowired
	UserDao dao ;
	
	public User getUsersByEmailandPassword(String username, String password) {
		dao = new UserDao(Dbconnection.getConnection());
		return UserDao.getUserByUsernameAndPassword(username, password);
	}
}
