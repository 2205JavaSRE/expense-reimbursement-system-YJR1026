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
		String sql = "INSERT INTO \"MyProject1\".Reimbursement (username,Tota_Cost, Expense_Type, reimuserid) values (?, ?, ?,(SELECT userid FROM \"Project1\".employee where username = ?));";
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setString(1,r.getUsername());
			ps.setDouble(2,r.getTotalCost());
			ps.setString(3,r.getExpenseType());
			ps.setString(4,r.getUsername());

			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean employeeLogin(Employee u) {
		String sql = "SELECT * FROM \"MyProject1\".employee WHERE username = ? AND password = ? ;";
		try(PreparedStatement ps = connection.prepareStatement(sql))  {
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				//result found,means valid inputs
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
		String sql = "SELECT * FROM \"MyProject1\".reimbursement WHERE payment_status = 'Pending';";
		List<Reimbursement> reimbursmentList = new ArrayList<>();
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimbursementId"), rs.getInt("reimuserid"), rs.getString("username"),rs.getInt("tota_cost"), rs.getString("expense_type"), rs.getString("payment_status"));
				
				
				reimbursmentList.add(r);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimbursmentList;
	}

	@Override
	public List<Reimbursement> reimburstmentByUsername(String username) {
		String sql = "SELECT * FROM \"MyProject1\".reimbursement WHERE username = ? AND payment_status = 'Pending' ;";
		List<Reimbursement> reimbursmentListByusername = new ArrayList<>();
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1,username);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimbursementId"), rs.getInt("reimuserid"), rs.getString("username"),rs.getInt("tota_cost"), rs.getString("expense_type"), rs.getString("payment_status"));

				
				reimbursmentListByusername.add(r);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimbursmentListByusername;
	}

	@Override
	public List<Reimbursement> reimburstmentByUsernameandStatus(String username, String status) {
		String sql = "select * from \"MyProject1\".reimbursement where username = ? and payment_status = ?";
		List<Reimbursement> reimbursmentListByusernameandStatus = new ArrayList<>();
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1,username);
			ps.setString(2,status);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimbursementId"), rs.getInt("reimuserid"), rs.getString("username"),rs.getInt("tota_cost"), rs.getString("expense_type"), rs.getString("payment_status"));

				reimbursmentListByusernameandStatus.add(r);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return reimbursmentListByusernameandStatus;
	}

	@Override
	
	public boolean managerLogin(Manager m) {
		String sql = "SELECT * FROM \"MyProject1\".manager WHERE username = ? AND password = ? ;";
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
		String sql = "update \"MyProject1\".Reimbursement  set payment_status  = ? where reimbursementid = ?;";
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
		String sql = "SELECT * FROM \"MyProject1\".reimbursement;";
		List<Reimbursement> reimbursmentList = new ArrayList<>();
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimbursementId"), rs.getInt("reimuserid"), rs.getString("username"),rs.getInt("tota_cost"), rs.getString("expense_type"), rs.getString("payment_status"));
				
				
				reimbursmentList.add(r);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimbursmentList;
	}


	@Override
	public List<Reimbursement> reimburstmentAllByUsername(String username) {
		String sql = "SELECT * FROM \"MyProject1\".reimbursement WHERE username = ?;";
		List<Reimbursement> reimbursmentListByusername = new ArrayList<>();
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setString(1,username);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimbursementId"), rs.getInt("reimuserid"), rs.getString("username"),rs.getInt("tota_cost"), rs.getString("expense_type"), rs.getString("payment_status"));

				
				reimbursmentListByusername.add(r);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return reimbursmentListByusername;
	}

}
