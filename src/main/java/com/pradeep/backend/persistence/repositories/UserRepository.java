package com.pradeep.backend.persistence.repositories;

import com.pradeep.backend.persistence.domain.backend.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends CrudRepository<User,Long> {
}
