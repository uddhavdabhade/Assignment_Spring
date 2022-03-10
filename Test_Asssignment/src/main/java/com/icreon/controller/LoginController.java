package com.icreon.controller;

import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icreon.UserDao.reposervice;
import com.icreon.model.User;
import com.icreon.service.EmailService;

@Controller
public class LoginController {

	  Random random = new Random(989898);
	  
	@Autowired
	User user;

	@Autowired
	reposervice repo;
	
	@Autowired
	EmailService service ;

	@RequestMapping("/login")
	public String logIn(String username, String password, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		user = repo.getUsersByEmailandPassword(username, password);

		if (user == null) {

			return "login.jsp";
		} else {

			return "welcome.jsp";

		}
	}

	
//	@RequestMapping("/forgot")
//	public String forgotPassword() {
//		
//		
//		
//		return "forgot_password.jsp";
//	}
	
	
	@RequestMapping("/forgot")
	public String openEmail() {


	return "forgot_password.jsp";


	}

	@RequestMapping("/sendOtp")
	public String send(@RequestParam("email")String email,HttpSession session) throws MessagingException {
	System.out.println("email"+email);

	
	

	int otp = random.nextInt(123456);
	System.out.println("otp"+otp);


	//code for send otp
	String subject="OTP FROM SYSTEM";
	String message="<h1>OTP="+otp+"</h1>";
	String to=email;



	boolean flag = this.service.sendEmail(subject,message,to);
	if(flag) {
	return "changepass.jsp";
	}else {


	session.setAttribute("message","check your emailid");


	return "verifyotp.jsp";

	}



	}
}
