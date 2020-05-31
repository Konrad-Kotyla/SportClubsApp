package pl.javastart.clubs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.clubs.model.Club;
import pl.javastart.clubs.model.Player;
import pl.javastart.clubs.service.SportService;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

    private SportService sportService;

    @Autowired
    public PlayerController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping("/players")
    public String clubs(Model model,
                        @RequestParam(required = false) String sort) {
        List<Player> players;
        if (sort != null) {
            players = sportService.findAllSortedPlayers(sort);
        } else {
            players = sportService.findAllPlayers();
        }
        model.addAttribute("players", players);
        return "players";
    }

    @GetMapping("/player/add")
    public String addPlayerHome(Model model) {
        List<Club> clubs = sportService.findAllClubs();
        model.addAttribute("player", new Player());
        model.addAttribute("clubs", clubs);
        return "playersAdd";
    }

    @PostMapping("/player/add")
    public String addPlayer(Player playerFromForm) {
        try {
            sportService.addPlayer(playerFromForm);
            return "addingSuccess";
        } catch (IllegalArgumentException exception) {
            return "error";
        }
    }

    @GetMapping("/player/edit")
    public String editHome(Model model, @RequestParam Long id) {
        Optional<Player> playerById = sportService.findPlayerById(id);
        List<Club> clubs = sportService.findAllClubs();
        if (playerById.isEmpty()) {
            return "error";
        }
        model.addAttribute("player", playerById.get());
        model.addAttribute("clubs", clubs);
        return "playersEdit";
    }

    @PostMapping("/player/edit")
    public String editPlayer(Player playerFromForm) {
        try {
            sportService.editPlayer(playerFromForm);
            return "editingSuccess";
        } catch (NullPointerException exception) {
            return "error";
        }
    }

    @GetMapping("/player/{id}/delete")
    public String deleteClub(@PathVariable Long id) {
        try {
            sportService.deletePlayerById(id);
            return "deletingSuccess";
        } catch (NullPointerException exception) {
            return "error";
        }
    }
}
