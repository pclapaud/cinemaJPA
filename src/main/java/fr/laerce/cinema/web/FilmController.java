package fr.laerce.cinema.web;//package fr.laerce.cinemav2springbbot.web;

import fr.laerce.cinema.dao.FilmDao;
import fr.laerce.cinema.dao.GenreDao;
import fr.laerce.cinema.dao.PersonneDao;
import fr.laerce.cinema.dao.RoleDao;
import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.Genre;
import fr.laerce.cinema.model.Personne;
import fr.laerce.cinema.model.Role;
import fr.laerce.cinema.service.MonImageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/film")
public class FilmController {

    @Autowired
    FilmDao filmDao;
    @Autowired
    PersonneDao personneDao;
    @Autowired
    RoleDao roledao;
    @Autowired
    GenreDao genreDao;
    @Autowired
    MonImageManager ImageMana;

    @GetMapping("/")
    public String main2(Model model){
        model.addAttribute("films",filmDao.findAll());
        model.addAttribute("persons",personneDao.findAll());
        model.addAttribute("genres",genreDao.findAll());
        model.addAttribute("film", new Film());
        return "Film/ListeFilms";
    }

    @GetMapping("/details/{id}")
    public String detailFilm(Model model, @PathVariable("id")long id){
        model.addAttribute("film", filmDao.findById(id).get());
        model.addAttribute("persons", personneDao.findAll());
        model.addAttribute("roles", roledao.findAll());
        model.addAttribute("genres",genreDao.findAll());
        model.addAttribute("newrole",new Role());
        return "Film/detailsFilm";
    }
    @PostMapping("/creation")
    public String modacteur(@RequestParam("titre") String titre , @RequestParam("realisateur") Long realisateur , @RequestParam("note") double note , @RequestParam("sommaire") String sommaire , @RequestParam("file") MultipartFile file, @RequestParam("genre") List<Genre> lesgenres){

            Film film = new Film();
            String fileName=ImageMana.ajouteImage("f","affiches",file);
            film.setSummary(sommaire);
            film.setTitle(titre);
            film.setRating(note);
            film.setImagePath(fileName);
            film.setLesGenres(lesgenres);
            Personne per = personneDao.findById(realisateur).get();
            film.setDirector(per);
            filmDao.save(film);

        return "redirect:/film/";
    }
    @PostMapping("/modification")
    public String modi(@RequestParam("id")Long id ,@RequestParam("titre") String titre ,@RequestParam("realisateur") Long realisateur ,@RequestParam("note") double note ,@RequestParam("sommaire") String sommaire, @RequestParam("genre") List<Genre> lesgenres){


        Film film = filmDao.findById(id).get();
        film.setSummary(sommaire);
        film.setTitle(titre);
        film.setRating(note);
        film.setLesGenres(lesgenres);
        Personne per = personneDao.findById(realisateur).get();
        film.setDirector(per);
        filmDao.save(film);

        return "redirect:/film/";
    }
    @GetMapping("/suprimer")
    public String supacteur(@ModelAttribute("id")Long id){

        filmDao.deleteById(id);
        return "redirect:/film/";
    }
    @PostMapping("/nouveaurole")
    public String newrole( @ModelAttribute Role role,@ModelAttribute("id")Long id,@ModelAttribute("personne")Long personne_id){
        role.setFilm(filmDao.findById(id).get());
        role.setPersonne(personneDao.findById(personne_id).get());
        roledao.save(role);
        return "redirect:/film/details/"+id;
    }
}
