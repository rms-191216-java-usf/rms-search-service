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


/**
* Grab all campuses
* Grab a campus by Id
* Grab all buildings
* Grab all buidings in campus
* Grab a buildings details from a campus
* Grab all rooms from the building
*
* */

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
  @GetMapping(value = "/campus/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Campus getBuilding(@PathVariable String attr, @PathVariable String val) {
        return campusService.getCampusById(Integer.parseInt(val));

    }


  // Get all buildings from campus
  @GetMapping(value = "/campus/building", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Campus> getAllBuildings() {
    return campusService.findAll();
  }

  //Get building by Id, not sure which one to use
  @GetMapping(value = "/campus/building/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Campus getBuildingById(@PathVariable String id) {
    return campusService.findById(id).get();
  }

  //building manager get building by id
 @GetMapping(value = "/bmgnr/building/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Campus> getBuildingById(){
      return campusService.getBuildingById();
 }
 //grab rooms for building
 @RequestMapping(value = "/campus/building", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Campus> getAllRooms(){
      return campusService.getAllRooms();
 }

 //grab rooms by Id from building from campus
 @RequestMapping(value = "/campus/building/room/{}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Campus> getRoomById(){
      return campusService.getRoomById();
 }

}
