package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.FilmDao;
import fr.laerce.cinema.dao.PersonneDao;
import fr.laerce.cinema.dao.RoleDao;
import fr.laerce.cinema.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleDao RoleDao;
    @Autowired
    FilmDao filmDao;
    @Autowired
    PersonneDao personneDao;


    @GetMapping("/")
    public String main3(Model model){
        model.addAttribute("roles",RoleDao.findAll());
        model.addAttribute("films",filmDao.findAll());
        model.addAttribute("personnes",personneDao.findAll());
        return "Role/ListeRole";
    }
    @GetMapping("/details/")
    public String detailFilm(Model model, @ModelAttribute("role")Role role){
        model.addAttribute("role", (Role)role);
        return "Role/detailsRole";
    }
    @PostMapping("/creation")
    public String modacteur( @ModelAttribute("film_id")long film_id, @ModelAttribute("person_id")long person_id, @ModelAttribute("rank")Integer rank, @ModelAttribute("name")String name){
        Role role = new Role();
        role.setRank(rank);
        role.setName(name);

        RoleDao.save(role);
        return "redirect:/acteur/";
    }
//    @GetMapping("/modification")
//    public String supacteur(@ModelAttribute("id")Long id,@ModelAttribute("nom")String nom,@ModelAttribute("prenom")String prenom,@ModelAttribute("naissance")String naissance,@ModelAttribute("photoPath")String photoPath){
//        Personne person = personneDao.findById(id).get();
//        person.setNom(nom);
//        person.setPrenom(prenom);
//        person.setNaissance(LocalDate.parse(naissance));
//        personneDao.save(person);
//        return "redirect:/acteur/details/"+id;
//    }
//    @GetMapping("/suprimer")
//    public String supacteur(@ModelAttribute("id")Long id){
//        personneDao.deleteById(id);
//        return "redirect:/acteur/";
//    }


}
