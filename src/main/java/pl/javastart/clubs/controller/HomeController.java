package pl.javastart.clubs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.javastart.clubs.model.Club;
import pl.javastart.clubs.service.SportService;

import java.util.List;

@Controller
public class HomeController {

    private SportService sportService;

    @Autowired
    public HomeController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping("/")
    String home(Model model) {
        List<Club> clubs = sportService.find5MostPopularClubs();
        model.addAttribute("clubs", clubs);
        return "home";
    }
}
