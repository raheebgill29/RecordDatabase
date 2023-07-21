package com.raheeb.fullstackbackend.repository;

import com.raheeb.fullstackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User,Long> {

}
