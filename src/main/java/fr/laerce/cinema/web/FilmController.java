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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
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

    @GetMapping("/")
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
    //@RequestMapping(value=("/creation"),headers=("content-type=multipart/*"),method=RequestMethod.POST)
   @PostMapping("/creation")
    public String modacteur(@Valid Film film, @RequestParam("file") MultipartFile file){
        try {
            String filename = filmDao.getFilename();
            byte[] bytes = file.getBytes();
            Path path = Paths.get("src/main/resources/images/affiches/" + filename);
            Files.write(path, bytes);
            film.setImagePath(filename);
            filmDao.save(film);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/films";
    }
}