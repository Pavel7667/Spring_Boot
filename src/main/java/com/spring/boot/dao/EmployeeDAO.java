
package com.spring.boot.dao;



import com.spring.boot.entity.Employee;

import java.util.List;

/**
 * Basic interface DAO
 */
public interface EmployeeDAO {

    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    Employee getEmployee(int id);

    public void deleteEmployee(int id);
}
