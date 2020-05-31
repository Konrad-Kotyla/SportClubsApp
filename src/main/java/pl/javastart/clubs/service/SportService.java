package pl.javastart.clubs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.clubs.model.Club;
import pl.javastart.clubs.model.ClubFilters;
import pl.javastart.clubs.model.Country;
import pl.javastart.clubs.model.Player;
import pl.javastart.clubs.repository.ClubRepository;
import pl.javastart.clubs.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SportService {

    ClubRepository clubRepository;
    PlayerRepository playerRepository;

    @Autowired
    public SportService(ClubRepository clubRepository, PlayerRepository playerRepository) {
        this.clubRepository = clubRepository;
        this.playerRepository = playerRepository;
    }


    //Club methods
    public List<Club> findAllSortedClubs(String sort) {
        Sort sortBy = Sort.by(sort);
        return clubRepository.findAll(sortBy);
    }

    public Optional<Club> findClubById(Long id) {
        return Optional.ofNullable(clubRepository.findAllById(id));
    }

    public List<Club> find3MostPopularClubs() {
        return clubRepository.findTop3ByOrderByNameAsc();
    }

    public List<Club> findAllClubs() {
        return clubRepository.findAll();
    }

    @Transactional
    public void addClub(Club clubFromForm) {
        if ("".equals(clubFromForm.getName()) || "".equals(clubFromForm.getFoundationDate()) || "".equals(clubFromForm.getDescription())) {
            throw new IllegalArgumentException("");
        } else {
            clubRepository.save(clubFromForm);
        }
    }

    @Modifying
    @Transactional
    public void editClub(Club clubFromForm) {
        Club clubToChange = clubRepository.findAllById(clubFromForm.getId());
        clubToChange.setId(clubFromForm.getId());
        clubToChange.setName(clubFromForm.getName());
        clubToChange.setCountry(clubFromForm.getCountry());
        clubToChange.setSport(clubFromForm.getSport());
        clubToChange.setDescription(clubFromForm.getDescription());
        clubToChange.setFoundationDate(clubFromForm.getFoundationDate());
        clubToChange.setImageUrl(clubFromForm.getImageUrl());

        clubRepository.update(clubToChange.getName(), clubToChange.getCountry(), clubToChange.getSport(),
                clubToChange.getDescription(), clubToChange.getFoundationDate(), clubToChange.getImageUrl(), clubToChange.getId());
    }

    public void deleteClubById(Long id) {
        Optional<Club> club = findClubById(id);
        if (club.isPresent()) {
            clubRepository.deleteById(id);
        } else {
            throw new NullPointerException();
        }
    }

    //Player methods
    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> findAllPlayersByClubId(Long id) {
        return playerRepository.findAllByClubId(id);
    }

    public List<Player> findAllSortedPlayers(String sort) {
        Sort sortBy = Sort.by(sort);
        return playerRepository.findAll(sortBy);
    }

    public Optional<Player> findPlayerById(Long id) {
        return Optional.ofNullable(playerRepository.findAllById(id));
    }

    @Transactional
    public void addPlayer(Player playerFromForm) {
        if ("".equals(playerFromForm.getFirstName()) || "".equals(playerFromForm.getLastName()) || "".equals(playerFromForm.getAge())) {
            throw new IllegalArgumentException("");
        } else {
            playerRepository.save(playerFromForm);
        }
    }

    public void deletePlayerById(Long id) {
        Optional<Player> player = findPlayerById(id);
        if (player.isPresent()) {
            playerRepository.deleteById(id);
        } else {
            throw new NullPointerException();
        }
    }

    @Modifying
    @Transactional
    public void editPlayer(Player playerFromForm) {
        Player playerToChange = playerRepository.findAllById(playerFromForm.getId());
        playerToChange.setId(playerFromForm.getId());
        playerToChange.setFirstName(playerFromForm.getFirstName());
        playerToChange.setLastName(playerFromForm.getLastName());
        playerToChange.setAge(playerFromForm.getAge());
        playerToChange.setCountry(playerFromForm.getCountry());
        playerToChange.setClub(playerFromForm.getClub());

        playerRepository.update(playerToChange.getFirstName(), playerToChange.getLastName(), playerToChange.getAge(),
                playerToChange.getCountry(), playerToChange.getClub(), playerToChange.getId());
    }

    public List<Club> findClubsByCountry(Country country) {
        return clubRepository.findAllByCountryOrderBy(country);
    }
}
