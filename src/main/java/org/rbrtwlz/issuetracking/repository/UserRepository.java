package org.rbrtwlz.issuetracking.repository;

import org.rbrtwlz.issuetracking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.role = 'SUPPORT'")
    public List<User> getSupportUsers();

}