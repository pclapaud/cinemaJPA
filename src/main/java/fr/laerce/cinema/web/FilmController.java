package fr.laerce.cinema.web;//package fr.laerce.cinemav2springbbot.web;

import fr.laerce.cinema.dao.FilmDao;
import fr.laerce.cinema.dao.PersonneDao;
import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.Personne;
import fr.laerce.cinema.service.MonImageManager;
import net.bytebuddy.asm.Advice;
import org.apache.catalina.connector.Request;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/film")
public class FilmController {

    @Autowired
    FilmDao filmDao;
    @Autowired
    PersonneDao personneDao;
    @Autowired
    MonImageManager ImageMana;

    @GetMapping("/")
    public String main2(Model model){
        model.addAttribute("films",filmDao.findAll());
        model.addAttribute("persons",personneDao.findAll());
        model.addAttribute("film", new Film());
        return "Film/ListeFilms";
    }

    @GetMapping("/details/{id}")
    public String detailFilm(Model model, @PathVariable("id")long id){
        model.addAttribute("film", filmDao.findById(id).get());
        model.addAttribute("persons", personneDao.findAll());
        return "Film/detailsFilm";
    }
    //@RequestMapping(value=("/creation"),headers=("content-type=multipart/*"),method=RequestMethod.POST)
   @PostMapping("/creation")
    public String modacteur(@RequestParam("titre") String titre ,@RequestParam("realisateur") Long realisateur ,@RequestParam("note") double note ,@RequestParam("sommaire") String sommaire , @RequestParam("file") MultipartFile file){
        //genere mon nouveau filename
            Film film = new Film();
            String fileName=ImageMana.ajouteImage("f","affiches",file);
            film.setSummary(sommaire);
            film.setTitle(titre);
            film.setRating(note);
            film.setImagePath(fileName);
            Personne per = personneDao.findById(realisateur).get();
            film.setRealisateur(per);
            filmDao.save(film);

        return "redirect:/film/";
    }
    @PostMapping("/modification")
    public String modi(@RequestParam("id")Long id ,@RequestParam("titre") String titre ,@RequestParam("realisateur") Long realisateur ,@RequestParam("note") double note ,@RequestParam("sommaire") String sommaire){
        //genere mon nouveau filename

        Film film = filmDao.findById(id).get();
        film.setSummary(sommaire);
        film.setTitle(titre);
        film.setRating(note);

        Personne per = personneDao.findById(realisateur).get();
        film.setRealisateur(per);
        filmDao.save(film);

        return "redirect:/film/";
    }
    @GetMapping("/suprimer")
    public String supacteur(@ModelAttribute("id")Long id){

        filmDao.deleteById(id);
        return "redirect:/film/";
    }
}
