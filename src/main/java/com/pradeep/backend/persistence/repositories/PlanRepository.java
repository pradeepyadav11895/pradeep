package com.pradeep.backend.persistence.repositories;

import com.pradeep.backend.persistence.domain.backend.Plan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository  extends CrudRepository<Plan,Integer> {
}
