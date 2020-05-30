package pl.javastart.football.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.javastart.football.model.Club;
import pl.javastart.football.repository.ClubRepository;
import pl.javastart.football.service.FootballService;

import java.util.List;

@Controller
public class HomeController {

    private FootballService footballService;

    @Autowired
    public HomeController(FootballService footballService) {
        this.footballService = footballService;
    }

    @GetMapping("/")
    String home(Model model) {
        List<Club> clubs = footballService.find3MostPopularClubs();
        model.addAttribute("clubs", clubs);
        return "home";
    }
}
