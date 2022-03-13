package testgliapi.technicaltest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import testgliapi.technicaltest.UserMasterModel;

public interface UserMasterRepository extends JpaRepository<UserMasterModel, Long> {
}
