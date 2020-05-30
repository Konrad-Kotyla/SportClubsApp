package pl.javastart.football.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.football.model.Footballer;

import java.util.List;

@Repository
public interface FootballerRepository extends JpaRepository<Footballer, Long> {

    List<Footballer> findAll(Sort sort);

    List<Footballer> findAllByClubId(Long id);
}
