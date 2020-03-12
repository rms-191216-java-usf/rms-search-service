package com.revature.rms.search.controllers;

import com.revature.rms.search.dtos.BuildingDto;
import com.revature.rms.search.dtos.CampusDto;
import com.revature.rms.search.dtos.RoomDto;
import com.revature.rms.search.entites.campus.Building;
import com.revature.rms.search.entites.campus.Campus;
import com.revature.rms.search.entites.campus.Room;
import com.revature.rms.search.services.ETLService;
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

    private ETLService etlService;

    @Autowired
    public CampusController(ETLService service) {
        this.etlService = service;
    }

    // Get all campuses
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public Iterable<Campus> getAll() {
//        return campusService.getAllCampus();
//    }

    // Grab the campus by Id
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CampusDto getCampusById(@PathVariable("id") String id) {
        return etlService.getCampusDtoById(id);
    }

    // Get all buildings from campus
//    @GetMapping(value = "/campus/building", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Building> getAllBuildings() {
//        return massService.findAll();
//    }

    //Get building by Id, not sure which one to use
    @GetMapping(value = "/building/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BuildingDto getBuildingById(@PathVariable("id") String id) {
        return etlService.getBuildingDtoById(id);
    }

    //building manager get building by id
//    @GetMapping(value = "/bmgnr/building/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Building> getBuildingById(){
//        return massService.getBuildingById();
//    }

    //grab rooms for building
//    @GetMapping(value = "/building/room", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Building> getAllRooms(){
//        return massService.getAllRooms();
//    }

    //grab rooms by building?
    @GetMapping(value = "/room/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RoomDto getRoomById(@PathVariable("id") String id){return etlService.getRoomDtoById(id); }

    //grab rooms by Id from building from campus
    @GetMapping(value = "/building-rooms/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoomDto> getRoomsById(@PathVariable("id") String id){
        return etlService.getBuildingDtoById(id).getRooms();
    }

}
