package com.revature.rms.search.repositories;

import com.revature.rms.search.entites.workorder.WorkOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BatchRepository extends MongoRepository<WorkOrder, String> {
}
