package com.revature.rms.search.clients;

import com.revature.rms.search.entites.campus.Building;
import com.revature.rms.search.entites.campus.Campus;
import com.revature.rms.search.entites.campus.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/v2")
@FeignClient(name = "campus-service")
public interface CampusClient {

    @GetMapping(value = "/campus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Campus> getAllCampuses();

    @GetMapping(value = "/campus/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Campus getCampusById();

    @GetMapping(value = "/building", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Building> getAllBuildings();

    @GetMapping(value = "/building/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public  Building getBuildingById();

    @GetMapping(value = "/room", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Room> getAllRooms();

    @GetMapping(value = "/room/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Room getRoomById();
}