package com.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ers.models.Employee;
import com.ers.models.Manager;
import com.ers.models.Reimbursement;
import com.ers.util.ConnectionFactory;

public class ImplReimbursementDAO implements ReimbursementDAO {
	
	private Connection connection = ConnectionFactory.getConnection();
	
	@Override
	public void createNewReimbursment(Reimbursement r) {
		String sql = "INSERT INTO reimbursement (userid, username, totalcost, expensetype, paymentstatus) values (?, ?, ?, ?, 'Pending');";
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setInt(1,r.getUserid());
			ps.setString(2,r.getUsername());
			ps.setDouble(3,r.getTotalCost());
			ps.setString(4,r.getExpenseType());

			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean employeeLogin(Employee u) {
		String sql = "SELECT * FROM employee WHERE username = ? AND password = ? ;";
		try(PreparedStatement ps = connection.prepareStatement(sql))  {
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("Login error -->" + e.getMessage());
			return false;
		}
		return false;
	}

	@Override
	public List<Reimbursement> allReimbursmentById() {
		String sql = "SELECT * FROM reimbursement WHERE paymentstatus = 'Pending';";
		List<Reimbursement> reimbursmentList = new ArrayList<>();
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimbursement_id"), rs.getInt("userid"), rs.getString("username"), rs.getInt("totalcost"), rs.getString("expensetype"), rs.getString("paymentstatus"));
				
				reimbursmentList.add(r);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimbursmentList;
	}

	@Override
	public List<Reimbursement> reimburstmentByUsername(String username) {
		String sql = "SELECT * FROM reimbursement WHERE username = ? AND paymentstatus = 'Pending';";
		List<Reimbursement> reimbursmentListByusername = new ArrayList<>();
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1,username);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimbursement_id"), rs.getInt("userid"), rs.getString("username"), rs.getInt("totalcost"), rs.getString("expensetype"), rs.getString("paymentstatus"));

				
				reimbursmentListByusername.add(r);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimbursmentListByusername;
	}

	@Override
	public List<Reimbursement> reimburstmentByUsernameandStatus(String username, String status) {
		String sql = "select * from reimbursement where username = ? and paymentstatus = ?";
		List<Reimbursement> reimbursmentListByusernameandStatus = new ArrayList<>();
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1,username);
			ps.setString(2,status);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimbursement_id"), rs.getInt("userid"), rs.getString("username"), rs.getInt("totalcost"), rs.getString("expensetype"), rs.getString("paymentstatus"));

				reimbursmentListByusernameandStatus.add(r);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return reimbursmentListByusernameandStatus;
	}

	@Override
	
	public boolean managerLogin(Manager m) {
		String sql = "SELECT * FROM manager WHERE username = ? AND password = ? ;";
		try(PreparedStatement ps = connection.prepareStatement(sql))  {
			ps.setString(1, m.getmUsername());
			ps.setString(2, m.getmPassword());
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				return true;
				
			}
			
		} catch (SQLException e) {
			System.out.println("Login error -->" + e.getMessage());
			return false;
		}
		return false;
	}

	@Override
	public void paymentStatusuUpgrade(int id, String status) {
		String sql = "update reimbursement set paymentstatus  = ? where reimbursement_id = ?;";
		try(PreparedStatement ps = connection.prepareStatement(sql))  {
			ps.setString(1, status);
			ps.setInt(2, id);
			ps.execute();

		} catch (SQLException e) {
			System.out.println("Login error -->" + e.getMessage());
		}	
	}

	@Override
	public List<Reimbursement> allReimbursment() {
		String sql = "SELECT * FROM reimbursement;";
		List<Reimbursement> reimbursmentList = new ArrayList<>();
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimbursement_id"), rs.getInt("userid"), rs.getString("username"), rs.getInt("totalcost"), rs.getString("expensetype"), rs.getString("paymentstatus"));
				
				reimbursmentList.add(r);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimbursmentList;
	}

	@Override
	public List<Reimbursement> reimburstmentAllByUsername(String username) {
		String sql = "SELECT * FROM reimbursement WHERE username = ?;";
		List<Reimbursement> reimbursmentListByusername = new ArrayList<>();
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setString(1,username);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimbursement_id"), rs.getInt("userid"), rs.getString("username"), rs.getInt("totalcost"), rs.getString("expensetype"), rs.getString("paymentstatus"));

				
				reimbursmentListByusername.add(r);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimbursmentListByusername;
	}
}
