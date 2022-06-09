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
	
	public void allReimbursment(Context ctx) {
		
		List<Reimbursement> reimbursmentList = rService.allReimbursment();
		
		ctx.json(reimbursmentList);
		
	}
	public void statusCheck(Context ctx) {
		
		String name = ctx.pathParam("username");
		String status = ctx.pathParam("status");

		Employee u = ctx.sessionAttribute("user");
		if (u.getUsername().equals(name)) {
			List<Reimbursement> reimbursmentListByStatus =rService.reimburstmentByUsernameandStatus(name,status);
			ctx.json(reimbursmentListByStatus);
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
				List<Reimbursement> reimbursmentListByUsername =rService.reimburstmentByUsername(name);
				ctx.json(reimbursmentListByUsername);
				ctx.status(HttpStatus.ACCEPTED_202);
			}else{
				ctx.result("you do not have access here");
				ctx.status(HttpCode.FORBIDDEN);
			}
		
		
		
	}
	
	public void managerCheck(Context ctx) {
		
		String uniqname = ctx.pathParam("uniqname");
		
		List<Reimbursement> reimbursmentListByUsername =rService.reimburstmentByUsername(uniqname);
			ctx.json(reimbursmentListByUsername);
			ctx.status(HttpStatus.ACCEPTED_202);

	
	
	
}	
	public void managerStatusCheck(Context ctx) {
	
	String name = ctx.pathParam("username");
	String status = ctx.pathParam("status");

		List<Reimbursement> reimbursmentListByStatus =rService.reimburstmentByUsernameandStatus(name,status);
		ctx.json(reimbursmentListByStatus);
		ctx.status(HttpStatus.ACCEPTED_202);
	
	
}
	
	
	
	public void paymentStatusUpdate(Context ctx) {
		
		Reimbursement jsonReimbursment =ctx.bodyAsClass(Reimbursement.class);
		
		rService.paymentUpdate(jsonReimbursment.getReimbursementId(), jsonReimbursment.getPaymentStatus());
		ctx.result("Payment Status Updated");
		ctx.status(HttpStatus.ACCEPTED_202);
	}
	
	public void allReimbursement(Context ctx) {
		
		List<Reimbursement> reimbursmentList = rService.allReimbursement();
		
		ctx.json(reimbursmentList);
		
	}
	
	public void allReimbursementByUsername(Context ctx) {
		
		
		
		String uniqname = ctx.pathParam("uniqname");
		
		List<Reimbursement> reimbursmentList = rService.reimburstmentAllByUsername(uniqname);
		
		ctx.json(reimbursmentList);
		
	}

}
