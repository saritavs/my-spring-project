package com.app.ecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecom.models.User;

/**
 * Repositories are interfaces that allows to interact with databases to do CRUD operations.
 */


@Repository
public interface UserRepositories extends JpaRepository<User, Long> {

}
