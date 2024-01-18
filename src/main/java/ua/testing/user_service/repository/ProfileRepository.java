package ua.testing.user_service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.testing.user_service.model.profile.ProfileData;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileData, Long> {
}
