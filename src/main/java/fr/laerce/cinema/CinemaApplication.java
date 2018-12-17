package fr.laerce.cinema;

import fr.laerce.cinema.dao.FilmDao;
import fr.laerce.cinema.dao.PersonneDao;
import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.Personne;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@SpringBootApplication
public class CinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner runner(PersonneDao dao){
//        return new CommandLineRunner() {
//            @Override
//            @Transactional
//            public void run(String... args) throws Exception {
//
//                List<Film> films = dao.getAll().get(1).getLesfilms();
//                for (Film f: films
//                     ) {
//                    System.out.println(f.getTitle());
//                }
//            }
//        };
//    }
@Bean
public CommandLineRunner runner(PersonneDao dao){
    return new CommandLineRunner() {
        @Override
        @Transactional
        public void run(String... args) throws Exception {
//            List<Personne> Persons = dao.getAll();
//            for (Personne f: Persons
//            ) {
//                System.out.println(f.getNom());
//            }

                List<Film> films = dao.getAll().get(21).getLesfilms();
                for (Film f : films
                ) {
                    System.out.println(f.getTitle());
                }
            }

    };
}
}
