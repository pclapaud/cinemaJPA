package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.UserDao;
import fr.laerce.cinema.model.Personne;
import fr.laerce.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/session")
public class SessionController {

    @Autowired
    UserDao userdao;

    @GetMapping("/")
    public String main3(){
        return "acteur/ListeActeurs";
    }
    @GetMapping("/connect")
    public String connect(@RequestParam("login")String login,@RequestParam("password")String password, HttpSession session) {
        User user = userdao.findByLoginAndPassword(login, password);
        session.setAttribute("user",user);
        return "redirect:/";
    }
    @GetMapping("/deconnexion")
    public String deco(HttpSession session) {

        session.setAttribute("user",null);
        return "redirect:/";
    }
}
