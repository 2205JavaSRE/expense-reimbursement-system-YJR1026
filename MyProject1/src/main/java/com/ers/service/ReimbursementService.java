package com.ers.service;

import java.util.List;

import com.ers.dao.ImplReimbursementDAO;
import com.ers.dao.ReimbursementDAO;
import com.ers.models.Reimbursement;

public class ReimbursementService {
	
	private static ReimbursementDAO rDao = new ImplReimbursementDAO();
	
	public void requestSubmit(String username, double totalCost, String expenseType, String paymentType) {
		
		Reimbursement newReimbursment = new Reimbursement(-1, -1, username, totalCost, expenseType, paymentType);
		
		rDao.createNewReimbursment(newReimbursment);
	}
	
	public List<Reimbursement> allReimbursment(){
		
		List<Reimbursement> reimbursmentList = rDao.allReimbursmentById();
		
		for(Reimbursement r : reimbursmentList) {
		}
		
		
		return reimbursmentList;
		
	}
	
	
	public List<Reimbursement> reimburstmentByUsername(String username) {
		
		List<Reimbursement> reimbursmentListByUsername = rDao.reimburstmentByUsername(username);
		
		
		
		return reimbursmentListByUsername;
		
	}
	
	
	public List<Reimbursement> reimburstmentByUsernameandStatus(String username, String status) {
		
		List<Reimbursement> reimbursmentListByStatus = rDao.reimburstmentByUsernameandStatus(username,status);
		
		return reimbursmentListByStatus;
		
	}


	public void paymentUpdate(int id, String Status) {
		
		rDao.paymentStatusuUpgrade(id, Status);
		
	}


	public List<Reimbursement> allReimbursement(){
		
		List<Reimbursement> reimbursmentList = rDao.allReimbursment();
		
		for(Reimbursement r : reimbursmentList) {
		}
		
		
		return reimbursmentList;
		
	}
	
	
	public List<Reimbursement> reimburstmentAllByUsername(String username) {
		
		List<Reimbursement> reimbursmentListByUsername = rDao.reimburstmentAllByUsername(username);
		
		
		
		return reimbursmentListByUsername;
		
	}




}
