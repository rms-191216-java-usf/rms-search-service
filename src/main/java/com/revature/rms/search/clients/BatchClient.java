package com.revature.rms.search.clients;

import com.revature.rms.search.entites.batch.Batch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "batch-service")
@RequestMapping("/batch")
public interface BatchClient {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Batch> getAllBatches();
<<<<<<< HEAD
=======

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Batch getBatchById(@PathVariable int id);
}
>>>>>>> 9cb7dd483ce07d33040cddf826694d04791263d4

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Batch getBatchById(@PathVariable int id);
}
