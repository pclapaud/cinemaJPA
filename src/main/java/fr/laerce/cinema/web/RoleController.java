package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.FilmDao;
import fr.laerce.cinema.dao.PersonneDao;
import fr.laerce.cinema.dao.RoleDao;
import fr.laerce.cinema.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import fr.laerce.cinema.service.MonImageManager;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


@Controller
@RequestMapping("/Role")
public class RoleController {

    @Autowired
    RoleDao RoleDao;


    @GetMapping("/")
    public String main3(Model model){
        model.addAttribute("roles",RoleDao.findAll());
        return "redirect:/";
    }


}
