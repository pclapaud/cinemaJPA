package fr.laerce.cinema.web;//package fr.laerce.cinemav2springbbot.web;



import fr.laerce.cinema.dao.FilmDao;
import fr.laerce.cinema.dao.PersonneDao;
import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.Personne;
import net.bytebuddy.asm.Advice;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    public PersonneDao personneDao;
    @Autowired
    FilmDao filmDao;

    @GetMapping("/")
    public String main(Model model){

        model.addAttribute("nom","Patrick");
        model.addAttribute("films", filmDao.getAll());
        return "index";
    }
@GetMapping("/acteurs")
    public String main3(Model model){
    model.addAttribute("acteurs",personneDao.getAll());
    return "ListeActeurs";
}
    @GetMapping("/films")
    public String main2(Model model){
        model.addAttribute("films",filmDao.getAll());
        model.addAttribute("persons",personneDao.getAll());
        model.addAttribute("Film", new Film());
        return "ListeFilms";
    }

    @GetMapping("/detailFilm/{id}")
    public String detailFilm(Model model, @PathVariable("id")long id){
        model.addAttribute("film", filmDao.getById(id));
        return "detailsFilm";
    }

    @GetMapping("/detailActeur/{id}")
    public String detailActeur(Model model, @PathVariable("id") Long id){

        model.addAttribute("Personne", personneDao.getById(id));
        return "detailsActeur";
    }
    @GetMapping("/creation")
    public String modacteur(@ModelAttribute("nom")String nom,@ModelAttribute("prenom")String prenom,@ModelAttribute("naissance")Integer naissance){
        Personne person = new Personne();
        person.setNom(nom);
        person.setPrenom(prenom);
        person.setNaissance(naissance);
        personneDao.save(person);
        return "redirect:/";
    }
    @GetMapping("/modification")
    public String supacteur(@ModelAttribute("id")Long id,@ModelAttribute("nom")String nom,@ModelAttribute("prenom")String prenom,@ModelAttribute("naissance")Integer naissance,@ModelAttribute("photoPath")String photoPath){
        Personne person = personneDao.getById(id);
        person.setNom(nom);
        person.setPrenom(prenom);
        person.setNaissance(naissance);
        personneDao.save(person);
        return "redirect:/";
    }
    @GetMapping("/suprimer")
    public String supacteur(@ModelAttribute("id")Long id){

        personneDao.remove(id);
        return "redirect:/";
    }
//    @GetMapping("/recherche{test}")
//    public String recherche(Model model, @PathVariable("test")String test){
//
//        model.addAttribute("film", dataModel.containsFilms(test));
//        return "index";
//
//    }
//
//
    @RequestMapping(value = "/images/{dossier}/{id}")
    public ResponseEntity<byte[]> affiche(@PathVariable("id")String id,@PathVariable("dossier")String dossier) {
        try {
            // Chemin absolu de l'image
            File file = ResourceUtils.getFile("classpath:images/"+dossier+"/"+id);
//            File file = new File(monUrl+"\\"+dossier+"\\"+id);

            FileInputStream in = new FileInputStream(file);
            byte[] media = IOUtils.toByteArray(in);
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, HttpStatus.OK);
            return responseEntity;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




}

