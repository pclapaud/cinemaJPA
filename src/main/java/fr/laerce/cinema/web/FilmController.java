package fr.laerce.cinema.web;//package fr.laerce.cinemav2springbbot.web;



import fr.laerce.cinema.dao.FilmDao;
import fr.laerce.cinema.dao.PersonneDao;
import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.Personne;
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

    @GetMapping("/")
    public String main2(Model model){
        model.addAttribute("films",filmDao.findAll());
        model.addAttribute("persons",personneDao.findAll());
        model.addAttribute("Film", new Film());
        return "ListeFilms";
    }

    @GetMapping("/detailFilm/{id}")
    public String detailFilm(Model model, @PathVariable("id")long id){
        model.addAttribute("film", filmDao.findById(id).get());
        return "detailsFilm";
    }
    //@RequestMapping(value=("/creation"),headers=("content-type=multipart/*"),method=RequestMethod.POST)
   @PostMapping("/creation")
    public String modacteur(@RequestParam("titre") String titre ,@RequestParam("realisateur") Long realisateur ,@RequestParam("note") double note ,@RequestParam("sommaire") String sommaire , @RequestParam("file") MultipartFile file){


            String fileName = "";
            try(DirectoryStream<Path> dir = Files.newDirectoryStream(Paths.get("src/main/resources/images/affiches/" ),"f"+"*")){

                for (Path filel: dir
                ) {
                    if(fileName.compareTo(filel.getFileName().toString())<0){
                        fileName = filel.getFileName().toString();
                    }
                }
                fileName = fileName.substring(1);

                Integer tempo = Integer.parseInt(fileName.substring(0,fileName.lastIndexOf('.')))+1;
                String resultat =String.valueOf(tempo);
                for(int i = String.valueOf(tempo).length(); i<4;i++){
                    resultat = 0+resultat;
                }
                fileName = "f"+resultat+".jpg";
            Film film = new Film();
            byte[] bytes = file.getBytes();
            Path path = Paths.get("src/main/resources/images/affiches/" + fileName);
            Files.write(path, bytes);
            film.setSummary(sommaire);
            film.setTitle(titre);
            film.setRating(note);
            film.setImagePath(fileName);
            Personne per = personneDao.findById(realisateur).get();
            film.setRealisateur(per);
            filmDao.save(film);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/films";
    }}
