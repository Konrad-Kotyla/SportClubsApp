package pl.javastart.football.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.javastart.football.model.Club;
import pl.javastart.football.model.ClubFilters;
import pl.javastart.football.model.Footballer;
import pl.javastart.football.service.FootballService;

import java.util.List;
import java.util.Optional;

@Controller
public class ClubController {

    private FootballService footballService;

    @Autowired
    public ClubController(FootballService footballService) {
        this.footballService = footballService;
    }

    @GetMapping("/clubs")
    public String clubs(Model model,
                        @RequestParam(required = false) String sort,
                        ClubFilters clubFilters) {
        List<Club> clubs;
        if (sort != null) {
            clubs = footballService.findAllSortedClubs(sort);
        } else {
            clubs = footballService.findAll();
        }
        model.addAttribute("clubs", clubs);
        model.addAttribute("filters", new ClubFilters());
        return "clubs";
    }

    @GetMapping("/club/{id}")
    public String getOne(@PathVariable Long id, Model model) {
        Optional<Club> clubById = footballService.findById(id);
        List<Footballer> footballers = footballService.findAllByClubId(id);
        if (clubById.isEmpty()) {
            return "error";
        }

        model.addAttribute("club", clubById.get());
        model.addAttribute("footballers", footballers);
        return "clubSite";
    }
}
