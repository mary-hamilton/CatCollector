package justrosa.catcollector.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import justrosa.catcollector.domain.Cat;
import justrosa.catcollector.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {

}
