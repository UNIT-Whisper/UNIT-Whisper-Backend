package com.unit.whisper.repository;


import com.unit.whisper.domain.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdentityId(Long identityId);
}
