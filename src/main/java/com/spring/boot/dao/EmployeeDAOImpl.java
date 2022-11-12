package com.spring.boot.dao;

import com.spring.boot.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

// @ Repository this class should work with DB
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    // link to Object which help work with DB
    @Autowired
    private EntityManager entityManager;

    /**
     * Transactions between DB and DAO to get List<Employee>
     *
     * @return allEmployees List of Objects
     */

    public List<Employee> getAllEmployees() {
        Query query = entityManager.createQuery("from Employee");
        return (List<Employee>) query.getResultList();
    }

    /**
     * Method saveEmployee save or update object in DB
     * <p>
     * Lintel update to see in response JSON id new Created Object
     *
     * @param employee object to save in DB
     */
    @Override
    public void saveEmployee(Employee employee) {
        Employee newEmployee = entityManager.merge(employee);
        employee.setId(newEmployee.getId());
    }

    /**
     * Method getEmployee using session and reflection to get Object from DB
     *
     * @param id of object in DB
     * @return object from DB
     */
    @Override
    public Employee getEmployee(int id) {
        return entityManager.find(Employee.class, id);
    }

    /**
     * The deleteEmployee create SQL query
     * And replace in query "replace" to id from parameter
     * Use executeUpdate  for update and delete
     *
     * @param id of Object from DB
     */
    @Override
    public void deleteEmployee(int id) {
        Query query = entityManager.createQuery("delete from Employee " +
                "where id =:employeeID");
        query.setParameter("employeeID", id);
        query.executeUpdate();
    }
}
