package com.revature.rms.search.controllers;

import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.campus.Building;
import com.revature.rms.search.entites.campus.Campus;
import com.revature.rms.search.entites.campus.Room;
import com.revature.rms.search.exceptions.InvalidRequestException;
import com.revature.rms.search.exceptions.ResourceNotFoundException;
import com.revature.rms.search.services.ETLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Grab all campuses Grab a campus by Id Grab all buildings Grab all buidings in campus Grab a
 * buildings details from a campus Grab all rooms from the building
 */
@RestController
@RequestMapping("/search")
public class SearchController {

  private ETLService etlService;

  @Autowired
  public SearchController(ETLService service) {
    this.etlService = service;
  }

  // Get all campuses
  @GetMapping(value = "/campuses", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<CampusDto> getAllCampuses() {
    return etlService.getAllCampuses();
  }

  // Grab the campus by Id
  @GetMapping(value = "/campus/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CampusDto getCampusById(@PathVariable("id") String id) {
    return etlService.getCampusDtoById(id);
  }

  // Get building by Id, not sure which one to use
  @GetMapping(value = "/building/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public BuildingDto getBuildingById(@PathVariable("id") String id) {
    return etlService.getBuildingDtoById(id);
  }

  // get room by id
  @GetMapping(value = "/room/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public RoomDto getRoomById(@PathVariable("id") String id) {
    return etlService.getRoomDtoById(id);
  }

  @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<EmployeeDto> findAllEmployees() {
    return etlService.getAllEmployees();
  }

  @GetMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public EmployeeDto findEmployeeById(@PathVariable("id") int id) {
    return etlService.getEmployeeDtoById(id);
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
