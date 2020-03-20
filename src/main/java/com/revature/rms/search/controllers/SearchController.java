package com.revature.rms.search.controllers;

import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.batch.Batch;
import com.revature.rms.search.entites.campus.Building;
import com.revature.rms.search.entites.campus.Campus;
import com.revature.rms.search.entites.campus.Room;
import com.revature.rms.search.entites.workorder.WorkOrder;
import com.revature.rms.search.exceptions.InvalidRequestException;
import com.revature.rms.search.exceptions.ResourceNotFoundException;
import com.revature.rms.search.services.ETLService;
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

  // Get all campuses
  @GetMapping(value = "/campuses", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<CampusDto> findAllCampuses() {
    return etlService.getAllCampuses();
  }

  // Grab the campus by Id
  @GetMapping(value = "/campus/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CampusDto findCampusById(@PathVariable("id") String id) {
    return etlService.getCampusDtoById(id);
  }

  // Get building by Id
  @GetMapping(value = "/building/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public BuildingDto findBuildingById(@PathVariable("id") String id) {
    return etlService.getBuildingDtoById(id);
  }

  // Get room by id
  @GetMapping(value = "/room/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public RoomDto findRoomById(@PathVariable("id") String id) {
    return etlService.getRoomDtoById(id);
  }

  @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<EmployeeDto> findAllEmployees() {
    return etlService.getAllEmployees();
  }

  @GetMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public EmployeeDto findEmployeeById(@PathVariable("id") int id) {
    return etlService.getEmployeeById(id);
  }

  @GetMapping(value = "/batch/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Batch findBatchById(@PathVariable("id") String id){
    return etlService.findBatchById(id);
  }

  @GetMapping(value = "/workorder/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public WorkOrder findWorkOrderById(@PathVariable("id") String id)  {
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
