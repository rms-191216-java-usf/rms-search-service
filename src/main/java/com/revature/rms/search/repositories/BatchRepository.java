package com.revature.rms.search.repositories;

import com.revature.rms.search.entites.batch.Batch;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * BatchRepository is used to store dummy data that was used in the first sprint. Future sprints should removed this class.
 *
 */

public interface BatchRepository extends MongoRepository<Batch, String> {}
