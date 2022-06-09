package com.ers.models;

import java.util.Objects;

public class Reimbursement {
	
	private int reimbursementId;
	private int userid;
	private String username;
	private double totalCost;
	private String expenseType;
	private String paymentStatus;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimbursementId, int userid, String username, double totalCost, String expenseType,
			String paymentStatus) {
		super();
		this.reimbursementId = reimbursementId;
		this.userid = userid;
		this.username = username;
		this.totalCost = totalCost;
		this.expenseType = expenseType;
		this.paymentStatus = paymentStatus;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expenseType, paymentStatus, reimbursementId, totalCost, userid, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Objects.equals(expenseType, other.expenseType) && Objects.equals(paymentStatus, other.paymentStatus)
				&& reimbursementId == other.reimbursementId
				&& Double.doubleToLongBits(totalCost) == Double.doubleToLongBits(other.totalCost)
				&& userid == other.userid && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Reimbursment [reimbursementId=" + reimbursementId + ", userid=" + userid + ", username=" + username
				+ ", totalCost=" + totalCost + ", expenseType=" + expenseType + ", paymentStatus=" + paymentStatus
				+ "]";
	}

}