package com.revature.rms.search.clients;

import com.revature.rms.search.entites.workorder.WorkOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient
@RequestMapping("/workorder")
public interface WorkOrderClient {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WorkOrder> getAllWorkOrders();

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WorkOrder> getWorkOrdersByRoom(@PathVariable int id);

}
