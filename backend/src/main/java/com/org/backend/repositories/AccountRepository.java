package com.org.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.backend.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	boolean existsByUserEmail(String email);
	boolean existsByUserPassword(String password);
	Optional<Account> findByUserEmailOrUserPassword(String email, String password);
	Optional<Account> findByUserEmail(String email);
}
