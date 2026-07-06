package com.poojithkalyan.bean;

import java.io.Serializable;

import com.poojithkalyan.dao.UserDao;
import com.poojithkalyan.dao.UserDaoImpl;
import com.poojithkalyan.entity.User;

import jakarta.annotation.PostConstruct;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;

import jakarta.faces.context.FacesContext;
import java.io.IOException;
// for  module 6 step 1
@Named("loginBean")
// @RequestScoped
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // Login Form Fields
    private String employeeId;
    private String password;
    private User loggedInUser;
    private String message;
    @PostConstruct
    public void init() {

        System.out.println("======================================");
        System.out.println("LoginBean Created");
        System.out.println("JSF Lifecycle -> @PostConstruct");
        System.out.println("======================================");
    }

    public String login() {

        System.out.println("\n=========== LOGIN ===========");

        System.out.println("Employee ID : " + employeeId);
        System.out.println("Password    : " + password);
         
        
        message = ""; // to display invalid user details on ui 
        // Create DAO Object
        UserDao dao = new UserDaoImpl();

        // Authenticate User
        User user = dao.login(employeeId, password);

        if (user != null) {
        	this.loggedInUser = user;
        
            System.out.println("--------------------------------");
            System.out.println("Login Successful");
            System.out.println("Welcome " + user.getFullName());
            System.out.println("--------------------------------");

            return "dashboard?faces-redirect=true";
        }

        message = "Invalid Employee ID or Password";
        System.out.println("--------------------------------");
        System.out.println("Invalid Employee ID or Password");
        System.out.println("--------------------------------");

        return null;
    }

    
    public void checkSession() throws IOException {

        if (loggedInUser == null) {

            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("login.xhtml");
        }
    }
    
    public String logout() {

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .invalidateSession();

        return "/login.xhtml?faces-redirect=true";
    }
    
    
    
    
    
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }
    // Getters & Setters

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}