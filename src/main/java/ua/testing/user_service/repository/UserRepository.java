package ua.testing.user_service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.testing.user_service.model.user.UserData;

@Repository
public interface UserRepository extends CrudRepository<UserData, Long> {
    boolean existsByUsername(String username);

    void deleteById(Long id);
}
