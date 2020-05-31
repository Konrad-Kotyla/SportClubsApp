package pl.javastart.clubs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.clubs.model.Club;
import pl.javastart.clubs.model.ClubFilters;
import pl.javastart.clubs.model.Country;
import pl.javastart.clubs.model.Player;
import pl.javastart.clubs.service.SportService;

import java.util.List;
import java.util.Optional;

@Controller
public class ClubController {

    private SportService sportService;

    @Autowired
    public ClubController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping("/clubs")
    public String clubs(Model model,
                        @RequestParam(required = false) String sort,
                        @RequestParam(required = false) Country country) {
        List<Club> clubs;
        if (country != null) {
            clubs = sportService.findClubsByCountry(country);
        } else if (sort != null) {
            clubs = sportService.findAllSortedClubs(sort);
        } else {
            clubs = sportService.findAllClubs();
        }
        model.addAttribute("clubs", clubs);
        model.addAttribute("filters", new ClubFilters());
        return "clubs";
    }

    @GetMapping("/club/{id}")
    public String getOne(@PathVariable Long id, Model model) {
        Optional<Club> clubById = sportService.findClubById(id);
        List<Player> players = sportService.findAllPlayersByClubId(id);
        if (clubById.isEmpty()) {
            return "error";
        }
        model.addAttribute("club", clubById.get());
        model.addAttribute("players", players);
        return "clubSite";
    }


    @GetMapping("/club/add")
    public String addClubHome(Model model) {
        model.addAttribute("club", new Club());
        return "clubsAdd";
    }

    @PostMapping("/club/add")
    public String addClub(Club clubFromForm) {
        try {
            sportService.addClub(clubFromForm);
            return "addingSuccess";
        } catch (IllegalArgumentException exception) {
            return "error";
        }
    }

    @GetMapping("/club/edit")
    public String editHome(Model model, @RequestParam Long id) {
        Optional<Club> clubById = sportService.findClubById(id);
        if (clubById.isEmpty()) {
            return "error";
        }
        model.addAttribute("club", clubById.get());
        return "clubsEdit";
    }

    @PostMapping("/club/edit")
    public String editClub(Club clubFromForm) {
        try {
            sportService.editClub(clubFromForm);
            return "editingSuccess";
        } catch (NullPointerException exception) {
            return "error";
        }
    }

    @GetMapping("/club/{id}/delete")
    public String deleteClub(@PathVariable Long id) {
        try {
            sportService.deleteClubById(id);
            return "deletingSuccess";
        } catch (NullPointerException exception) {
            return "error";
        }
    }

    @GetMapping("/like")
    public String likeIt(Model model, @RequestParam Long id, Club club) {
        Optional<Club> clubById = sportService.findClubById(id);
        if (clubById.isEmpty()) {
            return "error";
        }
        model.addAttribute("club", clubById);

        int like = sportService.likeIt(club.getId(), club.getLikes());
        club.setLikes(like);
        model.addAttribute("like", sportService.likeIt(id, clubById.get().getLikes()));
        return "redirect:/club/" + id;
    }
}
