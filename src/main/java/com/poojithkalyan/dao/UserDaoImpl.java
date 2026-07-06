package com.poojithkalyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.poojithkalyan.entity.User;
import com.poojithkalyan.util.Dbconnection;

public class UserDaoImpl implements UserDao {

    // ===========================================
    // Register New User
    // ===========================================
    @Override
    public boolean saveUser(User user) {

        String sql = "INSERT INTO users (employee_id, full_name, email, password, id_card) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection con = Dbconnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {

            ps.setString(1, user.getEmployeeId());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getIdCard());

            int rows = ps.executeUpdate();

            System.out.println("--------------------------------");
            System.out.println("Rows Inserted : " + rows);
            System.out.println("--------------------------------");

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ===========================================
    // Login Authentication
    // ===========================================
    @Override
    public User login(String employeeId, String password) {

        String sql = "SELECT * FROM users WHERE employee_id = ? AND password = ?";

        try (
                Connection con = Dbconnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {

            ps.setString(1, employeeId);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setEmployeeId(rs.getString("employee_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setIdCard(rs.getString("id_card"));

                System.out.println("--------------------------------");
                System.out.println("Login Successful");
                System.out.println("Welcome " + user.getFullName());
                System.out.println("--------------------------------");

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("--------------------------------");
        System.out.println("Invalid Employee ID or Password");
        System.out.println("--------------------------------");

        return null;
    }
}