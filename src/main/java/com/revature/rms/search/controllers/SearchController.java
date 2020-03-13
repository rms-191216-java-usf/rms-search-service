package com.revature.rms.search.controllers;

import com.revature.rms.search.dtos.BuildingDto;
import com.revature.rms.search.dtos.CampusDto;
import com.revature.rms.search.dtos.EmployeeDto;
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
@RequestMapping("/search")
public class SearchController {

    private ETLService etlService;

    @Autowired
    public SearchController(ETLService service) {
        this.etlService = service;
    }

     //Get all campuses
    @GetMapping(value="/campus",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CampusDto> getAllCampuses() {
        return etlService.getAllCampuses();
    }

    // Grab the campus by Id
    @GetMapping(value = "/campus/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CampusDto getCampusById(@PathVariable("id") String id) {
        return etlService.getCampusDtoById(id);
    }

    //Get building by Id, not sure which one to use
    @GetMapping(value = "/building/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BuildingDto getBuildingById(@PathVariable("id") String id) {
        return etlService.getBuildingDtoById(id);
    }
    
    //get room by id
    @GetMapping(value = "/room/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RoomDto getRoomById(@PathVariable("id") String id){return etlService.getRoomDtoById(id); }

    @GetMapping(value = "/employee",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDto> findAllEmployees() {
        return etlService.getAllEmployees();
    }

    @GetMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto findEmployeeById(@PathVariable("id") int id) {
        return etlService.getEmployeeDtoById(id);
    }


}
