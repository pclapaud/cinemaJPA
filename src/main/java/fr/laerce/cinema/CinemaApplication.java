//package fr.laerce.cinema;
//
//import fr.laerce.cinema.dao.DataModel;
//import fr.laerce.cinema.dao.PersonneDao;
//import fr.laerce.cinema.model.Personne;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootApplication
//public class CinemaApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(CinemaApplication.class, args);
//    }
//
//    @Bean
//
//    public CommandLineRunner runner(PersonneDao dao){
//        return new CommandLineRunner() {
//            @Override
//            @Transactional
//            public void run(String... args) throws Exception {
//
//                List<Personne> persons = dao.getAll();
//                for (Personne p: persons
//                     ) {
//                    System.out.println(p.getNom());
//                }
////                Personne person = new Personne();
////                person.setNom("Ventura");
////                person.setPrenom("Lino");
////                dao.save(person);
//
//            }
//        };
//    }
//
//}
//
package fr.laerce.cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }
}
