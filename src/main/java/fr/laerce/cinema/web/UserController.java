package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.RoleDao;
import fr.laerce.cinema.dao.UserDao;
import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.Personne;
import fr.laerce.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserDao userDao;


    @GetMapping("/")
    public String main3(Model model){
        model.addAttribute("users",userDao.findAll());
        model.addAttribute("newuser",new User());
        return "User/ListeUser";
    }

    @GetMapping("/details/{id}")
    public String detailFilm(Model model, @PathVariable("id")long id){
        model.addAttribute("user", userDao.findById(id).get());
        return "User/detailsUser";
    }
    @PostMapping("/creation")
    public String crea(@ModelAttribute User user){
        userDao.save(user);
        return "redirect:/User/";
    }
    @PostMapping("/modification")
    public String modi(@ModelAttribute User user){
        userDao.save(user);

        return "redirect:/User/";
    }
    @GetMapping("/suprimer")
    public String supacteur(@ModelAttribute("id")Long id){

        userDao.deleteById(id);
        return "redirect:/User/";
    }
}
