package ua.testing.user_service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.testing.user_service.model.user.UserData;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserData, Long> {
    Optional<UserData> findByUserTag(String userTag);

    boolean existsByUserTag(String userTag);

    boolean existsByEmail(String email);

    void deleteById(Long id);
}
