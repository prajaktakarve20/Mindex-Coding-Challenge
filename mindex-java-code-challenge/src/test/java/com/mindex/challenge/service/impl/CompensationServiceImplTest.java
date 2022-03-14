package com.mindex.challenge.service.impl;

// Author: Prajakta Karve
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
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

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/** Test class containing a test case to test the working of the Compensation Implementation Service **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
    private String createCompensationUrl;
    private String readCompensationUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationService compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Employee testEmployeeCompensation;

    /** Sets up the URLs for testing before the Test is carried out **/
    @Before
    public void setup() {
        createCompensationUrl = "http://localhost:" + port + "/compensation";
        readCompensationUrl = "http://localhost:" + port + "/compensation/{id}";

        testEmployeeCompensation = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }

    /** Runs the service and matches the actual output with the expected one **/
    @Test
    public void testCreateReadUpdate() {
        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(testEmployeeCompensation);
        testCompensation.setSalary("111,111,111 GPD");
        testCompensation.setEffectiveDate(Instant.parse("2022-03-12T00:00:00Z"));

        // Checks create
        ResponseEntity createdCompensationResponse = restTemplate.postForEntity(createCompensationUrl, testCompensation, Compensation.class);
        assertEquals(HttpStatus.OK, createdCompensationResponse.getStatusCode());
        Compensation createdCompensation = (Compensation)createdCompensationResponse.getBody();
        assertNotNull(createdCompensation);
        assertEquals(testCompensation, createdCompensation);

        // Checks read
        ResponseEntity readCompensationResponse = restTemplate.getForEntity(readCompensationUrl, Compensation.class, createdCompensation.getEmployee().getEmployeeId());
        assertEquals(HttpStatus.OK, readCompensationResponse.getStatusCode());
        Compensation readCompensation = (Compensation)readCompensationResponse.getBody();
        assertNotNull(readCompensation);
        assertEquals(readCompensation, createdCompensation);
    }

    /** Runs the service and matches the actual output with the expected one **/
    @Test
    public void testCreateReadUpdate_TestTwo() {

        testEmployeeCompensation = employeeRepository.findByEmployeeId("03aa1462-ffa9-4978-901b-7c001562cf6f");
        Compensation testCompensation1 = new Compensation();
        testCompensation1.setEmployee(testEmployeeCompensation);
        testCompensation1.setSalary("111,222,222 GPD");
        testCompensation1.setEffectiveDate(Instant.parse("2022-03-13T00:00:00Z"));

        // Checks create
        ResponseEntity createdCompensationResponse = restTemplate.postForEntity(createCompensationUrl, testCompensation1, Compensation.class);
        assertEquals(HttpStatus.OK, createdCompensationResponse.getStatusCode());
        Compensation createdCompensation1 = (Compensation)createdCompensationResponse.getBody();
        assertNotNull(createdCompensation1);
        assertEquals(testCompensation1, createdCompensation1);

        // Checks read
        ResponseEntity readCompensationResponse = restTemplate.getForEntity(readCompensationUrl, Compensation.class, createdCompensation1.getEmployee().getEmployeeId());
        assertEquals(HttpStatus.OK, readCompensationResponse.getStatusCode());
        Compensation readCompensation = (Compensation)readCompensationResponse.getBody();
        assertNotNull(readCompensation);
        assertEquals(readCompensation, createdCompensation1);
    }

}
