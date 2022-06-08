package com.ers.service;

import com.ers.dao.ImplReimbursementDAO;
import com.ers.dao.ReimbursementDAO;
import com.ers.models.Employee;
import com.ers.models.Manager;

import io.javalin.http.Context;

public class AuthenticationService {
	
	private static ReimbursementDAO rDao = new ImplReimbursementDAO();
	
	public static boolean authenticateUser(String username, String password) {
		
		Employee newUser = new Employee(-1, username, password);
		
		Boolean access = rDao.employeeLogin(newUser);
		
		return access;
		
	}
	public static boolean authenticateManger(String username, String password) {
		
		Manager newManager = new Manager(username, password);
		
		Boolean access = rDao.managerLogin(newManager);

		return access;
		
	}
	
	
	
	
}
