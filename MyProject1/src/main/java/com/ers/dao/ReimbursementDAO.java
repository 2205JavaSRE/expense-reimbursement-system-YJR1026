package com.ers.dao;

import java.util.List;

import com.ers.models.Employee;
import com.ers.models.Manager;
import com.ers.models.Reimbursement;

public interface ReimbursementDAO {
	
	public void createNewReimbursment(Reimbursement r);
	
	public boolean employeeLogin(Employee u);
	
	public List<Reimbursement> allReimbursmentById();
	
	public List<Reimbursement> reimburstmentByUsername(String username);
	
	public List<Reimbursement> reimburstmentByUsernameandStatus(String username, String status);
	
	public boolean managerLogin(Manager m);
	
	public void paymentStatusuUpgrade(int id, String status);
	
	public List<Reimbursement> allReimbursment();
	
	public List<Reimbursement> reimburstmentAllByUsername(String username);
}
