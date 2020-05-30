package pl.javastart.football.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.javastart.football.model.Club;
import pl.javastart.football.model.ClubFilters;
import pl.javastart.football.model.Footballer;
import pl.javastart.football.repository.ClubRepository;
import pl.javastart.football.repository.FootballerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FootballService {

    ClubRepository clubRepository;
    FootballerRepository footballerRepository;

    public FootballService(ClubRepository clubRepository, FootballerRepository footballerRepository) {
        this.clubRepository = clubRepository;
        this.footballerRepository = footballerRepository;
    }

    public List<Club> findAllSortedClubs(String sort) {
        Sort sortBy = Sort.by(sort);
        return clubRepository.findAll(sortBy);
    }

    public Optional<Club> findById(Long id) {
        return clubRepository.findById(id);
    }

    public List<Club> find3MostPopularClubs() {
        return clubRepository.findTop3ByOrderByNameAsc();
    }

    public List<Club> findAllForFilters(ClubFilters clubFilters) {
        if (clubFilters == null) {
            return clubRepository.findAll();
        } else {
            return clubRepository.findByNameContainsIgnoreCaseAndFoundationYear(clubFilters.getName(), clubFilters.getFoundationYear());
        }
    }

    public List<Footballer> findAllSortedFootballers(String sort) {
        Sort sortBy = Sort.by(sort);
        return footballerRepository.findAll(sortBy);
    }

    public List<Footballer> findAllByClubId(Long id) {
        return footballerRepository.findAllByClubId(id);
    }

    public List<Club> findAll() {
        return clubRepository.findAll();
    }
}
