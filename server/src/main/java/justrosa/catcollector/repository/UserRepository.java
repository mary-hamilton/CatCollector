package justrosa.catcollector.repository;

import justrosa.catcollector.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Stereotype annotation v
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional <User> findUserByUsername(String username);
    boolean existsUserByUsername(String username);
}
