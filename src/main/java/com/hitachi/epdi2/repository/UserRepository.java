package com.hitachi.epdi2.repository;

import com.hitachi.epdi2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Shiva Created on 08/07/23
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
