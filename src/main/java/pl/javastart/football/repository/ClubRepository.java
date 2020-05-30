package pl.javastart.football.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pl.javastart.football.model.Club;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long>, JpaSpecificationExecutor {

    List<Club> findTop3ByOrderByNameAsc();

    List<Club> findByNameContainsIgnoreCaseAndFoundationYear(String name, int foundationYear);

}
