package com.revature.rms.search.clients;

import com.revature.rms.search.entites.campus.Building;
import com.revature.rms.search.entites.campus.Campus;
import com.revature.rms.search.entites.campus.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/campuses")
@FeignClient(name = "campus-service")
public interface CampusClient {

    @GetMapping(value = "/campuses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Campus> getAllCampus();

    @GetMapping(value = "/campuses/training-managers/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Campus> getCampusByTrainingManagerId(@PathVariable int id);

    @GetMapping(value = "/campuses/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Campus getCampusById(@PathVariable int id);

    @GetMapping(value = "/campus/owners/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Campus> getAllCampusByOwner(@PathVariable int id);

    @GetMapping(value = "/buildings", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Building> getAllBuildings();

    @GetMapping(value = "/buildings/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  Building getBuildingById(@PathVariable int id);

    @GetMapping(value = "/buildings/training-leads/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  Building getBuildingByTrainingLeadId(@PathVariable int id);

    @GetMapping(value = "/buildings/owners/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Building> getAllBuildingsByOwner(@PathVariable int id);

    @GetMapping(value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Room> getAllRooms();

    @GetMapping(value = "/rooms/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Room getRoomById(@PathVariable int id);

    @GetMapping(value = "/rooms/owners/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  List<Room> getAllRoomByOwner(@PathVariable int id);

}
