package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.FilmDao;
import fr.laerce.cinema.dao.PersonneDao;
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
@RequestMapping("/acteur")
public class ActeurController {

    @Autowired
    public PersonneDao personneDao;
    @Autowired
    FilmDao filmDao;
    @Autowired
    MonImageManager ImageMana;


    @GetMapping("/")
    public String main3(Model model){
        model.addAttribute("acteurs",personneDao.findAll());
        return "acteur/ListeActeurs";
    }

    @GetMapping("/details/{id}")
    public String detailActeur(Model model, @PathVariable("id") Long id){

        model.addAttribute("Personne", personneDao.findById(id).get());

        return "acteur/detailsActeur";
    }
    @PostMapping("/creation")
    public String modacteur(@RequestParam("file") MultipartFile file, @ModelAttribute("nom")String nom, @ModelAttribute("prenom")String prenom, @ModelAttribute("naissance")String naissance){
        Personne person = new Personne();
        person.setNom(nom);
        person.setPrenom(prenom);
        person.setNaissance(LocalDate.parse(naissance));
        String fileName=ImageMana.ajouteImage("p","personnes",file);
        person.setPhotoPath(fileName);
        personneDao.save(person);
        return "redirect:/acteur/";
    }
    @GetMapping("/modification")
    public String supacteur(@ModelAttribute("id")Long id,@ModelAttribute("nom")String nom,@ModelAttribute("prenom")String prenom,@ModelAttribute("naissance")String naissance,@ModelAttribute("photoPath")String photoPath){
        Personne person = personneDao.findById(id).get();
        person.setNom(nom);
        person.setPrenom(prenom);
        person.setNaissance(LocalDate.parse(naissance));
        personneDao.save(person);
        return "redirect:/acteur/details/"+id;
    }
    @GetMapping("/suprimer")
    public String supacteur(@ModelAttribute("id")Long id){
        personneDao.deleteById(id);
        return "redirect:/acteur/";
    }
}