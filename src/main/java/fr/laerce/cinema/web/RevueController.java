package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.FilmDao;
import fr.laerce.cinema.dao.PersonneDao;
import fr.laerce.cinema.dao.UserDao;
import fr.laerce.cinema.dao.reviewDao;
import fr.laerce.cinema.model.Personne;
import fr.laerce.cinema.model.User;
import fr.laerce.cinema.model.revue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
@RequestMapping("/Revue")
public class RevueController {

    @Autowired
    reviewDao reviewDao;
    @Autowired
    FilmDao filmDao;
    @Autowired
    UserDao userDao;


    @GetMapping("/")
    public String main3(Model model){
        model.addAttribute("revues",reviewDao.findAll());
        model.addAttribute("films",filmDao.findAll());
        model.addAttribute("users",userDao.findAll());
        model.addAttribute("newrevue",new revue());
        return "Revue/ListeRevue";
    }

    @GetMapping("/details/{id}")
    public String detailFilm(Model model, @PathVariable("id")long id){
        model.addAttribute("revue", reviewDao.findById(id).get());
        model.addAttribute("films",filmDao.findAll());
        model.addAttribute("users",userDao.findAll());
        return "Revue/detailsRevue";
    }
    @PostMapping("/creation")
    public String crea(@ModelAttribute revue revue,@ModelAttribute("date")String date){
        revue.setDatte(LocalDate.parse(date));
        reviewDao.save(revue);
        return "redirect:/Revue/";
    }
    @PostMapping("/modification")
    public String modi(@ModelAttribute revue revue,@ModelAttribute("date")String date){
        revue.setDatte(LocalDate.parse(date));
        reviewDao.save(revue);
        return "redirect:/Revue/";
    }
    @GetMapping("/suprimer")
    public String supacteur(@ModelAttribute("id")Long id){
        reviewDao.deleteById(id);
        return "redirect:/Revue/";
    }


}
