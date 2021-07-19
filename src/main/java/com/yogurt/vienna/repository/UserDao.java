package com.yogurt.vienna.repository;

import com.yogurt.vienna.entity.DAOUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {
}
