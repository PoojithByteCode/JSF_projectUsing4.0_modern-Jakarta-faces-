package com.poojithkalyan.dao;

import com.poojithkalyan.entity.User;

/**
 * -----------------------------------------------------
 * DAO Interface
 * -----------------------------------------------------
 * Declares all database operations.
 * No SQL should be written here.
 * -----------------------------------------------------
 */
public interface UserDao {

    /**
     * Save a new employee.
     */
    boolean saveUser(User user);
    
     User login(String employeeId, String password);

    /**
     * Validate login credentials.
    
     User login(String employeeId, String password);

    /**
     * Check if Employee ID already exists.
     
    boolean employeeExists(String employeeId);

    /**
     * Check if Email already exists.
     
    boolean emailExists(String email);
    */

}