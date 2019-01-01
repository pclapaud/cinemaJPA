package fr.laerce.cinema.web;


import fr.laerce.cinema.dao.FilmDao;
import fr.laerce.cinema.dao.GenreDao;
import fr.laerce.cinema.dao.UserDao;
import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.Genre;
import fr.laerce.cinema.model.revue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/Genre")
public class GenreController {
    @Autowired
    GenreDao genreDao;
    @Autowired
    FilmDao filmDao;


    @GetMapping("/")
    public String main3(Model model){
        model.addAttribute("genres",genreDao.findAll());
        model.addAttribute("films",filmDao.findAll());
        model.addAttribute("newGenre",new Genre());
        return "Genre/ListeGenre";
    }
    @GetMapping("/details/{id}")
    public String detailFilm(Model model, @PathVariable("id")long id){
        model.addAttribute("genree", genreDao.findById(id).get());

        return "Genre/detailsGenre";
    }
    @PostMapping("/creation")
    public String crea(@ModelAttribute Genre genre){
        genreDao.save(genre);
        return "redirect:/Genre/";
    }
    @PostMapping("/ajoutfilm")
    public String ajoutfilm(@RequestParam("film")long film_id,@RequestParam("genre")long genre_id){
        Genre genre = genreDao.findById(genre_id).get();
        List<Film> listActua = genre.getFilmGenre();
        listActua.add(filmDao.findById(film_id).get());
        genre.setFilmGenre(listActua);
        genreDao.save(genre);
        return "redirect:/Genre/";
    }
    @PostMapping("/modification")
    public String cremoda(@ModelAttribute Genre genre){
        genreDao.save(genre);
        return "redirect:/Genre/";
    }
    @GetMapping("/suprimer")
    public String supacteur(@ModelAttribute("id")Long id){

        genreDao.deleteById(id);
        return "redirect:/Genre/";
    }
}
