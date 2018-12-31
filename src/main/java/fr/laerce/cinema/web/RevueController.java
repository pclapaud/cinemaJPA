package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.UserDao;
import fr.laerce.cinema.dao.reviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/revues")
public class RevueController {

    @Autowired
    reviewDao reviewDao;


    @GetMapping("/")
    public String main3(Model model){
        model.addAttribute("revues",reviewDao.findAll());
        return "Revue/ListeRevue";
    }


}
