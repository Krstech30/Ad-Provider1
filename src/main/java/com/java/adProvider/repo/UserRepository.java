package com.java.adProvider.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.adProvider.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);

	public User findByEmail(String email);

	@Query(value = "select * from tab_users  where phone=?", nativeQuery = true)
	public User findByPhone(String phone);

}
