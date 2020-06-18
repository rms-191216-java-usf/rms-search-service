package com.revature.rms.search.repositories;

import com.revature.rms.search.entites.workorder.WorkOrder;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 *
 * WorkOrderRepository is used to store dummy data that was used in the first sprint. Future sprints should removed this class.
 *
 */
public interface WorkOrderRepository extends MongoRepository<WorkOrder, Integer> {}
