package com.revature.rms.search.repositories;

import com.revature.rms.search.entites.batch.Batch;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BatchRepository extends MongoRepository<Batch, String> {}
