package io.auth.secure.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT u.password FROM users u WHERE u.email = ?#{#email}",nativeQuery = true)
    String findPasswordbyEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users " +
            "SET enabled = TRUE WHERE email = ?1",nativeQuery = true)
    int enableUser(String email);

}
