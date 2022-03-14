Author: Prajakta Karve

Task 1:

1. ReportingStructureController.java: Controller for the Reporting Structure component. This file contains 
   the URL mapping /reportingStructure to call the corresponding read() function from the ReportingStructureService
   when the user hits the ..../reportingStructure/{id} link to get the direct reports information for a given  
   employee using the specified employee Id.

2. ReportingStructure.java: This file contains all the class variables required for ReportingStructure such as
   the properties employee and numberOfReports and their getters and setters.
   
3. ReportingStructureService.java: This is the Service interface that calls the read() function.

4. ReportingStructureServiceImpl.java: This Service implements the ReportingStructureService interface and 
   defines the read() function that calls the read() function from the Employee Service to get the data for 
   a given employeeId and also calls the getNumberOfReports() function to get the number of direct reports 
   for the given employeeId.
   

Task 2:

1. CompensationController.java: Controller for the Compensation component. This file contains the URL mapping
   /compensation to call the corresponding, create() and read() functions from the CompensationService
   when the user hits the ..../compensation and ..../compensation/{id} links respectively to insert a new 
   record and to read the compensation information respectively for a given employee using the specified
   employee Id.
   
2. Compensation.java: This file contains all the class variables required for Compensation such as
   the properties employee, salary and effectiveDate and their getters and setters.
   
3. CompensationRepository.java: Connects to the Mongo Repository to store the compensation data for the
   employees.
   
4. CompensationService.java: This is the Service interface that calls the create() and read() functions.

5. CompensationServiceImpl.java: This Service implements the Compensation interface and defines the create() 
   and read() functions. The create() function calls the read() function from the Employee Service to get 
   data for given employeeId and then to add compensation information for that employeeId. The read() 
   function calls the read() function from the Employee Service to get employee data for the given employeeId
   and then calls findByEmployee() on the compensation repository to get the compensation information for
   the given employee derived using the previous read function from Employee Service.
