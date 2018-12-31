package fr.laerce.cinema.dao;

import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.revue;
import org.springframework.data.repository.CrudRepository;


public interface reviewDao extends CrudRepository<revue, Long> {

}
