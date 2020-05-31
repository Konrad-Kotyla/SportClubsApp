package pl.javastart.clubs.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.javastart.clubs.model.Club;
import pl.javastart.clubs.model.Country;
import pl.javastart.clubs.model.Player;
import pl.javastart.clubs.model.Sport;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findAllById(Long id);

    List<Player> findAll(Sort sort);

    List<Player> findAllByClubId(Long id);

    @Modifying
    @Query("UPDATE Player p SET p.firstName = :firstName, p.lastName = :lastName, p.age = :age, p.country = :country, " +
            "p.club = :club WHERE p.id = :id")
    void update(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("age") int age,
                @Param("country") Country country, @Param("club") Club club, @Param("id") Long id);

}
