package com.revature.rms.search.controllers;

import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.batch.Batch;
import com.revature.rms.search.entites.campus.Building;
import com.revature.rms.search.entites.campus.Campus;
import com.revature.rms.search.entites.campus.Room;
import com.revature.rms.search.entites.employee.Employee;
import com.revature.rms.search.entites.workorder.WorkOrder;
import com.revature.rms.search.exceptions.InvalidRequestException;
import com.revature.rms.search.exceptions.ResourceNotFoundException;
import com.revature.rms.search.services.ETLService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Some extra endpoints will be needed so that you can make calls by user type
 * so that it will be easier for the front-end team to work with what they need.
 * The batch and work order endpoints were to test the repos to ensure that they
 * were properly fetching the data we needed for the dummy objects we created.
 */
@RestController
@RequestMapping("/v1")
public class SearchController {

  private ETLService etlService;

  @Autowired
  public SearchController(ETLService service) {
    this.etlService = service;
  }

  /**
   * findAllCampuses method: No input params, returns a list all CampusDto objects.
   * @return the newly added employee object
   */
  @ApiOperation(value = "Returns a list of all campuses including all nested objects")
  @GetMapping(value = "/campuses", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<CampusDto> findAllCampuses() {
    return etlService.getAllCampuses();
  }

  /**
   * findCampusById method: Takes in a String id and returns the appropriate CampusDto.
   * @param id
   * @return the CamputDto with id matching input param
   */
  @ApiOperation(value = "Returns a campus by id including all nested object")
  @GetMapping(value = "/campus/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CampusDto findCampusById(@PathVariable("id") int id) {
    return etlService.getCampusDtoById(id);
  }

  /**
   * findAllCampusesByOwner method: Takes in an app user id and returns a list of campuses owned by that user
   * @param id
   * @return list of Campus with resourceOwner id matching input param
   */
  @ApiOperation(value = "Returns a list of campuses owned by a specified app user")
  @GetMapping(value = "/campus/owner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<CampusDto> findAllCampusesByOwner(@PathVariable("id") int id){
    return etlService.getAllCampusByOwner(id);
  }

  /**
   * findBuildingById method: Takes in a string id and returns the appropriate building
   * @param id
   * @return the BuildingDto with id matching input param
   */
  @ApiOperation(value = "Returns a building by id including all nested objects")
  @GetMapping(value = "/building/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public BuildingDto findBuildingById(@PathVariable("id") int id) {
    return etlService.getBuildingDtoById(id);
  }

  /**
   * findBuildingByOwner method: Returns a list of building based on a provided app user id
   * @param id
   * @return a list of Building objects
   */
  @ApiOperation(value = "Returns a list of buildings base on an app user id")
  @GetMapping(value = "/building/owner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<BuildingDto> findBuildingByOwner(@PathVariable("id") int id) {
    return etlService.getAllBuildingsByOwner(id);
  }

  /**
   * findRoomById method: Takes in a string id and returns the appropriate room
   * @param id
   * @return the RoomDto with id matching input param
   */
  @ApiOperation(value = "Returns a room by id including all nested objects")
  @GetMapping(value = "/room/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public RoomDto findRoomById(@PathVariable("id") int id) {
    return etlService.getRoomDtoById(id);
  }

  /**
   * findAllRoomByOwner method: Returns a list of rooms associated with a give app user
   * @param id
   * @return a list of Room ojbects
   */
  @ApiOperation(value = "Returns a list of rooms base on an app user id")
  @GetMapping(value = "/room/owner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Room> findAllRoomByOwner(@PathVariable("id") int id) {
    return etlService.getAllRoomByOwner(id);
  }

  /**
   * findAllEmployees method: Takes in no input params and returns a list of all employees
   * @return list of EmployeeDto objects
   */
  @ApiOperation(value = "Returns a list of all employees including all nested objects")
  @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<EmployeeDto> findAllEmployees() {
    return etlService.getAllEmployees();
  }

  /**
   * findEmployeeById method: Takes in a string id and returns the appropriate employee
   * @param id
   * @return the EmployeeDto with id matching input param
   */
  @ApiOperation(value = "Returns an employee by id including all nested obljects")
  @GetMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public EmployeeDto findEmployeeById(@PathVariable("id") int id) {
    return etlService.getEmployeeById(id);
  }

  /**
   * findAllEmployeeByOwner method: Returns a list of Employees resources owned by a provided app user
   * @param id
   * @return a list of Employee Objects
   */
  @ApiOperation(value = "Returns a list of Employees resources owned by a provided app user")
  @GetMapping(value = "/employee/owner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public  List<Employee> findAllEmployeeByOwner(@PathVariable("id") int id) {
    return  etlService.getAllEmployeeByOwner(id);
  }

  //Get batch by id - will be implemented once rms-batch-service is complete
  /**
   * findBatchById method: Takes in a string id and returns the appropriate batch
   * @param id
   * @return the BatchDto with id matching input param
   */
  @ApiOperation(value = "Returns a batch by id including all nested obljects")
  @GetMapping(value = "/batch/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Batch findBatchById(@PathVariable("id") int id){
    return etlService.findBatchById(id);
  }

  //Get work order by id - will be implemented once rms-work-order-service is complete
  /**
   * findWorkOrderById method: Takes in a string id and returns the appropriate work order
   * @param id
   * @return the WorkOrderDto with id matching input param
   */
  @GetMapping(value = "/workorder/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Returns a workorder including all nested obljects")
  public WorkOrder findWorkOrderById(@PathVariable("id") int id)  {
    return etlService.getWorkOrderById(id);
  }


  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleInvalidRequestException(InvalidRequestException e) {
    ErrorResponse err = new ErrorResponse();
    err.setMessage(e.getMessage());
    err.setTimestamp(System.currentTimeMillis());
    err.setStatus(400);
    return err;
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException e) {

    ErrorResponse err = new ErrorResponse();
    err.setMessage(e.getMessage());
    err.setTimestamp(System.currentTimeMillis());
    err.setStatus(400);
    return err;
  }
}
