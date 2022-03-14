package com.mindex.challenge.service.impl;

// Author: Prajakta Karve
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
/** Test class containing a test case to test the working of the Reporting Structure Implementation Service **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {
    private String readReportingStructureUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReportingStructureService reportStructureService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Employee testEmployeeReportingStructure;

    /** Sets up the URLs for testing before the Test is carried out **/
    @Before
    public void setup() {
        readReportingStructureUrl = "http://localhost:" + port + "/reportingStructure/{id}";

        testEmployeeReportingStructure = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }

    /** Runs the service and matches the actual output with the expected one **/
    @Test
    public void testCreateReadUpdate() {
        ReportingStructure testReportingStructure = new ReportingStructure(testEmployeeReportingStructure, 4);

        // Checks read
        ResponseEntity readReportingStructureResponse = restTemplate.getForEntity(readReportingStructureUrl, ReportingStructure.class, testEmployeeReportingStructure.getEmployeeId());
        assertEquals(HttpStatus.OK, readReportingStructureResponse.getStatusCode());
        ReportingStructure readReportingStructure = (ReportingStructure)readReportingStructureResponse.getBody();
        assertNotNull(readReportingStructure);
        assertEquals(readReportingStructure, testReportingStructure);
    }

    /** Runs the service and matches the actual output with the expected one **/
    @Test
    public void testCreateReadUpdate_TestTwo() {
        testEmployeeReportingStructure = employeeRepository.findByEmployeeId("03aa1462-ffa9-4978-901b-7c001562cf6f");
        ReportingStructure testReportingStructure1 = new ReportingStructure(testEmployeeReportingStructure, 2);

        // Checks read
        ResponseEntity readReportingStructureResponse = restTemplate.getForEntity(readReportingStructureUrl, ReportingStructure.class, testEmployeeReportingStructure.getEmployeeId());
        assertEquals(HttpStatus.OK, readReportingStructureResponse.getStatusCode());
        ReportingStructure readReportingStructure = (ReportingStructure)readReportingStructureResponse.getBody();
        assertNotNull(readReportingStructure);
        assertEquals(readReportingStructure, testReportingStructure1);
    }
}
