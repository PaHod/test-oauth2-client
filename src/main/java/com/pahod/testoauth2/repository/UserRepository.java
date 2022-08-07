package com.pahod.testoauth2.repository;

import com.pahod.testoauth2.domain.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, String> {
}
