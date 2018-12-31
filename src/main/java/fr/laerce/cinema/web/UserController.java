package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.RoleDao;
import fr.laerce.cinema.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserDao userDao;


    @GetMapping("/")
    public String main3(Model model){
        model.addAttribute("users",userDao.findAll());
        return "User/ListeUser";
    }


}
