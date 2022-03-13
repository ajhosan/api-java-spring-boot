package testgliapi.technicaltest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import testgliapi.technicaltest.UserRoleModel;

public interface UserRoleRepository extends JpaRepository<UserRoleModel, Long> {
}
