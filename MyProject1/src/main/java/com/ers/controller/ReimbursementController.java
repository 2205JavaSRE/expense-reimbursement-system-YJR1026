package com.ers.controller;

import java.util.List;

import org.eclipse.jetty.http.HttpStatus;

import com.ers.models.Employee;
import com.ers.models.Manager;
import com.ers.models.Reimbursement;
import com.ers.service.ReimbursementService;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class ReimbursementController {
	
	private ReimbursementService rService =new ReimbursementService();
	
	public void requestSubmit(Context ctx) {
		
		Reimbursement jsonReimbursement =ctx.bodyAsClass(Reimbursement.class);
		
		Employee employee = AuthenticationController.getEmployee(ctx);
		
		rService.requestSubmit(employee, jsonReimbursement.getTotalCost(),jsonReimbursement.getExpenseType(),jsonReimbursement.getPaymentStatus());
		
		ctx.status(201);
		ctx.status(HttpCode.CREATED);
		ctx.result("New Reimbursement submitted by: " + employee.getUsername());
		
	}
	
	public void allReimbursement(Context ctx) {
		
		List<Reimbursement> reimbursementList = rService.allReimbursement();
		
		ctx.json(reimbursementList);
		
	}
	public void statusCheck(Context ctx) {
		
		String name = ctx.pathParam("username");
		String status = ctx.pathParam("status");

		Employee u = ctx.sessionAttribute("user");
		if (u.getUsername().equals(name)) {
			List<Reimbursement> reimbursementListByStatus =rService.reimbursementByUsernameandStatus(name,status);
			ctx.json(reimbursementListByStatus);
			ctx.status(HttpStatus.ACCEPTED_202);
		}else {
			ctx.result("you do not have access here");
			ctx.status(HttpCode.FORBIDDEN);
		}
		
	}
		
	public void usernameCheck(Context ctx) {
			
			String name = ctx.pathParam("username");
			Employee u = ctx.sessionAttribute("user");
			
			if (u.getUsername().equals(name)) {
				List<Reimbursement> reimbursementListByUsername =rService.reimbursementByUsername(name);
				ctx.json(reimbursementListByUsername);
				ctx.status(HttpStatus.ACCEPTED_202);
			}else{
				ctx.result("you do not have access here");
				ctx.status(HttpCode.FORBIDDEN);
			}
		
	}
	
	public void managerCheck(Context ctx) {
		
		String uniqname = ctx.pathParam("uniqname");
		
		List<Reimbursement> reimbursementListByUsername =rService.reimbursementByUsername(uniqname);
			ctx.json(reimbursementListByUsername);
			ctx.status(HttpStatus.ACCEPTED_202);

	
}	
	public void managerStatusCheck(Context ctx) {
	
	String name = ctx.pathParam("username");
	String status = ctx.pathParam("status");

		List<Reimbursement> reimbursementListByStatus =rService.reimbursementByUsernameandStatus(name,status);
		ctx.json(reimbursementListByStatus);
		ctx.status(HttpStatus.ACCEPTED_202);
	
}
	
	public void paymentStatusUpdate(Context ctx) {
		
		Reimbursement jsonReimbursement =ctx.bodyAsClass(Reimbursement.class);
		
		rService.paymentUpdate(jsonReimbursement.getReimbursementId(), jsonReimbursement.getPaymentStatus());
		ctx.result("Payment Status Updated");
		ctx.status(HttpStatus.ACCEPTED_202);
	}
	
	public void allReimbursement(Context ctx) {
		
		List<Reimbursement> reimbursementList = rService.allReimbursement();
		
		ctx.json(reimbursementList);
		
	}
	
	public void allReimbursementByUsername(Context ctx) {
		
		String uniqname = ctx.pathParam("uniqname");
		
		List<Reimbursement> reimbursementList = rService.reimbursementAllByUsername(uniqname);
		
		ctx.json(reimbursementList);
		
	}

}
