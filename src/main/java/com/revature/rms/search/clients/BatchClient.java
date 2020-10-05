package com.revature.rms.search.clients;

import com.revature.rms.search.entites.batch.Batch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 *  BatchClient is not in used. It was created to be used in future sprints.
 */
@FeignClient(name = "batch-service")
@RequestMapping("/batch")
public interface BatchClient {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Batch> getAllBatches();

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Batch getBatchById(@PathVariable int id);
}
