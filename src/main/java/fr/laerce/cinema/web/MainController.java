package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.FilmDao;
import fr.laerce.cinema.dao.PersonneDao;
import fr.laerce.cinema.model.Personne;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Controller
public class MainController {

    @Autowired
    public PersonneDao personneDao;
    @Autowired
    FilmDao filmDao;

    @GetMapping("/")
    public String main(Model model){

        model.addAttribute("nom","Patrick");
        model.addAttribute("films", filmDao.findAll());
        return "index";
    }





    @RequestMapping(value = "/images/{dossier}/{id}")
    public ResponseEntity<byte[]> affiche(@PathVariable("id")String id,@PathVariable("dossier")String dossier) {
        try {
            // Chemin absolu de l'image
            //File file = ResourceUtils.getFile("classpath:images/"+dossier+"/"+id);
            File file = new File("src/main/stockage/images/"+dossier+"/"+id);

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

