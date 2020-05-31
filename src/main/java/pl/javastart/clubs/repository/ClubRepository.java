package pl.javastart.clubs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.javastart.clubs.model.Club;
import pl.javastart.clubs.model.Country;
import pl.javastart.clubs.model.Sport;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long>, JpaSpecificationExecutor {

    Club findAllById(Long id);

    @Modifying
    @Query("UPDATE Club c SET c.name = :name, c.country = :country, c.sport = :sport, c.description = :description, " +
            "c.foundationDate = :foundationDate, c.imageUrl = :imageUrl WHERE c.id = :id")
    void update(@Param("name") String name, @Param("country") Country country, @Param("sport") Sport sport,
                @Param("description") String description, @Param("foundationDate") LocalDate foundationDate,
                @Param("imageUrl") String imageUrl, @Param("id") Long id);

    List<Club> findTop3ByOrderByNameAsc();

    List<Club> findByNameContainsIgnoreCaseAndFoundationDate(String name, int foundationDate);

}
