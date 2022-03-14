package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    /** Gets the number of direct reports for the given employeeId **/
    public int getNumberOfReports(String employeeId) {
        int totalReports = 0;

        Employee employee = this.read(employeeId);
        if (employee == null) {
            throw new RuntimeException("No employee!");
        }

        List<Employee> reports = employee.getDirectReports();
        if (reports != null) {

            /* Traverses through every direct report for the current employee & makes a recursive call to the function
                to reach the leaf nodes of the tree and increment count with each recursive call*/
            for (Employee reportingEmployee : reports) {
                totalReports += 1 + getNumberOfReports(reportingEmployee.getEmployeeId());
            }
        }

        return totalReports;
    }

}
