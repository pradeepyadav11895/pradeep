package com.pradeep.backend.persistence.repositories;

import com.pradeep.backend.persistence.domain.backend.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends CrudRepository<Role,Integer> {
}
