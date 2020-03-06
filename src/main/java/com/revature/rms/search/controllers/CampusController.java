package com.revature.rms.search.controllers;

import com.revature.rms.search.entites.campus.Campus;
import com.revature.rms.search.services.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/campus")
public class CampusController {

  private CampusService campusService;

  @Autowired
  public CampusController(CampusService service) {
    this.campusService = service;
  }

  // Get all campuses
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Campus> getAll() {
    return campusService.getAllCampus();
  }

  // Grab the campus by Id
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Campus getBuilding(@PathVariable String attr, @PathVariable String val) {
        return campusService.getCampusById(Integer.parseInt(val));

    }


  // Get all buildings from campus
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Campus> getAllBuildings() {
    return campusService.findAll();
  }

  //Get building by Id, not sure which one to use
  @GetMapping(value = "/building/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Campus getBuildingById(@PathVariable String id) {
    return campusService.findById(id).get();
  }

  //building manager get building by id
 @GetMapping(value = "/bmgnr/building/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Campus> getBuildingById(){
      return campusService.getBuildingById();
 }
}
