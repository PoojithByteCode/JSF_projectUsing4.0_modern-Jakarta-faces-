package com.poojithkalyan.bean;

import jakarta.faces.context.FacesContext;

import jakarta.faces.application.FacesMessage;
import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import com.poojithkalyan.dao.UserDao;
import com.poojithkalyan.dao.UserDaoImpl;
import com.poojithkalyan.entity.User;

@Named("registerBean")
@RequestScoped
public class RegisterBean implements Serializable {
 
    private static final long serialVersionUID = 1L;

    // ========= Form Fields =========
    private String fullName;
    private String email;
    private String employeeId;
    private String password;
    private String confirmPassword;
    private Part idProof;

    // ==========================================================
    // Called whenever JSF creates this bean for a request.
    // This happens before the page is rendered or processed.
    // ==========================================================
    @PostConstruct
    public void init() {
        System.out.println("=======================================");
        System.out.println("RegisterBean Created");
        System.out.println("JSF Lifecycle -> @PostConstruct");
        System.out.println("=======================================");
    }

    // ==========================================================
    // Called after:
    // Restore View
    // Apply Request Values
    // Process Validations
    // Update Model Values
    //
    // This is the "Invoke Application" phase.
    // ==========================================================
    public String register() {

        System.out.println("\n=========== INVOKE APPLICATION ===========");

        System.out.println("Full Name      : " + fullName);
        System.out.println("Email          : " + email);
        System.out.println("Employee ID    : " + employeeId);
        System.out.println("Password       : " + password);

        if (idProof != null) {
            System.out.println("Uploaded File  : " + idProof.getSubmittedFileName());
        } else {
            System.out.println("No file uploaded");
        }

        // Validation Example
        if (!password.equals(confirmPassword)) {

            System.out.println("--------------------------------");
            System.out.println("Validation Failed");
            System.out.println("Password mismatch");
            System.out.println("--------------------------------");

            return null;
        }
     
            User user = new User();

            user.setEmployeeId(employeeId);
            user.setFullName(fullName);
            user.setEmail(email);
            user.setPassword(password);

            if (idProof != null) {
                user.setIdCard(idProof.getSubmittedFileName());
            } else {
                user.setIdCard(null);
            }

            UserDao dao = new UserDaoImpl();

            boolean saved = dao.saveUser(user);

            if (saved) {

                System.out.println("Registration Successful.");
                
                FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash()
                .put("successMessage",
                     "✔ ✌️Registration Successful! Please login with your Employee ID.");

                return "/login.xhtml?faces-redirect=true";

            } else {

                System.out.println("Registration Failed.");
                FacesContext.getCurrentInstance().addMessage(null,
                	    new FacesMessage(
                	        FacesMessage.SEVERITY_ERROR,
                	        "Registration Failed!",
                	        "Employee ID or Email already exists."
                	));

                	return null;
            }
      
      
        // Module 3
        // userService.register(...);
    }

    // ========= Getters & Setters =========

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    public Part getIdProof() { return idProof; }
    public void setIdProof(Part idProof) { this.idProof = idProof; }
}