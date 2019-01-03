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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    @GetMapping("/ajoutfilm")
    public String ajoutfilm(@RequestParam("film")long film_id,@RequestParam("genre")long genre_id){
        Genre genre = genreDao.findById(genre_id).get();
        Film film = filmDao.findById(film_id).get();
        List<Genre> listActua = film.getLesGenres();
        listActua.add(genre);
        film.setLesGenres(listActua);
        filmDao.save(film);
        return "redirect:/Genre/";
    }
    @PostMapping("/modification")
    public String cremoda(@ModelAttribute Genre genre){
        genreDao.save(genre);
        return "redirect:/Genre/";
    }
    @GetMapping("/suprimer")
    public String supacteur(@ModelAttribute("id")Long id){
        Genre genre = genreDao.findById(id).get();
        for (Film film:genre.getFilmGenre()
             ) {
            delie(id,film.getId());
        }
        genreDao.deleteById(id);
        return "redirect:/Genre/";
    }
    @GetMapping("/delie/{id}")
    public String delieage(@ModelAttribute("id")Long id,@ModelAttribute("film")Long film_id){
        delie(id,film_id);
        return "redirect:/Genre/";
    }
    public void delie(long id, long film_id){
        Genre genre = genreDao.findById(id).get();
        Film film = filmDao.findById(film_id).get();
        List<Genre> listActua = film.getLesGenres();
        listActua.remove(genre);
        film.setLesGenres(listActua);
        filmDao.save(film);
    }
}
